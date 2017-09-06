package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ReportsAccountsOrgnrClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String orgnr;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param orgnr
     *            Organization number, 9 digits. Example: 123456789
     */
    public ReportsAccountsOrgnrClientResource(net.apispark.webapi.Config config, java.lang.String orgnr) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.orgnr = orgnr;
        this.absolutePath = config.getBasePath() + "/reports/accounts/{orgnr}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Reportplaceholder} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Reportplaceholder getReportsAccountsOrgnr() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ReportsAccountsOrgnrResource.class).getReportsAccountsOrgnr();
    }

}
