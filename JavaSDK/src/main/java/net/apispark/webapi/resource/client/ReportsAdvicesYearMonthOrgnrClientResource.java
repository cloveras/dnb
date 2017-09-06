package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ReportsAdvicesYearMonthOrgnrClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String year;

    private java.lang.String month;

    private java.lang.String orgnr;

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
     */
    public ReportsAdvicesYearMonthOrgnrClientResource(net.apispark.webapi.Config config, java.lang.String year, java.lang.String month, java.lang.String orgnr) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.year = year;
        this.month = month;
        this.orgnr = orgnr;
        this.absolutePath = config.getBasePath() + "/reports/advices/{year}/{month}/{orgnr}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.AdviceList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.AdviceList getReportsAdvicesYearMonthOrgnr() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("year", this.year);
        client.setAttribute("month", this.month);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ReportsAdvicesYearMonthOrgnrResource.class).getReportsAdvicesYearMonthOrgnr();
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteReportsAdvicesYearMonthOrgnr() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("year", this.year);
        client.setAttribute("month", this.month);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.ReportsAdvicesYearMonthOrgnrResource.class).deleteReportsAdvicesYearMonthOrgnr();
    }

}
