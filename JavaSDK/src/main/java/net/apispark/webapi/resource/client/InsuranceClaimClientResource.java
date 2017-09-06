package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsuranceClaimClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public InsuranceClaimClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/insurance/claim";
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postInsuranceClaim(net.apispark.webapi.representation.Insuranceclaim bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.InsuranceClaimResource.class).postInsuranceClaim(bean);
    }

}
