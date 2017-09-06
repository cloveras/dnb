package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class VippsPaymentsClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public VippsPaymentsClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/vipps/payments";
    }

    /**
     * From 8.3.2: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.Vippspaymentresponse} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Vippspaymentresponse postVippsPayments(net.apispark.webapi.representation.Vippspaymentrequest bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.VippsPaymentsResource.class).postVippsPayments(bean);
    }

}
