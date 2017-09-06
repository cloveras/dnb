package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class CurrencyRateCurrencyClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String currency;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param currency
     *            ISO 4217: alpha 3-letter upcase e.g EUR
     */
    public CurrencyRateCurrencyClientResource(net.apispark.webapi.Config config, java.lang.String currency) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.currency = currency;
        this.absolutePath = config.getBasePath() + "/currency/rate/{currency}";
    }

    /**
     * Possibly add parameters for markup/margin, specify a date, find min/max in an interval (separate method?), etc.
     * 
     * @return {@link java.lang.Integer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public java.lang.Integer getCurrencyRateCurrency() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("currency", this.currency);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.CurrencyRateCurrencyResource.class).getCurrencyRateCurrency();
    }

}
