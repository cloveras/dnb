package net.apispark.webapi.resource;

public interface AccountsAccountBalanceResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Balance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Balance getAccountsAccountBalance();

}