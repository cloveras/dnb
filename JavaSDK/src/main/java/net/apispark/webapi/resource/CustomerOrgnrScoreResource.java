package net.apispark.webapi.resource;

public interface CustomerOrgnrScoreResource {

    /**
     * Customer score with all details.
     *
     * @return  {@link net.apispark.webapi.representation.Customerscore} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Customerscore getCustomerOrgnrScore();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postCustomerOrgnrScore(net.apispark.webapi.representation.Customerscore bean);

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deleteCustomerOrgnrScore();

}