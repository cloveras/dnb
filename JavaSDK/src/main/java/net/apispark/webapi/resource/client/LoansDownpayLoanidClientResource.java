package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class LoansDownpayLoanidClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String loanid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param loanid
     *            The loan to downpay.
     */
    public LoansDownpayLoanidClientResource(net.apispark.webapi.Config config, java.lang.String loanid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.loanid = loanid;
        this.absolutePath = config.getBasePath() + "/loans/downpay/{loanid}";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postLoansDownpayLoanid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("loanid", this.loanid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.LoansDownpayLoanidResource.class).postLoansDownpayLoanid();
    }

}
