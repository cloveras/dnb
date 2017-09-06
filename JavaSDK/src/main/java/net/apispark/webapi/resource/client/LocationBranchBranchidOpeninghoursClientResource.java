package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class LocationBranchBranchidOpeninghoursClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String branchid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param branchid
     *            Id of a branch
     */
    public LocationBranchBranchidOpeninghoursClientResource(net.apispark.webapi.Config config, java.lang.String branchid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.branchid = branchid;
        this.absolutePath = config.getBasePath() + "/location/branch/{branchid}/openinghours";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.AnonymousRepresentation2List} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.AnonymousRepresentation2List getLocationBranchBranchidOpeninghours() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("branchid", this.branchid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.LocationBranchBranchidOpeninghoursResource.class).getLocationBranchBranchidOpeninghours();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postLocationBranchBranchidOpeninghours() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("branchid", this.branchid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.LocationBranchBranchidOpeninghoursResource.class).postLocationBranchBranchidOpeninghours();
    }

}
