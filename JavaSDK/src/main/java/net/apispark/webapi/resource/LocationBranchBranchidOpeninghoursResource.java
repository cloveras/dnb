package net.apispark.webapi.resource;

public interface LocationBranchBranchidOpeninghoursResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.AnonymousRepresentation2List} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.AnonymousRepresentation2List getLocationBranchBranchidOpeninghours();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postLocationBranchBranchidOpeninghours();

}