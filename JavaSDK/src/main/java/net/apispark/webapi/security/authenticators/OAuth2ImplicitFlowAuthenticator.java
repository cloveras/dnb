package net.apispark.webapi.security.authenticators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Uniform;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.data.Reference;
import org.restlet.engine.util.StringUtils;
import org.restlet.resource.ClientResource;

/**
 * Configures authentication based on the OAuth2 Implicit flow.
 */
public class OAuth2ImplicitFlowAuthenticator implements Authenticator {

    /**
     * When handling a request, set the current OAuth credentials.
     */
    private static class OAuthUniform implements Uniform {

        private Uniform next;

        private OAuth2ImplicitFlowAuthenticator oauth;

        public OAuthUniform(ClientResource clientResource, OAuth2ImplicitFlowAuthenticator oauth) {
            this.next = clientResource.getNext();
            this.oauth = oauth;
        }

        @Override
        public void handle(Request request, Response response) {
            oauth.setAuthorization(request);
            this.next.handle(request, response);
        }
    }

    private static Logger LOGGER = Logger.getLogger(OAuth2ImplicitFlowAuthenticator.class.getCanonicalName());

    /** The current access token. */
    private String accessToken;

    /**
     * Indicates whether the access token is sent by query instead of the Authorization header by default (with the
     * Bearer challenge scheme).
     */
    private boolean accessTokenSentAsQueryParameter = false;

    /** The URI of the authorization resource. */
    private String authorizationUri;

    /** The set of parameters that will be used for the authorization request. */
    private Form requestParameters;

    /** The map of parameters sent back by the authorization server. */
    private Map<String, Object> responseParameters;

    /** The date at which the access token expires. */
    private Date tokenExpirationDate;

    /**
     * Constructor.
     */
    public OAuth2ImplicitFlowAuthenticator() {
        requestParameters = new Form();
    }

    @Override
    public void configure(ClientResource clientResource) {
        clientResource.setNext(new OAuthUniform(clientResource, this));
    }

    /**
     * Given the URL to which the authorization server has redirected the user agent, extracts and checks all parameters
     * in
     * the fragment part. Especially, it ensures the "state" sent in the authorization request has been preserved.
     * 
     * @param redirectedUrl
     *            The URL to which the authorization server has redirected the user agent.
     */
    public void fetchAccessToken(String redirectedUrl) {

        responseParameters = new HashMap<>();

        Reference ref = new Reference(redirectedUrl);
        String fragment = ref.getFragment(true);
        if (StringUtils.isNullOrEmpty(fragment)) {
            this.accessToken = null;
            throw new RuntimeException("The redirection url does not contain a fragment.");
        }

        for (Parameter parameter : new Form(fragment)) {
            responseParameters.put(parameter.getName(), parameter.getValue());
        }

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

        this.accessToken = (String) responseParameters.get("access_token");
        if (StringUtils.isNullOrEmpty(accessToken)) {
            throw new RuntimeException("No access token provided.");
        }
        if (StringUtils.isNullOrEmpty((String) responseParameters.get("token_type"))) {
            LOGGER.fine("No token type provided, added bearer instead");
            responseParameters.put("token_type", "bearer");
        }

        // set the expiration date
        if (!StringUtils.isNullOrEmpty((String) responseParameters.get("expires_in"))) {
            LOGGER.fine("Token expires in " + responseParameters.get("expires_in"));
            try {
                int expiresIn = Integer.parseInt((String) responseParameters.get("expires_in"));
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.add(Calendar.SECOND, expiresIn);
                tokenExpirationDate = calendar.getTime();
            } catch (Exception e) {
                LOGGER.warning("Invalid parameter \"expires_in\" " + responseParameters.get("expires_in"));
                tokenExpirationDate = null;
            }
        } else {
            tokenExpirationDate = null;
        }

        // the scope has been set by the server, let's update it.
        if (!StringUtils.isNullOrEmpty((String) responseParameters.get("scope"))) {
            requestParameters.set("scope", (String) responseParameters.get("scope"));
        }

        String expectedState = requestParameters.getFirstValue("state");
        String providedState = (String) responseParameters.get("state");

        if (expectedState == null && providedState != null) {
            throw new RuntimeException(String.format(
                    "The \"state\" parameter has been provided with an unexpected value: '%s'.", providedState));
        }
        if (expectedState != null && providedState == null) {
            throw new RuntimeException("The \"state\" parameter is required in the response but has not been provided.");
        }
        if (expectedState != null && !expectedState.equals(providedState)) {
            throw new RuntimeException(
                    String.format(
                            "The \"state\" parameter has been provided with an unexpected value: '%s' whereas '%s' was expected.",
                            providedState, expectedState));
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
     * Returns the computed URL of the authorization endpoint, according to the parameters previously set such as the
     * redirection URL, etc.
     * 
     * @return The computed URL of the authorization endpoint.
     */
    public String getAuthorizationUri() {

        if (StringUtils.isNullOrEmpty(authorizationUri)) {
            throw new RuntimeException("The authorization URI is mandatory.");
        }

        if (StringUtils.isNullOrEmpty(requestParameters.getFirstValue("client_id"))) {
            throw new RuntimeException("The client id is mandatory.");
        }

        Reference ref = new Reference(authorizationUri);
        ref.addQueryParameter("response_type", "token");
        for (Parameter parameter : requestParameters) {
            ref.addQueryParameter(parameter.getName(), parameter.getValue());
        }

        return ref.toString(true, false);
    }

    /**
     * Returns the lifetime in seconds of the access token, as returned by the authorization server. The estimated
     * expiration date is available by calling {@link #getTokenExpirationDate()}.
     * 
     * @return The lifetime in seconds of the access token, as returned by the authorization server.
     */
    public String getExpiresIn() {
        if (responseParameters.containsKey("expires_in")) {
            return (String) responseParameters.get("expires_in");
        }
        return null;
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
     * Returns the "state" parameter of the next authorization request.
     * 
     * @return The value of the "state" parameter.
     */
    public String getState() {
        return (String) responseParameters.get("state");
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
     * Indicates whether the access token is sent by query. By default, it is sent by using the Authorization header
     * (with the Bearer challenge scheme).
     * 
     * @return True if the access token is sent by query, false by the Authorization header.
     */
    public boolean isAccessTokenSentAsQueryParameter() {
        return accessTokenSentAsQueryParameter;
    }

    /**
     * Indicates whether the access token is sent by query instead of the Authorization header (with the
     * Bearer challenge scheme).
     * 
     * @param accessTokenSentAsQueryParameter
     *            True if the access token is sent by query, false for the Authorization header.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator sendAccessTokenAsQueryParameter(boolean accessTokenSentAsQueryParameter) {
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
    public OAuth2ImplicitFlowAuthenticator setAccessToken(String accessToken) {
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
    public OAuth2ImplicitFlowAuthenticator setAdditionalParameter(String name, String value) {
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
     * Sets the URI of the authorization resource.
     * 
     * @param authorizationUri
     *            The URI of the authorization resource.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator setAuthorizationUri(String authorizationUri) {
        this.authorizationUri = authorizationUri;
        return this;
    }

    /**
     * Sets the client identifier.
     * 
     * @param clientId
     *            the client identifier.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator setClientId(String clientId) {
        setAdditionalParameter("client_id", clientId);
        return this;
    }

    /**
     * Sets the redirection endpoint of the next authorization request.
     * 
     * @param redirectionUrl
     *            The redirection endpoint.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator setRedirectionUri(String redirectionUrl) {
        setAdditionalParameter("redirect_uri", redirectionUrl);
        return this;
    }

    /**
     * Sets the set of parameters that will be used for the authorization request.
     * 
     * @param requestParameters
     *            The set of parameters that will be used for the authorization request.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator setRequestParameters(Form requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    /**
     * Sets the "scope" parameter of the next authorization request.
     * 
     * @param scope
     *            The value of the scope.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator setScope(String scope) {
        setAdditionalParameter("scope", scope);
        return this;
    }

    /**
     * Sets the "state" parameter of the next authorization request.
     * 
     * @param state
     *            The value of the "state" parameter.
     * @return The current instance for fluent usage.
     */
    public OAuth2ImplicitFlowAuthenticator setState(String state) {
        setAdditionalParameter("state", state);
        return this;
    }
}
