package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ```
{
   "account": 12345678910,
   "balance": 12345,
   "timestamp": "2017-01-03T09:56:28+00:0",
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Balance implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String account;

    private java.lang.Integer amount;

    private java.util.Date timestamp;

    /**
     * Returns the value of property "account". 
     * Account number, 11 digits.
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
     * Returns the value of property "amount". 
     * 
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
     * Returns the value of property "timestamp". 
     * ISO 8601: www.iso.org/iso/home/standards/iso8601.htm
     */
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = net.apispark.webapi.Config.DATETIME_FORMAT)
    public java.util.Date getTimestamp() {
        return timestamp;
    }

    /**
     * Updates the value of property "timestamp". 
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

}
