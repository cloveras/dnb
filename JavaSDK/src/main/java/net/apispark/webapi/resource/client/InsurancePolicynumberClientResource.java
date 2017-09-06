package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsurancePolicynumberClientResource {

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
    public InsurancePolicynumberClientResource(net.apispark.webapi.Config config, java.lang.String policynumber) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.policynumber = policynumber;
        this.absolutePath = config.getBasePath() + "/insurance/{policynumber}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Insurance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Insurance getInsurancePolicynumber() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("policynumber", this.policynumber);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsurancePolicynumberResource.class).getInsurancePolicynumber();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postInsurancePolicynumber(net.apispark.webapi.representation.Insurance bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("policynumber", this.policynumber);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.InsurancePolicynumberResource.class).postInsurancePolicynumber(bean);
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteInsurancePolicynumber() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("policynumber", this.policynumber);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.InsurancePolicynumberResource.class).deleteInsurancePolicynumber();
    }

}
