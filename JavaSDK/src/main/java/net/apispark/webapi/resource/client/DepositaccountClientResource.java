package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class DepositaccountClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String ownerid;

    private java.lang.String lenderid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param ownerid
     *            Personal id number (SSN) of the "owner" for the artifact this deposit account is security for, 11 digits.
     * @param lenderid
     *            Personal id number (SSN) of the "lender" for the artifact this deposit account is security for, 11 digits.
     */
    public DepositaccountClientResource(net.apispark.webapi.Config config, java.lang.String ownerid, java.lang.String lenderid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.ownerid = ownerid;
        this.lenderid = lenderid;
        this.absolutePath = config.getBasePath() + "/account/deposit/{ownerid}/{lenderid}";
    }

    /**
     * For now: Using Account object with SSIDs of tennant and owner. 

Required:
* Electronic copy of signed rental agreement
* The address of the apartment (or license details for car, boat, etc)
* Name, SSN, email, phone of the lender (or is SSN sufficient, at least for existing DNB customers?)

Use a Customer object?

DNB deposit account: https://www.dnb.no/privat/nettbank-mobil-og-kort/konto/depositumskonto.html .
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postAccountDepositOwneridLenderid(net.apispark.webapi.representation.Account bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("ownerid", this.ownerid);
        client.setAttribute("lenderid", this.lenderid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.DepositaccountResource.class).postAccountDepositOwneridLenderid(bean);
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteAccountDepositOwneridLenderid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("ownerid", this.ownerid);
        client.setAttribute("lenderid", this.lenderid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.DepositaccountResource.class).deleteAccountDepositOwneridLenderid();
    }

    /**
     * Need to provide more than an Account object, but then again this is a draft/note only.
     * 
     * @return {@link net.apispark.webapi.representation.Account} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Account getAccountDepositOwneridLenderid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("ownerid", this.ownerid);
        client.setAttribute("lenderid", this.lenderid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.DepositaccountResource.class).getAccountDepositOwneridLenderid();
    }

}
