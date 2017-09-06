package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class LocationBranchLatitudeLongditudeClientResource {

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
    public LocationBranchLatitudeLongditudeClientResource(net.apispark.webapi.Config config, java.lang.String latitude, java.lang.String longditude) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.latitude = latitude;
        this.longditude = longditude;
        this.absolutePath = config.getBasePath() + "/location/branch/{latitude}/{longditude}/";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getLocationBranchLatitudeLongditude() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("latitude", this.latitude);
        client.setAttribute("longditude", this.longditude);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.LocationBranchLatitudeLongditudeResource.class).getLocationBranchLatitudeLongditude();
    }

}
