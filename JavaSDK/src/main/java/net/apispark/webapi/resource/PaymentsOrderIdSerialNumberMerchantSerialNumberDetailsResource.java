package net.apispark.webapi.resource;

public interface PaymentsOrderIdSerialNumberMerchantSerialNumberDetailsResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Vippstransaction} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Vippstransaction getPaymentsOrderIdSerialNumberMerchantSerialNumberDetails();

}