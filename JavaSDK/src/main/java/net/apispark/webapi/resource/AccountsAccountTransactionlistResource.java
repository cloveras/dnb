package net.apispark.webapi.resource;

public interface AccountsAccountTransactionlistResource {

    /**
     * Supports pagination: pagesize and pagenumber,.
     *
     * @return  {@link net.apispark.webapi.representation.TransactionList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.TransactionList getAccountsAccountTransactionlist();

}