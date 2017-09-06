package net.apispark.webapi.resource;

public interface VippsInvoiceOrgnrResource {

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getVippsInvoiceOrgnr();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postVippsInvoiceOrgnr(net.apispark.webapi.representation.Customer bean);

}