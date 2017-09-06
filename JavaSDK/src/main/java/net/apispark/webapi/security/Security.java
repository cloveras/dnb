package net.apispark.webapi.security;

import org.restlet.resource.ClientResource;

import net.apispark.webapi.security.authenticators.HeaderApiKeyAuthenticator;
import net.apispark.webapi.security.authenticators.HttpBasicAuthenticator;
import net.apispark.webapi.security.authenticators.OAuth2Authenticator;
import net.apispark.webapi.security.authenticators.OAuth2ImplicitFlowAuthenticator;
import net.apispark.webapi.security.authenticators.OAuth2ResourceOwnerPasswordFlowAuthenticator;
import net.apispark.webapi.security.authenticators.QueryApiKeyAuthentication;

/**
 * Provides helpers to ease the configuration of authenticated client resources.
 */
public class Security {

    /**
     * Configures the given client resource in order to support the HTTP BASIC authentication scheme.
     * 
     * @param clientResource
     *            The client resource to configure.
     * @param userid
     *            The user login.
     * @param password
     *            The user password.
     */
    public static void configureBasicAuth(ClientResource clientResource, String userid, String password) {
        new HttpBasicAuthenticator(userid, password).configure(clientResource);
    }

    /**
     * Configures the given client resource in order to support authentication based on the value of a specific header.
     * 
     * @param clientResource
     *            The client resource to configure.
     * @param headerName
     *            The name of the header.
     * @param headerValue
     *            The header value.
     */
    public static void configureHeaderApiKey(ClientResource clientResource, String headerName, String headerValue) {
        new HeaderApiKeyAuthenticator(headerName, headerValue).configure(clientResource);
    }

    /**
     * Configures the given client resource in order to support authentication based on the value of a specific query
     * parameter.
     * 
     * @param clientResource
     *            The client resource to configure.
     * @param queryParameterName
     *            The name of the query parameter.
     * @param queryParameterValue
     *            The query parameter value.
     */
    public static void configureQueryParameterApiKey(ClientResource clientResource, String queryParameterName,
            String queryParameterValue) {
        new QueryApiKeyAuthentication(queryParameterName, queryParameterValue).configure(clientResource);
    }

    /**
     * Configures the given client resource in order to support OAuth 2.0 authentication scheme based on a single access
     * token. The token in sent with the Authorization header, using the Bearer challenge scheme.
     * 
     * @param clientResource
     *            The client resource.
     * @param token
     *            The access token.
     */
    public static void configureOAuth2(ClientResource clientResource, String token) {
        new OAuth2Authenticator(token).configure(clientResource);
    }

    /**
     * Configures the given client resource in order to support OAuth 2.0 Implicit flow.
     * 
     * @param clientResource
     *            The client resource.
     * @return The OAuth 2.0 configuration instance, for fully controllling the flow.
     */
    public static OAuth2ImplicitFlowAuthenticator configureOAuth2Implicit(ClientResource clientResource) {
        OAuth2ImplicitFlowAuthenticator authenticator = new OAuth2ImplicitFlowAuthenticator();
        authenticator.configure(clientResource);

        return authenticator;
    }

    /**
     * Configures the given client resource in order to support OAuth 2.0 Resource owner password flow.
     * 
     * @param clientResource
     *            The client resource.
     * @return The OAuth 2.0 configuration instance, for fully controllling the flow.
     */
    public static OAuth2ResourceOwnerPasswordFlowAuthenticator configureOAuth2ResourceOwnerPassword(
            ClientResource clientResource) {
        OAuth2ResourceOwnerPasswordFlowAuthenticator authenticator = new OAuth2ResourceOwnerPasswordFlowAuthenticator();
        authenticator.configure(clientResource);

        return authenticator;
    }

}
