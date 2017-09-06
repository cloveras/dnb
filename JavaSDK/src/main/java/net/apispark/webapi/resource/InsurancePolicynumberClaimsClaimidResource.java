package net.apispark.webapi.resource;

public interface InsurancePolicynumberClaimsClaimidResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Insuranceclaim} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Insuranceclaim getInsurancePolicynumberClaimsClaimid();

}