package net.apispark.webapi.resource;

public interface VippsSettlementSettlementidExpenseResource {

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getVippsSettlementSettlementidExpense();

    /**
     * Date, who, amount, description, etc.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postVippsSettlementSettlementidExpense(net.apispark.webapi.representation.StringList bean);

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteVippsSettlementSettlementidExpense();

}