package net.apispark.webapi.resource;

public interface CurrencyRateCurrencyResource {

    /**
     * Possibly add parameters for markup/margin, specify a date, find min/max in an interval (separate method?), etc.
     *
     * @return  {@link java.lang.Integer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    java.lang.Integer getCurrencyRateCurrency();

}