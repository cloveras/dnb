package net.apispark.webapi.resource;

public interface CustomersOrgnrResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Customer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Customer getCustomersOrgnr();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postCustomersOrgnr(net.apispark.webapi.representation.Customer bean);

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteCustomersOrgnr();

}