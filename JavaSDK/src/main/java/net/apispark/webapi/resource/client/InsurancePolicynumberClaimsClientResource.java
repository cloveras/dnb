package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsurancePolicynumberClaimsClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String policynumber;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param policynumber
     *            Attribute "policynumber"
     */
    public InsurancePolicynumberClaimsClientResource(net.apispark.webapi.Config config, java.lang.String policynumber) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.policynumber = policynumber;
        this.absolutePath = config.getBasePath() + "/insurance/{policynumber}/claims";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.InsuranceclaimList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.InsuranceclaimList getInsurancePolicynumberClaims() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("policynumber", this.policynumber);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsurancePolicynumberClaimsResource.class).getInsurancePolicynumberClaims();
    }

}
