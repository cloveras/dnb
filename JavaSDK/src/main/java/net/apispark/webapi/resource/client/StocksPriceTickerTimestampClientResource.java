package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class StocksPriceTickerTimestampClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String ticker;

    private java.lang.String timestamp;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param ticker
     *            Attribute "ticker"
     * @param timestamp
     *            Attribute "timestamp"
     */
    public StocksPriceTickerTimestampClientResource(net.apispark.webapi.Config config, java.lang.String ticker, java.lang.String timestamp) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.ticker = ticker;
        this.timestamp = timestamp;
        this.absolutePath = config.getBasePath() + "/stocks/price/{ticker}/{timestamp}";
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void getStocksPriceTickerTimestamp() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("ticker", this.ticker);
        client.setAttribute("timestamp", this.timestamp);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.StocksPriceTickerTimestampResource.class).getStocksPriceTickerTimestamp();
    }

}
