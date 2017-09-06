package net.apispark.webapi.resource;

public interface ApiKeyResource {

    /**
     * Simplified example. Requires a method of identification, the minimum being Facebook or Google OAuth. .
     *
     * @return  {@link java.lang.String} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    java.lang.String getApiKey();

}