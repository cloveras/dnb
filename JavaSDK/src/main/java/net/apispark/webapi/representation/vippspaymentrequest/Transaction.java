package net.apispark.webapi.representation.vippspaymentrequest;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Transaction implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String orderId;

    private java.lang.String refOrderId;

    private java.lang.Integer amount;

    private java.lang.String transactionText;

    private java.lang.String timeStamp;

    /**
     * Returns the value of property "orderId". 
     * Id which uniquely identifies an Order. Maximum length is 30 alphanumeric characters
     */
    public java.lang.String getOrderId() {
        return orderId;
    }

    /**
     * Updates the value of property "orderId". 
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns the value of property "refOrderId". 
     * Identifies if the payment references to some past orders registered with ViPPS. If defined, transactions for this payment will show up as child transactions of the specified order. Described functionality is not in place and is planned for future implementation
     */
    public java.lang.String getRefOrderId() {
        return refOrderId;
    }

    /**
     * Updates the value of property "refOrderId". 
     */
    public void setRefOrderId(java.lang.String refOrderId) {
        this.refOrderId = refOrderId;
    }

    /**
     * Returns the value of property "amount". 
     * Amount in øre. 32 Bit Integer (2147483647)
     */
    public java.lang.Integer getAmount() {
        return amount;
    }

    /**
     * Updates the value of property "amount". 
     */
    public void setAmount(java.lang.Integer amount) {
        this.amount = amount;
    }

    /**
     * Returns the value of property "transactionText". 
     * Transaction text that can be displayed to end user
     */
    public java.lang.String getTransactionText() {
        return transactionText;
    }

    /**
     * Updates the value of property "transactionText". 
     */
    public void setTransactionText(java.lang.String transactionText) {
        this.transactionText = transactionText;
    }

    /**
     * Returns the value of property "timeStamp". 
     * Timestamp in ISO
-
8601 representing when the 
order has been made by merchant
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

}
