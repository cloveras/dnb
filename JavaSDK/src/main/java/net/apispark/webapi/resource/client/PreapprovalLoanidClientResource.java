package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class PreapprovalLoanidClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String loanid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param loanid
     *            Attribute "loanid"
     */
    public PreapprovalLoanidClientResource(net.apispark.webapi.Config config, java.lang.String loanid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.loanid = loanid;
        this.absolutePath = config.getBasePath() + "/preapproval/{loanid}";
    }

    /**
     * For now: Returns a Loan object.
     * 
     * @return {@link net.apispark.webapi.representation.Loan} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Loan getPreapprovalLoanid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("loanid", this.loanid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.PreapprovalLoanidResource.class).getPreapprovalLoanid();
    }

}
