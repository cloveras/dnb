package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class TransactionsTransactionidClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String transactionid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param transactionid
     *            id
     */
    public TransactionsTransactionidClientResource(net.apispark.webapi.Config config, java.lang.String transactionid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.transactionid = transactionid;
        this.absolutePath = config.getBasePath() + "/transactions/{transactionid}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Transaction} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Transaction getTransactionsTransactionid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("transactionid", this.transactionid);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.TransactionsTransactionidResource.class).getTransactionsTransactionid();
    }

    /**
     * TBD. Needs a lot more details. Just a note, really.
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void postTransactionsTransactionid(net.apispark.webapi.representation.Transaction bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("transactionid", this.transactionid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.TransactionsTransactionidResource.class).postTransactionsTransactionid(bean);
    }

    /**
     * 
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteTransactionsTransactionid() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("transactionid", this.transactionid);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.TransactionsTransactionidResource.class).deleteTransactionsTransactionid();
    }

}
