package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ReportsRolesYearOrgnrClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String year;

    private java.lang.String orgnr;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param year
     *            Year, 4 digits. Example: 2017
     * @param orgnr
     *            Organization number, 9 digits. Example: 123456789
     */
    public ReportsRolesYearOrgnrClientResource(net.apispark.webapi.Config config, java.lang.String year, java.lang.String orgnr) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.year = year;
        this.orgnr = orgnr;
        this.absolutePath = config.getBasePath() + "/reports/roles/{year}/{orgnr}";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getReportsRolesYearOrgnr() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("year", this.year);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.ReportsRolesYearOrgnrResource.class).getReportsRolesYearOrgnr();
    }

}
