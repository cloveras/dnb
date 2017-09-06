package net.apispark.webapi.resource;

public interface ReportsAdvicesYearMonthOrgnrResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.AdviceList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.AdviceList getReportsAdvicesYearMonthOrgnr();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteReportsAdvicesYearMonthOrgnr();

}