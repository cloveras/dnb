package net.apispark.webapi.resource;

public interface AccountsAccountStatementYearMonthResource {

    /**
     * Account statement for specified account, year and month. Some meta data, and a list of all transactions. .
     *
     * @return  {@link net.apispark.webapi.representation.Accountstatement} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Accountstatement getAccountsAccountStatementYearMonth();

}