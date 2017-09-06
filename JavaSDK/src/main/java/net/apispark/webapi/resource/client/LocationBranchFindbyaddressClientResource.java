package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class LocationBranchFindbyaddressClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public LocationBranchFindbyaddressClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/location/branch/findbyaddress";
    }

    /**
     * 
     * 
     * @param address
     *            For now: Assume geocoding functionality similar to Google Maps: https://developers.google.com/maps/documentation/geocoding/intro
     * @return {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Location getLocationBranchFindbyaddress(java.lang.String address) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "address", address);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.LocationBranchFindbyaddressResource.class).getLocationBranchFindbyaddress();
    }

}
