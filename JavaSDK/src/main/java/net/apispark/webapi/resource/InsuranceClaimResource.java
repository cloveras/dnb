package net.apispark.webapi.resource;

public interface InsuranceClaimResource {

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postInsuranceClaim(net.apispark.webapi.representation.Insuranceclaim bean);

}