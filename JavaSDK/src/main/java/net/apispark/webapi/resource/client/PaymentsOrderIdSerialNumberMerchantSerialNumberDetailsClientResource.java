package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class PaymentsOrderIdSerialNumberMerchantSerialNumberDetailsClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String orderId;

    private java.lang.String merchantSerialNumber;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param orderId
     *            Attribute "orderId"
     * @param merchantSerialNumber
     *            Attribute "merchantSerialNumber"
     */
    public PaymentsOrderIdSerialNumberMerchantSerialNumberDetailsClientResource(net.apispark.webapi.Config config, java.lang.String orderId, java.lang.String merchantSerialNumber) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.orderId = orderId;
        this.merchantSerialNumber = merchantSerialNumber;
        this.absolutePath = config.getBasePath() + "/payments /{orderId}/serialNumber/{merchantSerialNumber}/details";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Vippstransaction} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Vippstransaction getPaymentsOrderIdSerialNumberMerchantSerialNumberDetails() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("orderId", this.orderId);
        client.setAttribute("merchantSerialNumber", this.merchantSerialNumber);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.PaymentsOrderIdSerialNumberMerchantSerialNumberDetailsResource.class).getPaymentsOrderIdSerialNumberMerchantSerialNumberDetails();
    }

}
