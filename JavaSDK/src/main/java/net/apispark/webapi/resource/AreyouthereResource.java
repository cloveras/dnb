package net.apispark.webapi.resource;

public interface AreyouthereResource {

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getApiPing();

}