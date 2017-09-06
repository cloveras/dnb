package net.apispark.webapi.resource;

public interface InsurancePolicynumberResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Insurance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Insurance getInsurancePolicynumber();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postInsurancePolicynumber(net.apispark.webapi.representation.Insurance bean);

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteInsurancePolicynumber();

}