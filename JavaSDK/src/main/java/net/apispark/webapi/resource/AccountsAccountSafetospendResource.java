package net.apispark.webapi.resource;

public interface AccountsAccountSafetospendResource {

    /**
     * The current balance, minus known outgoing transactions, plus known incoming transactions. .
     *
     * @return  {@link java.lang.Integer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    java.lang.Integer getAccountsAccountSafetospend();

}