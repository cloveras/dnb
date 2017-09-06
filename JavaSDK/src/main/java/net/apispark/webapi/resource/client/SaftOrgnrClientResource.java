package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class SaftOrgnrClientResource {

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
    public SaftOrgnrClientResource(net.apispark.webapi.Config config, java.lang.String orgnr) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.orgnr = orgnr;
        this.absolutePath = config.getBasePath() + "/saf-t/{orgnr}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.SAFTfile} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.SAFTfile getSaftOrgnr() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.SaftOrgnrResource.class).getSaftOrgnr();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postSaftOrgnr(net.apispark.webapi.representation.SAFTfile bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.SaftOrgnrResource.class).postSaftOrgnr(bean);
    }

}
