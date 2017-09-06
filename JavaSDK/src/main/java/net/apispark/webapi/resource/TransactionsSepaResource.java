package net.apispark.webapi.resource;

public interface TransactionsSepaResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.SEPAresponse} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.SEPAresponse postTransactionsSepa(net.apispark.webapi.representation.SEPArequest bean);

}