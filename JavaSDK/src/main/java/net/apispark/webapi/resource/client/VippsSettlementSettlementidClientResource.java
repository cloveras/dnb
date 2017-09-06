package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class VippsSettlementSettlementidClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String settlementid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param settlementid
     *            Attribute "settlementid"
     */
    public VippsSettlementSettlementidClientResource(net.apispark.webapi.Config config, java.lang.String settlementid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.settlementid = settlementid;
        this.absolutePath = config.getBasePath() + "/vipps/settlement/{settlementid}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.StringList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.StringList getVippsSettlementSettlementid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("settlementid", this.settlementid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.VippsSettlementSettlementidResource.class).getVippsSettlementSettlementid();
    }

    /**
     * (Not possible in Vipps today).
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteVippsSettlementSettlementid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("settlementid", this.settlementid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.VippsSettlementSettlementidResource.class).deleteVippsSettlementSettlementid();
    }

}
