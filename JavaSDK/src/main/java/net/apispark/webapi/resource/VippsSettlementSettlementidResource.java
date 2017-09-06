package net.apispark.webapi.resource;

public interface VippsSettlementSettlementidResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.StringList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.StringList getVippsSettlementSettlementid();

    /**
     * (Not possible in Vipps today).
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteVippsSettlementSettlementid();

}