package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Accountstatement implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String account;

    private java.lang.String year;

    private java.lang.String month;

    private java.util.List<net.apispark.webapi.representation.Transaction> transactions = new java.util.ArrayList<net.apispark.webapi.representation.Transaction>();

    /**
     * Returns the value of property "account". 
     * 
     */
    public java.lang.String getAccount() {
        return account;
    }

    /**
     * Updates the value of property "account". 
     */
    public void setAccount(java.lang.String account) {
        this.account = account;
    }

    /**
     * Returns the value of property "year". 
     * 
     */
    public java.lang.String getYear() {
        return year;
    }

    /**
     * Updates the value of property "year". 
     */
    public void setYear(java.lang.String year) {
        this.year = year;
    }

    /**
     * Returns the value of property "month". 
     * 
     */
    public java.lang.String getMonth() {
        return month;
    }

    /**
     * Updates the value of property "month". 
     */
    public void setMonth(java.lang.String month) {
        this.month = month;
    }

    /**
     * Returns the value of property "transactions". 
     * 
     */
    public java.util.List<net.apispark.webapi.representation.Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Updates the value of property "transactions". 
     */
    public void setTransactions(java.util.List<net.apispark.webapi.representation.Transaction> transactions) {
        this.transactions = transactions;
    }

}
