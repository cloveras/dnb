package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Example:
```
{
    "merchantInfo": {
        "merchantSerialNumber": "NSBWSHP12",
        "callBack": "https://www.demo.no/api/callback"
    },
    "customerInfo": {
        "mobileNumber": "90090900"
    },
    "transaction": {
        "orderId": "219930212",
        "refOrderId": "119930211",
        "amount": 1200,
        "transactionText": "Transaction text",
        "timeStamp":"2014-06-24T08:34:25-07:00"
    }
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Vippspaymentrequest implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.util.List<net.apispark.webapi.representation.vippspaymentrequest.MerchantInfo> merchantInfo = new java.util.ArrayList<net.apispark.webapi.representation.vippspaymentrequest.MerchantInfo>();

    private java.util.List<net.apispark.webapi.representation.vippspaymentrequest.CustomerInfo> customerInfo = new java.util.ArrayList<net.apispark.webapi.representation.vippspaymentrequest.CustomerInfo>();

    private java.util.List<net.apispark.webapi.representation.vippspaymentrequest.Transaction> transaction = new java.util.ArrayList<net.apispark.webapi.representation.vippspaymentrequest.Transaction>();

    /**
     * Returns the value of property "merchantInfo". 
     * 
     */
    public java.util.List<net.apispark.webapi.representation.vippspaymentrequest.MerchantInfo> getMerchantInfo() {
        return merchantInfo;
    }

    /**
     * Updates the value of property "merchantInfo". 
     */
    public void setMerchantInfo(java.util.List<net.apispark.webapi.representation.vippspaymentrequest.MerchantInfo> merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    /**
     * Returns the value of property "customerInfo". 
     * 
     */
    public java.util.List<net.apispark.webapi.representation.vippspaymentrequest.CustomerInfo> getCustomerInfo() {
        return customerInfo;
    }

    /**
     * Updates the value of property "customerInfo". 
     */
    public void setCustomerInfo(java.util.List<net.apispark.webapi.representation.vippspaymentrequest.CustomerInfo> customerInfo) {
        this.customerInfo = customerInfo;
    }

    /**
     * Returns the value of property "transaction". 
     * 
     */
    public java.util.List<net.apispark.webapi.representation.vippspaymentrequest.Transaction> getTransaction() {
        return transaction;
    }

    /**
     * Updates the value of property "transaction". 
     */
    public void setTransaction(java.util.List<net.apispark.webapi.representation.vippspaymentrequest.Transaction> transaction) {
        this.transaction = transaction;
    }

}
