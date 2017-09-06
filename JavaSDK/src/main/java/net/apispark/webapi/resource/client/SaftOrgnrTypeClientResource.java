package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class SaftOrgnrTypeClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String orgnr;

    private java.lang.String type;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param orgnr
     *            Organization number, 9 digits. Example: 123456789
     * @param type
     *            Type of SAF-T file
     */
    public SaftOrgnrTypeClientResource(net.apispark.webapi.Config config, java.lang.String orgnr, java.lang.String type) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.orgnr = orgnr;
        this.type = type;
        this.absolutePath = config.getBasePath() + "/saf-t/{orgnr}/{type}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.SAFTfile} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.SAFTfile getSaftOrgnrType() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        client.setAttribute("type", this.type);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.SaftOrgnrTypeResource.class).getSaftOrgnrType();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postSaftOrgnrType(net.apispark.webapi.representation.SAFTfile bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orgnr", this.orgnr);
        client.setAttribute("type", this.type);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.SaftOrgnrTypeResource.class).postSaftOrgnrType(bean);
    }

}
