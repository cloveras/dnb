package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class CurrencyConvertForeigncurrencyCurrencyAmountClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String foreigncurrency;

    private java.lang.String currency;

    private java.lang.String amount;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param foreigncurrency
     *            ISO 4217: alpha 3-letter upcase e.g EUR
     * @param currency
     *            ISO 4217: alpha 3-letter upcase e.g EUR
     * @param amount
     *            Attribute "amount"
     */
    public CurrencyConvertForeigncurrencyCurrencyAmountClientResource(net.apispark.webapi.Config config, java.lang.String foreigncurrency, java.lang.String currency, java.lang.String amount) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.foreigncurrency = foreigncurrency;
        this.currency = currency;
        this.amount = amount;
        this.absolutePath = config.getBasePath() + "/currency/convert/{foreigncurrency}/{currency}/{amount}";
    }

    /**
     * 
     * 
     * @param date
     *            YYYY-MM-DD (or change to ISO timestamp?)
     * @return {@link java.lang.Integer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public java.lang.Integer getCurrencyConvertForeigncurrencyCurrencyAmount(java.lang.String date) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "date", date);
        client.setAttribute("foreigncurrency", this.foreigncurrency);
        client.setAttribute("currency", this.currency);
        client.setAttribute("amount", this.amount);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.CurrencyConvertForeigncurrencyCurrencyAmountResource.class).getCurrencyConvertForeigncurrencyCurrencyAmount();
    }

}
