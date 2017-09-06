package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class TransactionsCategoryMccAccountClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String mcc;

    private java.lang.String account;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param mcc
     *            MCC: Merchant Category Code: https://en.wikipedia.org/wiki/Merchant_category_code
     * @param account
     *            Account number, 11 digits.
     */
    public TransactionsCategoryMccAccountClientResource(net.apispark.webapi.Config config, java.lang.String mcc, java.lang.String account) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.mcc = mcc;
        this.account = account;
        this.absolutePath = config.getBasePath() + "/transactions/category/{mcc}/{account}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.TransactionList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.TransactionList getTransactionsCategoryMccAccount() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("mcc", this.mcc);
        client.setAttribute("account", this.account);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.TransactionsCategoryMccAccountResource.class).getTransactionsCategoryMccAccount();
    }

}
