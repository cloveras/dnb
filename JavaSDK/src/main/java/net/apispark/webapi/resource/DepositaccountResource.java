package net.apispark.webapi.resource;

public interface DepositaccountResource {

    /**
     * For now: Using Account object with SSIDs of tennant and owner. 

Required:
* Electronic copy of signed rental agreement
* The address of the apartment (or license details for car, boat, etc)
* Name, SSN, email, phone of the lender (or is SSN sufficient, at least for existing DNB customers?)

Use a Customer object?

DNB deposit account: https://www.dnb.no/privat/nettbank-mobil-og-kort/konto/depositumskonto.html .
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postAccountDepositOwneridLenderid(net.apispark.webapi.representation.Account bean);

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteAccountDepositOwneridLenderid();

    /**
     * Need to provide more than an Account object, but then again this is a draft/note only.
     *
     * @return  {@link net.apispark.webapi.representation.Account} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Account getAccountDepositOwneridLenderid();

}