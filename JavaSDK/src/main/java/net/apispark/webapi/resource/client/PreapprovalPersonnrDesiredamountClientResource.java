package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class PreapprovalPersonnrDesiredamountClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String personnr;

    private java.lang.String desiredamount;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param personnr
     *            11-digit personal id number
     * @param desiredamount
     *            How much?
     */
    public PreapprovalPersonnrDesiredamountClientResource(net.apispark.webapi.Config config, java.lang.String personnr, java.lang.String desiredamount) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.personnr = personnr;
        this.desiredamount = desiredamount;
        this.absolutePath = config.getBasePath() + "/preapproval/{personnr}/{desiredamount}";
    }

    /**
     * Optional parameters: County (kommunenummer), gnr (gårdsnummer) and bnr (bruksnummer ) may be used to specify which property the preapproval is being checked for. This may allow DNB to find out that the preapproval holder is in a bidding process.
     * 
     * @param county
     *            Number of the county (kommune).
     * @param gnr
     *            Gårdsnummer.
     * @param bnr
     *            Bruksnummer.
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getPreapprovalPersonnrDesiredamount(java.lang.String county, java.lang.String gnr, java.lang.String bnr) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "county", county);
        QueryParameterHelper.addQueryParameter(client, "gnr", gnr);
        QueryParameterHelper.addQueryParameter(client, "bnr", bnr);
        client.setAttribute("personnr", this.personnr);
        client.setAttribute("desiredamount", this.desiredamount);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.PreapprovalPersonnrDesiredamountResource.class).getPreapprovalPersonnrDesiredamount();
    }

    /**
     * For now apply for a specified amount only. If the amouhnt specified is 0 (zero), the application will be treated as for the maximum possible amount.
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postPreapprovalPersonnrDesiredamount() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("personnr", this.personnr);
        client.setAttribute("desiredamount", this.desiredamount);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.PreapprovalPersonnrDesiredamountResource.class).postPreapprovalPersonnrDesiredamount();
    }

}
