package net.apispark.webapi.representation.vippspaymentresponse;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class TransactionInfo implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String amount;

    private java.lang.String status;

    private java.lang.String transactionId;

    private java.lang.String timeStamp;

    private java.lang.String message;

    /**
     * Returns the value of property "amount". 
     * 
     */
    public java.lang.String getAmount() {
        return amount;
    }

    /**
     * Updates the value of property "amount". 
     */
    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    /**
     * Returns the value of property "status". 
     * 
     */
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * Updates the value of property "status". 
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    /**
     * Returns the value of property "transactionId". 
     * 
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }

    /**
     * Updates the value of property "transactionId". 
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Returns the value of property "timeStamp". 
     * 
     */
    public java.lang.String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Updates the value of property "timeStamp". 
     */
    public void setTimeStamp(java.lang.String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Returns the value of property "message". 
     * 
     */
    public java.lang.String getMessage() {
        return message;
    }

    /**
     * Updates the value of property "message". 
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

}
