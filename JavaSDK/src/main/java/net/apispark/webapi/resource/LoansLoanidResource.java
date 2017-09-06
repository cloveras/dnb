package net.apispark.webapi.resource;

public interface LoansLoanidResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Loan} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Loan getLoansLoanid();

    /**
     * Downpayment first.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteLoansLoanid();

}