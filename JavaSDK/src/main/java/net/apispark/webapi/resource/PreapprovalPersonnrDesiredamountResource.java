package net.apispark.webapi.resource;

public interface PreapprovalPersonnrDesiredamountResource {

    /**
     * Optional parameters: County (kommunenummer), gnr (gårdsnummer) and bnr (bruksnummer ) may be used to specify which property the preapproval is being checked for. This may allow DNB to find out that the preapproval holder is in a bidding process.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    void getPreapprovalPersonnrDesiredamount();

    /**
     * For now apply for a specified amount only. If the amouhnt specified is 0 (zero), the application will be treated as for the maximum possible amount.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postPreapprovalPersonnrDesiredamount();

}