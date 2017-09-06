package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class VippsSettlementSettlementidUserClientResource {

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
    public VippsSettlementSettlementidUserClientResource(net.apispark.webapi.Config config, java.lang.String settlementid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.settlementid = settlementid;
        this.absolutePath = config.getBasePath() + "/vipps/settlement/{settlementid}/user";
    }

    /**
     * Should POST a Person, not Customer.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postVippsSettlementSettlementidUser(net.apispark.webapi.representation.Customer bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("settlementid", this.settlementid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.VippsSettlementSettlementidUserResource.class).postVippsSettlementSettlementidUser(bean);
    }

}
