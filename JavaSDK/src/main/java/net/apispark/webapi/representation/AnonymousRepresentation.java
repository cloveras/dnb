package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AnonymousRepresentation implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private net.apispark.webapi.representation.Customer customer;

    private java.lang.String balance;

    private net.apispark.webapi.representation.anonymousrepresentation.Invoice invoice;

    /**
     * Returns the value of property "customer". 
     * This is you
     */
    public net.apispark.webapi.representation.Customer getCustomer() {
        return customer;
    }

    /**
     * Updates the value of property "customer". 
     */
    public void setCustomer(net.apispark.webapi.representation.Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the value of property "balance". 
     * Some details of current balance, if relevant to the subscription plan
     */
    public java.lang.String getBalance() {
        return balance;
    }

    /**
     * Updates the value of property "balance". 
     */
    public void setBalance(java.lang.String balance) {
        this.balance = balance;
    }

    /**
     * Returns the value of property "invoice". 
     * 
     */
    public net.apispark.webapi.representation.anonymousrepresentation.Invoice getInvoice() {
        return invoice;
    }

    /**
     * Updates the value of property "invoice". 
     */
    public void setInvoice(net.apispark.webapi.representation.anonymousrepresentation.Invoice invoice) {
        this.invoice = invoice;
    }

}
