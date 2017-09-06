package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountsAccountTransactionlistYearMonthClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String account;

    private java.lang.String year;

    private java.lang.String month;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param account
     *            Account number, 11 digits. Example: 12345678910
     * @param year
     *            Year, 4 digits. Example: 2017
     * @param month
     *            Month, 2 digits (January is 01). Example: 01
     */
    public AccountsAccountTransactionlistYearMonthClientResource(net.apispark.webapi.Config config, java.lang.String account, java.lang.String year, java.lang.String month) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.year = year;
        this.month = month;
        this.absolutePath = config.getBasePath() + "/accounts/{account}/transactionlist/{year}/{month}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.TransactionList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.TransactionList getAccountsAccountTransactionlistYearMonth() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        client.setAttribute("year", this.year);
        client.setAttribute("month", this.month);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.AccountsAccountTransactionlistYearMonthResource.class).getAccountsAccountTransactionlistYearMonth();
    }

}
