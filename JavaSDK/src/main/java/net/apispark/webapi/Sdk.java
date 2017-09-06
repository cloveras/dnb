package net.apispark.webapi;

/**
 * Entry-point for API calls.
 */
public class Sdk {

    private final net.apispark.webapi.Config config = new net.apispark.webapi.Config();

    /**
     * Returns the SDK configuration.
     */
    public net.apispark.webapi.Config getConfig() {
        return config;
    }

    public net.apispark.webapi.resource.client.ApiKeyClientResource apiKey() {
        return new net.apispark.webapi.resource.client.ApiKeyClientResource(config);
    }

    public net.apispark.webapi.resource.client.ApiKeyApikeyClientResource apiKeyApikey(java.lang.String apikey) {
        return new net.apispark.webapi.resource.client.ApiKeyApikeyClientResource(config, apikey);
    }

    public net.apispark.webapi.resource.client.ApiRatelimitClientResource apiRatelimit() {
        return new net.apispark.webapi.resource.client.ApiRatelimitClientResource(config);
    }

    public net.apispark.webapi.resource.client.ApiWhoamiClientResource apiWhoami() {
        return new net.apispark.webapi.resource.client.ApiWhoamiClientResource(config);
    }

    public net.apispark.webapi.resource.client.AreyouthereClientResource areyouthere() {
        return new net.apispark.webapi.resource.client.AreyouthereClientResource(config);
    }

    public net.apispark.webapi.resource.client.CustomerOrgnrScoreClientResource customerOrgnrScore(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.CustomerOrgnrScoreClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.CustomersOrgnrClientResource customersOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.CustomersOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.CustomersOrgnrAboutClientResource customersOrgnrAbout(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.CustomersOrgnrAboutClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.CustomersOrgnrAccountsClientResource customersOrgnrAccounts(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.CustomersOrgnrAccountsClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.CustomersOrgnrProfileClientResource customersOrgnrProfile(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.CustomersOrgnrProfileClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.AccountAccountClientResource accountAccount(java.lang.String account) {
        return new net.apispark.webapi.resource.client.AccountAccountClientResource(config, account);
    }

    public net.apispark.webapi.resource.client.AccountsAccountBalanceClientResource accountsAccountBalance(java.lang.String account) {
        return new net.apispark.webapi.resource.client.AccountsAccountBalanceClientResource(config, account);
    }

    public net.apispark.webapi.resource.client.AccountsAccountNameClientResource accountsAccountName(java.lang.String account) {
        return new net.apispark.webapi.resource.client.AccountsAccountNameClientResource(config, account);
    }

    public net.apispark.webapi.resource.client.AccountsAccountSafetospendClientResource accountsAccountSafetospend(java.lang.String account) {
        return new net.apispark.webapi.resource.client.AccountsAccountSafetospendClientResource(config, account);
    }

    public net.apispark.webapi.resource.client.AccountsAccountStatementYearMonthClientResource accountsAccountStatementYearMonth(java.lang.String account, java.lang.String year, java.lang.String month) {
        return new net.apispark.webapi.resource.client.AccountsAccountStatementYearMonthClientResource(config, account, year, month);
    }

    public net.apispark.webapi.resource.client.AccountsAccountTransactionlistClientResource accountsAccountTransactionlist(java.lang.String account) {
        return new net.apispark.webapi.resource.client.AccountsAccountTransactionlistClientResource(config, account);
    }

    public net.apispark.webapi.resource.client.AccountsAccountTransactionlistYearMonthClientResource accountsAccountTransactionlistYearMonth(java.lang.String account, java.lang.String year, java.lang.String month) {
        return new net.apispark.webapi.resource.client.AccountsAccountTransactionlistYearMonthClientResource(config, account, year, month);
    }

    public net.apispark.webapi.resource.client.DepositaccountClientResource depositaccount(java.lang.String ownerid, java.lang.String lenderid) {
        return new net.apispark.webapi.resource.client.DepositaccountClientResource(config, ownerid, lenderid);
    }

    public net.apispark.webapi.resource.client.TransactionsCategoryMccAccountClientResource transactionsCategoryMccAccount(java.lang.String mcc, java.lang.String account) {
        return new net.apispark.webapi.resource.client.TransactionsCategoryMccAccountClientResource(config, mcc, account);
    }

    public net.apispark.webapi.resource.client.TransactionsSepaClientResource transactionsSepa() {
        return new net.apispark.webapi.resource.client.TransactionsSepaClientResource(config);
    }

    public net.apispark.webapi.resource.client.TransactionsTransactionidClientResource transactionsTransactionid(java.lang.String transactionid) {
        return new net.apispark.webapi.resource.client.TransactionsTransactionidClientResource(config, transactionid);
    }

    public net.apispark.webapi.resource.client.LoansApplyClientResource loansApply() {
        return new net.apispark.webapi.resource.client.LoansApplyClientResource(config);
    }

    public net.apispark.webapi.resource.client.LoansDownpayLoanidClientResource loansDownpayLoanid(java.lang.String loanid) {
        return new net.apispark.webapi.resource.client.LoansDownpayLoanidClientResource(config, loanid);
    }

    public net.apispark.webapi.resource.client.LoansLoanidClientResource loansLoanid(java.lang.String loanid) {
        return new net.apispark.webapi.resource.client.LoansLoanidClientResource(config, loanid);
    }

    public net.apispark.webapi.resource.client.PreapprovalLoanidClientResource preapprovalLoanid(java.lang.String loanid) {
        return new net.apispark.webapi.resource.client.PreapprovalLoanidClientResource(config, loanid);
    }

    public net.apispark.webapi.resource.client.PreapprovalPersonnrDesiredamountClientResource preapprovalPersonnrDesiredamount(java.lang.String personnr, java.lang.String desiredamount) {
        return new net.apispark.webapi.resource.client.PreapprovalPersonnrDesiredamountClientResource(config, personnr, desiredamount);
    }

    public net.apispark.webapi.resource.client.DeletepreapprovalClientResource deletepreapproval(java.lang.String personnr) {
        return new net.apispark.webapi.resource.client.DeletepreapprovalClientResource(config, personnr);
    }

    public net.apispark.webapi.resource.client.CurrencyConvertForeigncurrencyCurrencyAmountClientResource currencyConvertForeigncurrencyCurrencyAmount(java.lang.String foreigncurrency, java.lang.String currency, java.lang.String amount) {
        return new net.apispark.webapi.resource.client.CurrencyConvertForeigncurrencyCurrencyAmountClientResource(config, foreigncurrency, currency, amount);
    }

    public net.apispark.webapi.resource.client.CurrencyListCurrencyClientResource currencyListCurrency(java.lang.String currency) {
        return new net.apispark.webapi.resource.client.CurrencyListCurrencyClientResource(config, currency);
    }

    public net.apispark.webapi.resource.client.CurrencyRateCurrencyClientResource currencyRateCurrency(java.lang.String currency) {
        return new net.apispark.webapi.resource.client.CurrencyRateCurrencyClientResource(config, currency);
    }

    public net.apispark.webapi.resource.client.LocationBranchFindbyaddressClientResource locationBranchFindbyaddress() {
        return new net.apispark.webapi.resource.client.LocationBranchFindbyaddressClientResource(config);
    }

    public net.apispark.webapi.resource.client.LocationBranchBranchidDetailsClientResource locationBranchBranchidDetails(java.lang.String branchid) {
        return new net.apispark.webapi.resource.client.LocationBranchBranchidDetailsClientResource(config, branchid);
    }

    public net.apispark.webapi.resource.client.LocationBranchBranchidOpeninghoursClientResource locationBranchBranchidOpeninghours(java.lang.String branchid) {
        return new net.apispark.webapi.resource.client.LocationBranchBranchidOpeninghoursClientResource(config, branchid);
    }

    public net.apispark.webapi.resource.client.LocationBranchLatitudeLongditudeClientResource locationBranchLatitudeLongditude(java.lang.String latitude, java.lang.String longditude) {
        return new net.apispark.webapi.resource.client.LocationBranchLatitudeLongditudeClientResource(config, latitude, longditude);
    }

    public net.apispark.webapi.resource.client.LocationAtmLatitudeLongditudeClientResource locationAtmLatitudeLongditude(java.lang.String latitude, java.lang.String longditude) {
        return new net.apispark.webapi.resource.client.LocationAtmLatitudeLongditudeClientResource(config, latitude, longditude);
    }

    public net.apispark.webapi.resource.client.InsuranceCarOfferLicensenumberClientResource insuranceCarOfferLicensenumber(java.lang.String licensenumber) {
        return new net.apispark.webapi.resource.client.InsuranceCarOfferLicensenumberClientResource(config, licensenumber);
    }

    public net.apispark.webapi.resource.client.InsuranceCarServiceLatitudeLongditudeClientResource insuranceCarServiceLatitudeLongditude(java.lang.String latitude, java.lang.String longditude) {
        return new net.apispark.webapi.resource.client.InsuranceCarServiceLatitudeLongditudeClientResource(config, latitude, longditude);
    }

    public net.apispark.webapi.resource.client.InsuranceCarTowingserviceLatitudeLongditudeClientResource insuranceCarTowingserviceLatitudeLongditude(java.lang.String latitude, java.lang.String longditude) {
        return new net.apispark.webapi.resource.client.InsuranceCarTowingserviceLatitudeLongditudeClientResource(config, latitude, longditude);
    }

    public net.apispark.webapi.resource.client.InsuranceCarLicensenumberClientResource insuranceCarLicensenumber(java.lang.String licensenumber) {
        return new net.apispark.webapi.resource.client.InsuranceCarLicensenumberClientResource(config, licensenumber);
    }

    public net.apispark.webapi.resource.client.InsuranceClaimClientResource insuranceClaim() {
        return new net.apispark.webapi.resource.client.InsuranceClaimClientResource(config);
    }

    public net.apispark.webapi.resource.client.InsuranceWeatheralertLatitudeLongditudeClientResource insuranceWeatheralertLatitudeLongditude(java.lang.String latitude, java.lang.String longditude) {
        return new net.apispark.webapi.resource.client.InsuranceWeatheralertLatitudeLongditudeClientResource(config, latitude, longditude);
    }

    public net.apispark.webapi.resource.client.InsurancePolicynumberClientResource insurancePolicynumber(java.lang.String policynumber) {
        return new net.apispark.webapi.resource.client.InsurancePolicynumberClientResource(config, policynumber);
    }

    public net.apispark.webapi.resource.client.InsurancePolicynumberClaimsClientResource insurancePolicynumberClaims(java.lang.String policynumber) {
        return new net.apispark.webapi.resource.client.InsurancePolicynumberClaimsClientResource(config, policynumber);
    }

    public net.apispark.webapi.resource.client.InsurancePolicynumberClaimsClaimidClientResource insurancePolicynumberClaimsClaimid(java.lang.String policynumber, java.lang.String claimid) {
        return new net.apispark.webapi.resource.client.InsurancePolicynumberClaimsClaimidClientResource(config, policynumber, claimid);
    }

    public net.apispark.webapi.resource.client.StocksPriceTickerTimestampClientResource stocksPriceTickerTimestamp(java.lang.String ticker, java.lang.String timestamp) {
        return new net.apispark.webapi.resource.client.StocksPriceTickerTimestampClientResource(config, ticker, timestamp);
    }

    public net.apispark.webapi.resource.client.StocksTickerClientResource stocksTicker(java.lang.String ticker) {
        return new net.apispark.webapi.resource.client.StocksTickerClientResource(config, ticker);
    }

    public net.apispark.webapi.resource.client.AppRootClientResource appRoot() {
        return new net.apispark.webapi.resource.client.AppRootClientResource(config);
    }

    public net.apispark.webapi.resource.client.PaymentsOrderIdSerialNumberMerchantSerialNumberDetailsClientResource paymentsOrderIdSerialNumberMerchantSerialNumberDetails(java.lang.String orderId, java.lang.String merchantSerialNumber) {
        return new net.apispark.webapi.resource.client.PaymentsOrderIdSerialNumberMerchantSerialNumberDetailsClientResource(config, orderId, merchantSerialNumber);
    }

    public net.apispark.webapi.resource.client.VippsCustomerClientResource vippsCustomer() {
        return new net.apispark.webapi.resource.client.VippsCustomerClientResource(config);
    }

    public net.apispark.webapi.resource.client.VippsInvoiceOrgnrClientResource vippsInvoiceOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.VippsInvoiceOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.VippsMerchantsLatitudeLongditudeClientResource vippsMerchantsLatitudeLongditude(java.lang.String latitude, java.lang.String longditude) {
        return new net.apispark.webapi.resource.client.VippsMerchantsLatitudeLongditudeClientResource(config, latitude, longditude);
    }

    public net.apispark.webapi.resource.client.VippsPaymentsClientResource vippsPayments() {
        return new net.apispark.webapi.resource.client.VippsPaymentsClientResource(config);
    }

    public net.apispark.webapi.resource.client.VippsSettlementCreateClientResource vippsSettlementCreate() {
        return new net.apispark.webapi.resource.client.VippsSettlementCreateClientResource(config);
    }

    public net.apispark.webapi.resource.client.VippsSettlementSettlementidClientResource vippsSettlementSettlementid(java.lang.String settlementid) {
        return new net.apispark.webapi.resource.client.VippsSettlementSettlementidClientResource(config, settlementid);
    }

    public net.apispark.webapi.resource.client.VippsSettlementSettlementidExpenseClientResource vippsSettlementSettlementidExpense(java.lang.String settlementid) {
        return new net.apispark.webapi.resource.client.VippsSettlementSettlementidExpenseClientResource(config, settlementid);
    }

    public net.apispark.webapi.resource.client.VippsSettlementSettlementidSettleClientResource vippsSettlementSettlementidSettle(java.lang.String settlementid) {
        return new net.apispark.webapi.resource.client.VippsSettlementSettlementidSettleClientResource(config, settlementid);
    }

    public net.apispark.webapi.resource.client.VippsSettlementSettlementidUserClientResource vippsSettlementSettlementidUser(java.lang.String settlementid) {
        return new net.apispark.webapi.resource.client.VippsSettlementSettlementidUserClientResource(config, settlementid);
    }

    public net.apispark.webapi.resource.client.VippsSettlementSettlementidUserUseridClientResource vippsSettlementSettlementidUserUserid(java.lang.String settlementid, java.lang.String userid) {
        return new net.apispark.webapi.resource.client.VippsSettlementSettlementidUserUseridClientResource(config, settlementid, userid);
    }

    public net.apispark.webapi.resource.client.SaftOrgnrClientResource saftOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.SaftOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.SaftOrgnrTypeClientResource saftOrgnrType(java.lang.String orgnr, java.lang.String type) {
        return new net.apispark.webapi.resource.client.SaftOrgnrTypeClientResource(config, orgnr, type);
    }

    public net.apispark.webapi.resource.client.ReportsAccountsOrgnrClientResource reportsAccountsOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsAccountsOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsAdvicesYearMonthOrgnrClientResource reportsAdvicesYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsAdvicesYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsAnnualYearOrgnrClientResource reportsAnnualYearOrgnr(java.lang.String year, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsAnnualYearOrgnrClientResource(config, year, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsBehaviourOrgnrClientResource reportsBehaviourOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsBehaviourOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compareClientResource reportsBenchmarksYearMonthOrgnrComparetoOrgnr_compare(java.lang.String year, java.lang.String month, java.lang.String orgnr, java.lang.String orgnr_compare) {
        return new net.apispark.webapi.resource.client.ReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compareClientResource(config, year, month, orgnr, orgnr_compare);
    }

    public net.apispark.webapi.resource.client.ReportsCalendarsYearMonthOrgnrClientResource reportsCalendarsYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsCalendarsYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsChatsYearMonthOrgnrClientResource reportsChatsYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsChatsYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsExpensesYearMonthOrgnrClientResource reportsExpensesYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsExpensesYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsInvoicesOrgnrClientResource reportsInvoicesOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsInvoicesOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsLiquidityYearmonthOrgnrClientResource reportsLiquidityYearmonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsLiquidityYearmonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsLiquidityprognosisYearMonthOrgnrClientResource reportsLiquidityprognosisYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsLiquidityprognosisYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsLiquiditystatusYearMonthOrgnrClientResource reportsLiquiditystatusYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsLiquiditystatusYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsLoanpaymentsYearMonthOrgnrClientResource reportsLoanpaymentsYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsLoanpaymentsYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsNotificationsYearMonthOrgnrClientResource reportsNotificationsYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsNotificationsYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsOrganizationOrgnrClientResource reportsOrganizationOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsOrganizationOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsProductsOrgnrClientResource reportsProductsOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsProductsOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsProfitabilityYearMonthOrgnrClientResource reportsProfitabilityYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsProfitabilityYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsRevenuesYearMonthOrgnrClientResource reportsRevenuesYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsRevenuesYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsRolesYearOrgnrClientResource reportsRolesYearOrgnr(java.lang.String year, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsRolesYearOrgnrClientResource(config, year, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsSolidityOrgnrClientResource reportsSolidityOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsSolidityOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsSystemOrgnrClientResource reportsSystemOrgnr(java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsSystemOrgnrClientResource(config, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsTransactionsYearMonthOrgnrClientResource reportsTransactionsYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsTransactionsYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

    public net.apispark.webapi.resource.client.ReportsTurnoversYearMonthOrgnrClientResource reportsTurnoversYearMonthOrgnr(java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        return new net.apispark.webapi.resource.client.ReportsTurnoversYearMonthOrgnrClientResource(config, year, month, orgnr);
    }

}
