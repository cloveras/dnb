package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ApiKeyApikeyClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String apikey;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param apikey
     *            Example: fa01s-21res-qq21p-mq1p6
     */
    public ApiKeyApikeyClientResource(net.apispark.webapi.Config config, java.lang.String apikey) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.apikey = apikey;
        this.absolutePath = config.getBasePath() + "/api/key/{apikey}";
    }

    /**
     * For now: Also returns some housekeeping details, as an example. .
     * 
     * @return {@link net.apispark.webapi.representation.AnonymousRepresentation} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.AnonymousRepresentation getApiKeyApikey() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("apikey", this.apikey);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiKeyApikeyResource.class).getApiKeyApikey();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteApiKeyApikey() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("apikey", this.apikey);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.ApiKeyApikeyResource.class).deleteApiKeyApikey();
    }

}
