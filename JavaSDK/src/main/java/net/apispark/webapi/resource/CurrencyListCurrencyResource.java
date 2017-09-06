package net.apispark.webapi.resource;

public interface CurrencyListCurrencyResource {

    /**
     * Suitable for making a table or similar.
     *
     * @return  {@link java.lang.String} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    java.lang.String getCurrencyListCurrency();

}