package net.apispark.webapi.resource;

public interface CustomersOrgnrAccountsResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.AccountList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.AccountList getCustomersOrgnrAccounts();

}