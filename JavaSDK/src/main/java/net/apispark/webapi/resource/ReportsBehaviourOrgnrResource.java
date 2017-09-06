package net.apispark.webapi.resource;

public interface ReportsBehaviourOrgnrResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Reportplaceholder} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Reportplaceholder getReportsBehaviourOrgnr();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteReportsBehaviourOrgnr();

}