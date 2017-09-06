package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;

/**
 * Delete preapproval for this person.
 */
public class DeletepreapprovalClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String personnr;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param personnr
     *            Attribute "personnr"
     */
    public DeletepreapprovalClientResource(net.apispark.webapi.Config config, java.lang.String personnr) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.personnr = personnr;
        this.absolutePath = config.getBasePath() + "/preapproval/delete/{personnr}";
    }

    /**
     * When a loan is issued, either by DNB or another bank, the preapproval should be deleted.
Separate method to avoid specifying amount. "Personnr" is sufficient.
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deletePreapprovalDeletePersonnr() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("personnr", this.personnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.DeletepreapprovalResource.class).deletePreapprovalDeletePersonnr();
    }

}
