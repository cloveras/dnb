package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compareClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String year;

    private java.lang.String month;

    private java.lang.String orgnr;

    private java.lang.String orgnr_compare;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param year
     *            Year, 4 digits. Example: 2017
     * @param month
     *            Month, 2 digits (January is 01). Example: 01
     * @param orgnr
     *            Organization number, 9 digits. Example: 123456789
     * @param orgnr_compare
     *            Organization number of company to to compare with, 9 digits. Example: 123456789
     */
    public ReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compareClientResource(net.apispark.webapi.Config config, java.lang.String year, java.lang.String month, java.lang.String orgnr, java.lang.String orgnr_compare) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.year = year;
        this.month = month;
        this.orgnr = orgnr;
        this.orgnr_compare = orgnr_compare;
        this.absolutePath = config.getBasePath() + "/reports/benchmarks/{year}/{month}/{orgnr}/compareto/{orgnr_compare}";
    }

    /**
     * 
     * 
     * @param type
     *            What to compare: revenue (default=, profitability, liquidity
     * @return {@link net.apispark.webapi.representation.Reportplaceholder} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Reportplaceholder getReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compare(java.lang.String type) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "?type=", type);
        client.setAttribute("year", this.year);
        client.setAttribute("month", this.month);
        client.setAttribute("orgnr", this.orgnr);
        client.setAttribute("orgnr_compare", this.orgnr_compare);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compareResource.class).getReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compare();
    }

}
