package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class VippsSettlementSettlementidUserUseridClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String settlementid;

    private java.lang.String userid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param settlementid
     *            Attribute "settlementid"
     * @param userid
     *            Attribute "userid"
     */
    public VippsSettlementSettlementidUserUseridClientResource(net.apispark.webapi.Config config, java.lang.String settlementid, java.lang.String userid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.settlementid = settlementid;
        this.userid = userid;
        this.absolutePath = config.getBasePath() + "/vipps/settlement/{settlementid}/user/{userid}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Customer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Customer getVippsSettlementSettlementidUserUserid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("settlementid", this.settlementid);
        client.setAttribute("userid", this.userid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.VippsSettlementSettlementidUserUseridResource.class).getVippsSettlementSettlementidUserUserid();
    }

}
