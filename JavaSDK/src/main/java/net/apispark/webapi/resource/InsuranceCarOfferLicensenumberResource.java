package net.apispark.webapi.resource;

public interface InsuranceCarOfferLicensenumberResource {

    /**
     * Needs a lot of input. https://www.dnb.no/privat/forsikring/bilforsikring.html

For now: Assume the attributes of Car object are sufficient.
     *
     * @return  {@link net.apispark.webapi.representation.Insuranceoffer} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.Insuranceoffer postInsuranceCarOfferLicensenumber(net.apispark.webapi.representation.Car bean);

}