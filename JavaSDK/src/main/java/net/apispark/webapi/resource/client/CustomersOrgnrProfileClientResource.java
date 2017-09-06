package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class CustomersOrgnrProfileClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String orgnr;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param orgnr
     *            Attribute "orgnr"
     */
    public CustomersOrgnrProfileClientResource(net.apispark.webapi.Config config, java.lang.String orgnr) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.orgnr = orgnr;
        this.absolutePath = config.getBasePath() + "/customers/{orgnr}/profile";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getCustomersOrgnrProfile() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.CustomersOrgnrProfileResource.class).getCustomersOrgnrProfile();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postCustomersOrgnrProfile() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.CustomersOrgnrProfileResource.class).postCustomersOrgnrProfile();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteCustomersOrgnrProfile() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.CustomersOrgnrProfileResource.class).deleteCustomersOrgnrProfile();
    }

}
