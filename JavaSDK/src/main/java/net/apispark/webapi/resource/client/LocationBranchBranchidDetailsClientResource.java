package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class LocationBranchBranchidDetailsClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String branchid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param branchid
     *            Id of branch
     */
    public LocationBranchBranchidDetailsClientResource(net.apispark.webapi.Config config, java.lang.String branchid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.branchid = branchid;
        this.absolutePath = config.getBasePath() + "/location/branch/{branchid}/details";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.StringList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.StringList getLocationBranchBranchidDetails() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("branchid", this.branchid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.LocationBranchBranchidDetailsResource.class).getLocationBranchBranchidDetails();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postLocationBranchBranchidDetails() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("branchid", this.branchid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.LocationBranchBranchidDetailsResource.class).postLocationBranchBranchidDetails();
    }

}
