package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class InsuranceWeatheralertLatitudeLongditudeClientResource {

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
     *            Attribute "latitude"
     * @param longditude
     *            Attribute "longditude"
     */
    public InsuranceWeatheralertLatitudeLongditudeClientResource(net.apispark.webapi.Config config, java.lang.String latitude, java.lang.String longditude) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.latitude = latitude;
        this.longditude = longditude;
        this.absolutePath = config.getBasePath() + "/insurance/weatheralert/{latitude}/{longditude}";
    }

    /**
     * 
     * 
     * @param radius
     *            Radius in km.
     * @return {@link net.apispark.webapi.representation.StringList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.StringList getInsuranceWeatheralertLatitudeLongditude(java.lang.String radius) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "radius", radius);
        client.setAttribute("latitude", this.latitude);
        client.setAttribute("longditude", this.longditude);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.InsuranceWeatheralertLatitudeLongditudeResource.class).getInsuranceWeatheralertLatitudeLongditude();
    }

}
