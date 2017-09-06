package net.apispark.webapi.resource;

public interface CustomersOrgnrAboutResource {

    /**
     * Nice if this is different from /customers/{orgnr}.
     *
     * @return  {@link net.apispark.webapi.representation.Customer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Customer getCustomersOrgnrAbout();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    void postCustomersOrgnrAbout(net.apispark.webapi.representation.Customer bean);

}