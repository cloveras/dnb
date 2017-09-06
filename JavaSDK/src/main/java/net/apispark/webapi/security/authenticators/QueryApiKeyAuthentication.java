package net.apispark.webapi.security.authenticators;

import org.restlet.resource.ClientResource;

/**
 * Configures authentication based on the value of a specific query parameter.
 */
public class QueryApiKeyAuthentication implements Authenticator {
    /** The name of the authentication query parameter. */
    private final String queryParameterName;
    
    /** The value of the query parameter. */
    private final String token;

    /**
     * Constructor.
     * 
     * @param queryParameterName
     *            The name of the authentication query parameter.
     * @param token
     *            The value of the query parameter.
     */
    public QueryApiKeyAuthentication(String queryParameterName, String token) {
        this.queryParameterName = queryParameterName;
        this.token = token;
    }

    @Override
    public void configure(ClientResource clientResource) {
        clientResource.setQueryValue(queryParameterName, token);
    }

}
