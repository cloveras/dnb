package net.apispark.webapi.resource;

public interface TransactionsTransactionidResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Transaction} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Transaction getTransactionsTransactionid();

    /**
     * TBD. Needs a lot more details. Just a note, really.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postTransactionsTransactionid(net.apispark.webapi.representation.Transaction bean);

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteTransactionsTransactionid();

}