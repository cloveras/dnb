package net.apispark.webapi.resource;

public interface ApiKeyApikeyResource {

    /**
     * For now: Also returns some housekeeping details, as an example. .
     *
     * @return  {@link net.apispark.webapi.representation.AnonymousRepresentation} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.AnonymousRepresentation getApiKeyApikey();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteApiKeyApikey();

}