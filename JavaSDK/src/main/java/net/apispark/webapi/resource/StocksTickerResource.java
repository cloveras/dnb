package net.apispark.webapi.resource;

public interface StocksTickerResource {

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getStocksTicker();

}