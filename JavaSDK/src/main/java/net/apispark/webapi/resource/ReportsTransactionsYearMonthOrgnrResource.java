package net.apispark.webapi.resource;

public interface ReportsTransactionsYearMonthOrgnrResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.StringList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.StringList getReportsTransactionsYearMonthOrgnr();

}