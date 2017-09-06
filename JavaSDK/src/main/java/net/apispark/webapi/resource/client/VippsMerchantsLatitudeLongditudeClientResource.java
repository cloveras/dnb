package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class VippsMerchantsLatitudeLongditudeClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String latitude;

    private java.lang.String longditude;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param latitude
     *            Current latitude. Example: 37.4238253802915
     * @param longditude
     *            Current longditude. Example: -122.0842499
     */
    public VippsMerchantsLatitudeLongditudeClientResource(net.apispark.webapi.Config config, java.lang.String latitude, java.lang.String longditude) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.latitude = latitude;
        this.longditude = longditude;
        this.absolutePath = config.getBasePath() + "/vipps/merchants/{latitude}/{longditude}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.CustomerList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.CustomerList getVippsMerchantsLatitudeLongditude() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("latitude", this.latitude);
        client.setAttribute("longditude", this.longditude);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.VippsMerchantsLatitudeLongditudeResource.class).getVippsMerchantsLatitudeLongditude();
    }

}
