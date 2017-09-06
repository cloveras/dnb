package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsuranceCarLicensenumberClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String licensenumber;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param licensenumber
     *            AB12345
     */
    public InsuranceCarLicensenumberClientResource(net.apispark.webapi.Config config, java.lang.String licensenumber) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.licensenumber = licensenumber;
        this.absolutePath = config.getBasePath() + "/insurance/car/{licensenumber}";
    }

    /**
     * Check if a car is insured: /insurance/car/AB12345.
     * 
     * @return {@link net.apispark.webapi.representation.Insurance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Insurance getInsuranceCarLicensenumber() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("licensenumber", this.licensenumber);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsuranceCarLicensenumberResource.class).getInsuranceCarLicensenumber();
    }

    /**
     * Needs a lot of input. https://www.dnb.no/privat/forsikring/bilforsikring.html.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.Insurance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Insurance postInsuranceCarLicensenumber(net.apispark.webapi.representation.StringList bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("licensenumber", this.licensenumber);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsuranceCarLicensenumberResource.class).postInsuranceCarLicensenumber(bean);
    }

}
