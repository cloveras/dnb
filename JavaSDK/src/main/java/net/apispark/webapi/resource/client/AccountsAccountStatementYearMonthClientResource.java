package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AccountsAccountStatementYearMonthClientResource {

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
     *            Attribute "account"
     * @param year
     *            Attribute "year"
     * @param month
     *            Attribute "month"
     */
    public AccountsAccountStatementYearMonthClientResource(net.apispark.webapi.Config config, java.lang.String account, java.lang.String year, java.lang.String month) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.account = account;
        this.year = year;
        this.month = month;
        this.absolutePath = config.getBasePath() + "/accounts/{account}/statement/{year}/{month}";
    }

    /**
     * Account statement for specified account, year and month. Some meta data, and a list of all transactions. .
     * 
     * @return {@link net.apispark.webapi.representation.Accountstatement} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Accountstatement getAccountsAccountStatementYearMonth() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("account", this.account);
        client.setAttribute("year", this.year);
        client.setAttribute("month", this.month);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.AccountsAccountStatementYearMonthResource.class).getAccountsAccountStatementYearMonth();
    }

}
