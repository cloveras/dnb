package net.apispark.webapi.resource;

public interface ApiWhoamiResource {

    /**
     * All details of current API user account:
* Contact details
* API key details
* Payment/plan/subscription
* Throttling info
* etc.
     *
     * @return  {@link net.apispark.webapi.representation.StringList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.StringList getApiWhoami();

}