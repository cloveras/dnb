package net.apispark.webapi.resource;

public interface AppRootResource {

    /**
     * From: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf

To launch the ViPPS app, use the following call (at a minimum):

vipps://?action=inAppPayment&appID=fa01s-21res-qq21p-mq1p6&amount=12000&merchantSerialNumber=ed9320pre&fallbackURL=app://?action=confirmed.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getAppRoot();

}