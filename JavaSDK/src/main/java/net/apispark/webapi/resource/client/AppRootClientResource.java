package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class AppRootClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public AppRootClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/";
    }

    /**
     * From: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf

To launch the ViPPS app, use the following call (at a minimum):

vipps://?action=inAppPayment&appID=fa01s-21res-qq21p-mq1p6&amount=12000&merchantSerialNumber=ed9320pre&fallbackURL=app://?action=confirmed.
     * 
     * @param action
     *            for payment it should be “inAppPayment” and for reservation “inAppReserve”.
     *            Required parameter.
     * @param appID
     *            App registration id will be created for Merchants’ each app registered in Vipps Systems and will be shared once the enrolment process is done
     *            Required parameter.
     * @param amount
     *            Amount for which the payment should be initiated. The amount should in the lowest denomination i.e., Øre. Vipps only support NOK currency. Minimum value is 100 and maximum value is 9999999. Vipps will not round of the provided number. If the amount is not in the mentioned format, Vipps will return the FAIL state of transaction mentioning incorrect number format.
     *            Required parameter.
     * @param orderID
     *            Order id from 3rdparty app for which user is initiating a payment. Maximum length for this field is 30 chars. This order ID will be stored in the Vipps systems against the transactions and shall be displayed the receipt in the Vipps App for the user. The order ID will also be presented for the respective transaction in the Sales and Settlement report.
     *            Required parameter.
     * @param message
     *            URL encoded string. Payment’s reference message. Maximum length for this field is 200 chars. The message field will be displayed as part of the receipt.
     * @param merchantSerialNumber
     *            Merchant Sales Unit’s serial number will be a unique number in Vipps systems. The Serial number registered in Vipps Systems will be shared once the enrollment process is done.
     *            Required parameter.
     * @param data
     *            URL encoded string. If this exists, then the contents will go as a query string parameter with fallbackURL. Calling app can use this field to identify their session or booking for which the payment has been done. Maximum length for this field is 128 chars.
     * @param fallbackURL
     *            URL encoded string. URL to be invoked once the payment process is complete. This is to respond back to calling app
     *            Required parameter.
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getAppRoot(java.lang.String action, java.lang.String appID, java.lang.String amount, java.lang.String orderID, java.lang.String message, java.lang.String merchantSerialNumber, java.lang.String data, java.lang.String fallbackURL) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "action", action);
        QueryParameterHelper.addQueryParameter(client, "appID", appID);
        QueryParameterHelper.addQueryParameter(client, "amount", amount);
        QueryParameterHelper.addQueryParameter(client, "orderID", orderID);
        QueryParameterHelper.addQueryParameter(client, "message", message);
        QueryParameterHelper.addQueryParameter(client, "merchantSerialNumber", merchantSerialNumber);
        QueryParameterHelper.addQueryParameter(client, "data", data);
        QueryParameterHelper.addQueryParameter(client, "fallbackURL", fallbackURL);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.AppRootResource.class).getAppRoot();
    }

}
