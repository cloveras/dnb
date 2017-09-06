package net.apispark.webapi.resource;

public interface VippsSettlementCreateResource {

    /**
     * date, name, participants, currency(?).
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postVippsSettlementCreate(net.apispark.webapi.representation.StringList bean);

}