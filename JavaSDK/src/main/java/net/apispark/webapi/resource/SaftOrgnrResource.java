package net.apispark.webapi.resource;

public interface SaftOrgnrResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.SAFTfile} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.SAFTfile getSaftOrgnr();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postSaftOrgnr(net.apispark.webapi.representation.SAFTfile bean);

}