package net.apispark.webapi.resource;

public interface VippsCustomerResource {

    /**
     * Phone number, personal id number, name, card number, account number.
     *
     * @return  {@link net.apispark.webapi.representation.Customer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.Customer postVippsCustomer(net.apispark.webapi.representation.StringList bean);

}