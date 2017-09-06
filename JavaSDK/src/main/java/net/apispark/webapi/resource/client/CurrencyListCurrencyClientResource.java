package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class CurrencyListCurrencyClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String currency;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param currency
     *            "Home" currency
     */
    public CurrencyListCurrencyClientResource(net.apispark.webapi.Config config, java.lang.String currency) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.currency = currency;
        this.absolutePath = config.getBasePath() + "/currency/list/{currency}";
    }

    /**
     * Suitable for making a table or similar.
     * 
     * @return {@link java.lang.String} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public java.lang.String getCurrencyListCurrency() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("currency", this.currency);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.CurrencyListCurrencyResource.class).getCurrencyListCurrency();
    }

}
