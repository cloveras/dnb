package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountsAccountNameClientResource {

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
    public AccountsAccountNameClientResource(net.apispark.webapi.Config config, java.lang.String account) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.absolutePath = config.getBasePath() + "/accounts/{account}/name";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getAccountsAccountName() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.AccountsAccountNameResource.class).getAccountsAccountName();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postAccountsAccountName() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.AccountsAccountNameResource.class).postAccountsAccountName();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteAccountsAccountName() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.AccountsAccountNameResource.class).deleteAccountsAccountName();
    }

}
