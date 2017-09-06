package net.apispark.webapi.resource;

public interface AccountAccountResource {

    /**
     * Maybe add query parameters for filtering? '?type=stocks', etc.
     *
     * @return  {@link net.apispark.webapi.representation.Account} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Account getAccountAccount();

    /**
     * Maybe send partial Account object (without account number) in request?.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postAccountAccount(net.apispark.webapi.representation.Account bean);

    /**
     * Maybe return HTTP 2XX 'marked for deletion', as the account will not be deleted immediately?.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteAccountAccount();

}