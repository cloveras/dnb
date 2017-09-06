package net.apispark.webapi.resource;

public interface InsuranceCarLicensenumberResource {

    /**
     * Check if a car is insured: /insurance/car/AB12345.
     *
     * @return  {@link net.apispark.webapi.representation.Insurance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Insurance getInsuranceCarLicensenumber();

    /**
     * Needs a lot of input. https://www.dnb.no/privat/forsikring/bilforsikring.html.
     *
     * @return  {@link net.apispark.webapi.representation.Insurance} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.Insurance postInsuranceCarLicensenumber(net.apispark.webapi.representation.StringList bean);

}