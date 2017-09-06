package net.apispark.webapi.resource;

public interface InsurancePolicynumberClaimsResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.InsuranceclaimList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.InsuranceclaimList getInsurancePolicynumberClaims();

}