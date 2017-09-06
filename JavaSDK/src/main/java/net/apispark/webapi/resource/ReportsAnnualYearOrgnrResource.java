package net.apispark.webapi.resource;

public interface ReportsAnnualYearOrgnrResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Annualreport} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Annualreport getReportsAnnualYearOrgnr();

}