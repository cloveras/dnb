package net.apispark.webapi.resource;

public interface VippsPaymentsResource {

    /**
     * From 8.3.2: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf.
     *
     * @return  {@link net.apispark.webapi.representation.Vippspaymentresponse} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.Vippspaymentresponse postVippsPayments(net.apispark.webapi.representation.Vippspaymentrequest bean);

}