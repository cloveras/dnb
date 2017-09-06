package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountsAccountTransactionlistClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String account;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param account
     *            Attribute "account"
     */
    public AccountsAccountTransactionlistClientResource(net.apispark.webapi.Config config, java.lang.String account) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.absolutePath = config.getBasePath() + "/accounts/{account}/transactionlist";
    }

    /**
     * Supports pagination: pagesize and pagenumber,.
     * 
     * @param pagesize
     *            Parameter "pagesize"
     * @param pagenumber
     *            Parameter "pagenumber"
     * @return {@link net.apispark.webapi.representation.TransactionList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.TransactionList getAccountsAccountTransactionlist(java.lang.Integer pagesize, java.lang.Integer pagenumber) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "pagesize", pagesize);
        QueryParameterHelper.addQueryParameter(client, "pagenumber", pagenumber);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.AccountsAccountTransactionlistResource.class).getAccountsAccountTransactionlist();
    }

}
