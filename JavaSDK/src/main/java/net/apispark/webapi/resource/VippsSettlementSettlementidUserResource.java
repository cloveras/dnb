package net.apispark.webapi.resource;

public interface VippsSettlementSettlementidUserResource {

    /**
     * Should POST a Person, not Customer.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postVippsSettlementSettlementidUser(net.apispark.webapi.representation.Customer bean);

}