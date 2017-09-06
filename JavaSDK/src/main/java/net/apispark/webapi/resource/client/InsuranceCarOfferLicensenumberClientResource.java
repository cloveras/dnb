package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsuranceCarOfferLicensenumberClientResource {

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
    public InsuranceCarOfferLicensenumberClientResource(net.apispark.webapi.Config config, java.lang.String licensenumber) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.licensenumber = licensenumber;
        this.absolutePath = config.getBasePath() + "/insurance/car/offer/{licensenumber}";
    }

    /**
     * Needs a lot of input. https://www.dnb.no/privat/forsikring/bilforsikring.html

For now: Assume the attributes of Car object are sufficient.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.Insuranceoffer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Insuranceoffer postInsuranceCarOfferLicensenumber(net.apispark.webapi.representation.Car bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("licensenumber", this.licensenumber);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsuranceCarOfferLicensenumberResource.class).postInsuranceCarOfferLicensenumber(bean);
    }

}
