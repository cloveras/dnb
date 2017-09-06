package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class StocksTickerClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String ticker;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param ticker
     *            Attribute "ticker"
     */
    public StocksTickerClientResource(net.apispark.webapi.Config config, java.lang.String ticker) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.ticker = ticker;
        this.absolutePath = config.getBasePath() + "/stocks/{ticker}";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getStocksTicker() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("ticker", this.ticker);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.StocksTickerResource.class).getStocksTicker();
    }

}
