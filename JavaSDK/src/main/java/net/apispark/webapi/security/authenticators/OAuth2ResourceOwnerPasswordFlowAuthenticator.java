package net.apispark.webapi.security.authenticators;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Uniform;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.engine.util.StringUtils;
import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.apispark.webapi.security.ClientFactory;

/**
 * Configures authentication based on the OAuth2 Resource owner password flow.
 */
public class OAuth2ResourceOwnerPasswordFlowAuthenticator implements Authenticator {

    /**
     * When handling a request and set the current OAuth credentials.<br>
     * It also refreshes the OAuth access token if required, either before handing the request, or after the resource
     * server indicates that the credentials are no more valid.
     */
    private static class OAuthUniform implements Uniform {

        private Uniform next;

        private OAuth2ResourceOwnerPasswordFlowAuthenticator oauth;

        public OAuthUniform(ClientResource clientResource, OAuth2ResourceOwnerPasswordFlowAuthenticator oauth) {
            this.next = clientResource.getNext();
            this.oauth = oauth;
        }

        @Override
        public void handle(Request request, Response response) {
            oauth.refreshTokenIfExpired();
            oauth.setAuthorization(request);
            oauth.beforeHandle(request);

            this.next.handle(request, response);

            if (oauth.isAutoRefreshTokenEnabled() && oauth.shouldRefreshToken(response)) {
                oauth.refreshTokens();
                oauth.setAuthorization(request);

                this.next.handle(request, response);
            }
        }
    }

    /**
     * Client resource that transparently handles the computation of the access token, and refreshes the token.
     */

    private static Logger LOGGER = Logger.getLogger(OAuth2ResourceOwnerPasswordFlowAuthenticator.class
            .getCanonicalName());

    /** The current access token. */
    private String accessToken;

    /**
     * Indicates whether the access token is sent by query instead of the Authorization header by default (with the
     * Bearer challenge scheme).
     */
    private boolean accessTokenSentAsQueryParameter = false;

    /**
     * Indicates whether the token is transparently refreshed in case its expiration has been detected. By default, the
     * token is refreshed automatically.
     */
    private boolean autoRefreshTokenEnabled = true;

    /** The current refresh token. */
    private String refreshToken;

    /** The URI of the refresh token endpoint. */
    private String refreshTokenUri;

    /** The set of parameters that will be used for the authorization request. */
    private Form requestParameters;

    /** The map of parameters sent back by the authorization server. */
    private Map<String, Object> responseParameters;

    /** The date at which the access token expires. */
    private Date tokenExpirationDate;

    /** The URI of the token endpoint. */
    private String tokenUri;

    /** The TLS Protocol to use for all token calls. */
    private String specificTlsProtocol;

    /** List of trusted certificates. */
    private final List<InputStream> trustedCertificates = new ArrayList<>();

    /** The ClientFactory used for the requests. */
    private ClientFactory clientFactory;

    /**
     * Constructor.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator() {
        requestParameters = new Form();
    }

    /**
     * Called before requesting the protected resource. Does nothing by default.
     */
    public void beforeHandle(Request request) {
        // Does nothing by default.
    }

    @Override
    public void configure(ClientResource clientResource) {
        clientResource.setNext(new OAuthUniform(clientResource, this));
    }

    /**
     * Called before requesting an access token or refreshing an existing token.<br>
     * The implementation by default does nothing.<br>
     * Override to complete the client resource with extra headers, etc.
     *
     * @param clientResource
     *            The client resource that handles the request to the authorization server.
     */
    public void customizeTokenRequest(ClientResource clientResource) {
        // Does nothing by default.
    }

    /**
     * Indicates whether the access token is transparently refreshed in case it has expired. By default, the token is
     * refreshed automatically.
     *
     * @param enabled
     *            True to enable auto refresh.
     */
    public void enableTokenAutoRefresh(boolean enabled) {
        this.autoRefreshTokenEnabled = enabled;
    }

    /**
     * Parses the entity sent by the authorization server in order to get the access token and any other parameters.
     *
     * @param authorizationResponse
     *            The authorization response.
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    private void extractTokenResponse(Response authorizationResponse) throws IOException, JsonParseException,
            JsonMappingException {
        responseParameters = new ObjectMapper().readValue(authorizationResponse.getEntity().getReader(), Map.class);

        // Check whether the authorization server returns an error.
        if (responseParameters.containsKey("error")) {
            String errorCode = (String) responseParameters.get("error");
            String errorDescription = (String) responseParameters.get("error_description");
            String errorUri = (String) responseParameters.get("error_uri");

            if (StringUtils.isNullOrEmpty(errorUri)) {
                String format = "The authorization request fails for the following reason (%s): %s";
                if (StringUtils.isNullOrEmpty(errorDescription)) {
                    switch (errorCode) {
                    case "invalid_request":
                        errorDescription = "The request is missing a required parameter, includes an invalid parameter value, includes a parameter more than once, or is otherwise malformed.";
                        break;
                    case "unauthorized_client":
                        errorDescription = "The client is not authorized to request an access token using this method.";
                        break;
                    case "access_denied":
                        errorDescription = "The resource owner or authorization server denied the request.";
                        break;
                    case "unsupported_response_type":
                        errorDescription = "The authorization server does not support obtaining an access token using this method.";
                        break;
                    case "invalid_scope":
                        errorDescription = "The requested scope is invalid, unknown, or malformed.";
                        break;
                    case "server_error":
                        errorDescription = "The authorization server encountered an unexpected condition that prevented it from fulfilling the request.";
                        break;
                    case "temporarily_unavailable":
                        errorDescription = "The authorization server is currently unable to handle the request due to a temporary overloading or maintenance of the server.";
                        break;
                    default:
                        errorDescription = errorCode;
                        break;
                    }
                }
                throw new RuntimeException(String.format(format, errorCode, errorDescription));
            } else {
                String format = "The authorization request fails due to the following error (%s). You will find more details here: %s";
                throw new RuntimeException(String.format(format, errorCode, errorUri));
            }
        }

        // set the expiration date
        if (responseParameters.containsKey("expires_in")) {
            int expiresIn = (int) responseParameters.get("expires_in");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.add(Calendar.SECOND, expiresIn);
            tokenExpirationDate = calendar.getTime();
        } else {
            tokenExpirationDate = null;
        }

        // the scope has been set by the server, let's update it.
        if (!StringUtils.isNullOrEmpty((String) responseParameters.get("scope"))) {
            requestParameters.set("scope", (String) responseParameters.get("scope"));
        }

        accessToken = (String) responseParameters.get("access_token");
        if (StringUtils.isNullOrEmpty(accessToken)) {
            throw new RuntimeException("No access token provided.");
        }
        refreshToken = (String) responseParameters.get("refresh_token");
        LOGGER.fine("[accessToken=" + accessToken + ",refreshToken=" + refreshToken + "] expires at "
                + tokenExpirationDate);
    }

    private ClientFactory getClientFactory() {
        // lazy initialization of ClientFactory to get all settings
        if (clientFactory == null) {
            ClientFactory.ClientFactoryBuilder clientFactoryBuilder = ClientFactory.builder();
            if (!StringUtils.isNullOrEmpty(specificTlsProtocol)) {
                clientFactoryBuilder.setSpecificTlsProtocol(specificTlsProtocol);
            }
            for (InputStream certificate : trustedCertificates) {
                clientFactoryBuilder.addTrustedCertificate(certificate);
            }
            clientFactory = clientFactoryBuilder.build();

        }
        return clientFactory;
    }

    /**
     * According to the current set of parameters, calls the authorization server and get an access token.
     */
    public void fetchAccessToken() {

        if (StringUtils.isNullOrEmpty(tokenUri)) {
            throw new RuntimeException("The token URI is mandatory.");
        }

        // Get the tokens
        Form form = new Form();
        form.add("grant_type", "password");
        form.addAll(requestParameters);

        // check parameters stricly required by the Oauth RFC: username, password
        if (StringUtils.isNullOrEmpty(form.getFirstValue("username"))) {
            throw new RuntimeException("You must specify a username");
        }
        if (StringUtils.isNullOrEmpty(form.getFirstValue("password"))) {
            throw new RuntimeException("You must specify a password");
        }

        ClientResource tokenResource = getClientFactory().newClientResource(tokenUri);
        customizeTokenRequest(tokenResource);
        tokenResource.post(form);
        try {
            extractTokenResponse(tokenResource.getResponse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the current access token.
     *
     * @return The current access token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Returns the lifetime in seconds of the access token, as returned by the authorization server. The estimated
     * expiration date is available by calling {@link #getTokenExpirationDate()}.
     *
     * @return The lifetime in seconds of the access token, as returned by the authorization server.
     */
    public Integer getExpiresIn() {
        if (responseParameters.containsKey("expires_in")) {
            return (int) responseParameters.get("expires_in");

        }
        return null;
    }

    /**
     * Returns the current refresh token.
     *
     * @return The current refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Returns the URI of the refresh token endpoint.
     *
     * @return The URI of the refresh token endpoint.
     */
    public String getRefreshTokenUri() {
        return refreshTokenUri;
    }

    /**
     * Returns the set of parameters that will be used for the authorization request.
     *
     * @return The set of parameters that will be used for the authorization request.
     */
    public Form getRequestParameters() {
        return requestParameters;
    }

    /**
     * Returns the parameters extracted from the last response of the authorization server.
     *
     * @return The parameters extracted from the last response of the authorization server.
     */
    public Map<String, Object> getResponseParameters() {
        return responseParameters;
    }

    /**
     * Returns the "scope" parameter of the next authorization request.
     *
     * @return The value of the scope.
     */
    public String getScope() {
        return requestParameters.getFirstValue("scope");
    }

    /**
     * Returns the TLS Protocol to use for all token calls.
     *
     * @return The TLS Protocol to use for all token calls.
     */
    public String getSpecificTlsProtocol() {
        return specificTlsProtocol;
    }

    /**
     * Returns the date at which the token becomes invalid.
     *
     * @return The expiration date of the token.
     */
    public Date getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    /**
     * Returns the URI of the token endpoint.
     *
     * @return The URI of the token endpoint.
     */
    public String getTokenUri() {
        return tokenUri;
    }

    /**
     * Indicates whether the access token is sent by query. By default, it is sent by using the Authorization header
     * (with the Bearer challenge scheme).
     *
     * @return True if the access token is sent by query, false by the Authorization header.
     */
    public boolean isAccessTokenSentAsQueryParameter() {
        return accessTokenSentAsQueryParameter;
    }

    /**
     * Indicates whether the token is transparently refreshed in case it has expired. By default, the token is refreshed
     * automatically.
     *
     * @return True if the token is transparently refreshed in case it has expired.
     */
    public boolean isAutoRefreshTokenEnabled() {
        return autoRefreshTokenEnabled;
    }

    /**
     * Checks expiration date of the current access token, and recomputes a new one.<br>
     * Called before requesting the protected resource.
     *
     */
    public void refreshTokenIfExpired() {
        if (isAutoRefreshTokenEnabled() && accessToken != null && tokenExpirationDate != null
                && tokenExpirationDate.before(new Date())) {
            refreshTokens();
        }
    }

    /**
     * According to the current set of parameters, calls the authorization server and refresh both access and refresh
     * tokens.
     *
     */
    public void refreshTokens() {
        if (refreshToken == null) {
            return;
        }

        if (StringUtils.isNullOrEmpty(refreshTokenUri)) {
            setRefreshTokenUri(getTokenUri());
        }

        if (StringUtils.isNullOrEmpty(refreshTokenUri)) {
            throw new RuntimeException("The refresh token URI is mandatory.");
        }

        // check the refresh token
        Form refreshForm = new Form();
        refreshForm.add("grant_type", "refresh_token");
        refreshForm.add("refresh_token", refreshToken);
        refreshForm.addAll(requestParameters);

        ClientResource tokenResource = getClientFactory().newClientResource(refreshTokenUri);
        customizeTokenRequest(tokenResource);
        tokenResource.post(refreshForm);

        try {
            extractTokenResponse(tokenResource.getResponse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Indicates whether the access token is sent by query instead of the Authorization header (with the
     * Bearer challenge scheme).
     *
     * @param accessTokenSentAsQueryParameter
     *            True if the access token is sent by query, false for the Authorization header.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator sendAccessTokenAsQueryParameter(
            boolean accessTokenSentAsQueryParameter) {
        this.accessTokenSentAsQueryParameter = accessTokenSentAsQueryParameter;
        return this;
    }

    /**
     * Sets the current access token.
     *
     * @param accessToken
     *            The current access token.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * Adds a parameter in the request sent later to the authorization server.
     *
     * @param name
     *            The name of the parameter.
     * @param value
     *            The value of the parameter.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setAdditionalParameter(String name, String value) {
        if (!StringUtils.isNullOrEmpty(name) && !StringUtils.isNullOrEmpty(value)) {
            requestParameters.set(name, value);
        }
        return this;
    }

    /**
     * Completes the given request with the current access token, either as a "Authorization" header by default, or as
     * query parameter.
     *
     * @param request
     *            The given request with the current access token.
     */
    private void setAuthorization(Request request) {
        LOGGER.fine("Challenge with accessToken " + accessToken);
        if (isAccessTokenSentAsQueryParameter()) {
            Form query = request.getResourceRef().getQueryAsForm();
            query.set("access_token", accessToken);
            request.getResourceRef().setQuery(query.getQueryString());
        } else {
            ChallengeResponse challengeResponse = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
            challengeResponse.setRawValue(accessToken);
            request.setChallengeResponse(challengeResponse);
        }
    }

    /**
     * Sets the resource owner password.
     *
     * @param password
     *            The resource owner password.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setPassword(String password) {
        setAdditionalParameter("password", password);
        return this;
    }

    /**
     * Sets the current refresh token.
     *
     * @param refreshToken
     *            The current refresh token.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    /**
     * Sets the URI of the refresh token endpoint.
     *
     * @param refreshTokenUri
     *            The URI of the refresh token endpoint.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setRefreshTokenUri(String refreshTokenUri) {
        this.refreshTokenUri = refreshTokenUri;
        return this;
    }

    /**
     * Sets the set of parameters that will be used for the authorization request.
     *
     * @param requestParameters
     *            The set of parameters that will be used for the authorization request.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setRequestParameters(Form requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    /**
     * Sets the "scope" parameter of the next authorization request.
     *
     * @param scope
     *            The value of the "scope" parameter.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setScope(String scope) {
        setAdditionalParameter("scope", scope);
        return this;
    }

    /**
     * Sets the TLS Protocol to use for all token calls.
     *
     * @param specificTlsProtocol
     *            The TLS Protocol to use for all token calls.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setSpecificTlsProtocol(String specificTlsProtocol) {
        this.specificTlsProtocol = specificTlsProtocol;
        return this;
    }

    /**
     * Indicates that the protocol TLSv1.2 is used for all HTTPS requests issued by the client resource.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setTlsv12Protocol() {
        setSpecificTlsProtocol("TLSv1.2");
        return this;
    }

    /**
     * Sets the URI of the token endpoint.
     *
     * @param tokenUri
     *            The URI of the token endpoint.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
        return this;
    }

    /**
     * Sets the resource owner user name.
     *
     * @param userName
     *            The resource owner user name.
     * @return The current instance for fluent usage.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator setUserName(String userName) {
        setAdditionalParameter("username", userName);
        return this;
    }

    /**
     * Checks whether the response sent by the authorization server indicates that the access token must be refreshed.<br>
     * By default, the status of the response (status 401).<br>
     * Override to provide your own logic.
     *
     * @param authorizationResponse
     *            The authorization response.
     * @return True if the access token must be refreshed.
     */
    public boolean shouldRefreshToken(Response authorizationResponse) {
        return Status.CLIENT_ERROR_UNAUTHORIZED.equals(authorizationResponse.getStatus());
    }

    /**
     * Add SSL certificate to the list of trusted certificates.
     * Note: InputSteams are closed at first request.
     */
    public OAuth2ResourceOwnerPasswordFlowAuthenticator addTrustedCertificate(InputStream... trustedCertificates) {
        for (InputStream certificate : trustedCertificates) {
            this.trustedCertificates.add(certificate);
        }
        return this;
    }

}
