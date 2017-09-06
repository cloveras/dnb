package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountAccountClientResource {

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
    public AccountAccountClientResource(net.apispark.webapi.Config config, java.lang.String account) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.absolutePath = config.getBasePath() + "/account/{account}";
    }

    /**
     * Maybe add query parameters for filtering? '?type=stocks', etc.
     * 
     * @return {@link net.apispark.webapi.representation.Account} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Account getAccountAccount() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.AccountAccountResource.class).getAccountAccount();
    }

    /**
     * Maybe send partial Account object (without account number) in request?.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postAccountAccount(net.apispark.webapi.representation.Account bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.AccountAccountResource.class).postAccountAccount(bean);
    }

    /**
     * Maybe return HTTP 2XX 'marked for deletion', as the account will not be deleted immediately?.
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteAccountAccount() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.AccountAccountResource.class).deleteAccountAccount();
    }

}
