package net.apispark.webapi.resource;

public interface SaftOrgnrTypeResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.SAFTfile} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.SAFTfile getSaftOrgnrType();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postSaftOrgnrType(net.apispark.webapi.representation.SAFTfile bean);

}