package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountsAccountBalanceClientResource {

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
    public AccountsAccountBalanceClientResource(net.apispark.webapi.Config config, java.lang.String account) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.absolutePath = config.getBasePath() + "/accounts/{account}/balance";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Balance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Balance getAccountsAccountBalance() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.AccountsAccountBalanceResource.class).getAccountsAccountBalance();
    }

}
