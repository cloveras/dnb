package net.apispark.webapi.resource;

/**
 * Delete preapproval for this person.
 */
public interface DeletepreapprovalResource {

    /**
     * When a loan is issued, either by DNB or another bank, the preapproval should be deleted.
Separate method to avoid specifying amount. "Personnr" is sufficient.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    void deletePreapprovalDeletePersonnr();

}