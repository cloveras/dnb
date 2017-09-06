package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountsAccountSafetospendClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String account;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param account
     *            Account number, 11 digits.
     */
    public AccountsAccountSafetospendClientResource(net.apispark.webapi.Config config, java.lang.String account) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.absolutePath = config.getBasePath() + "/accounts/{account}/safetospend";
    }

    /**
     * The current balance, minus known outgoing transactions, plus known incoming transactions. .
     * 
     * @return {@link java.lang.Integer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public java.lang.Integer getAccountsAccountSafetospend() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.AccountsAccountSafetospendResource.class).getAccountsAccountSafetospend();
    }

}
