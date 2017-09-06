package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsurancePolicynumberClaimsClaimidClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String policynumber;

    private java.lang.String claimid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param policynumber
     *            Attribute "policynumber"
     * @param claimid
     *            Attribute "claimid"
     */
    public InsurancePolicynumberClaimsClaimidClientResource(net.apispark.webapi.Config config, java.lang.String policynumber, java.lang.String claimid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.policynumber = policynumber;
        this.claimid = claimid;
        this.absolutePath = config.getBasePath() + "/insurance/{policynumber}/claims/{claimid}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Insuranceclaim} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Insuranceclaim getInsurancePolicynumberClaimsClaimid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("policynumber", this.policynumber);
        client.setAttribute("claimid", this.claimid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsurancePolicynumberClaimsClaimidResource.class).getInsurancePolicynumberClaimsClaimid();
    }

}
