package net.apispark.webapi.resource;

public interface LoansApplyResource {

    /**
     * Need to POST quite a bit of data, and also handle AML, KYC, etc.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postLoansApply(net.apispark.webapi.representation.StringList bean);

}