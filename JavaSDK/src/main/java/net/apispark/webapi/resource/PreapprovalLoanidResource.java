package net.apispark.webapi.resource;

public interface PreapprovalLoanidResource {

    /**
     * For now: Returns a Loan object.
     *
     * @return  {@link net.apispark.webapi.representation.Loan} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Loan getPreapprovalLoanid();

}