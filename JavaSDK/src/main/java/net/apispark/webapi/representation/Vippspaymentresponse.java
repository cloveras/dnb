package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * From 8.3.7: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf

```
{
    "orderId": "219930212",
    "merchantSerialNumber": "NSBWSHP12",
    "transactionInfo": {
        "amount": 1200,
        "status": "Initiate",
        "transactionId": "1001234566",
        "timeStamp": "2014-06-24T08:34:25-07:00" , 
        "message":"Please use vipps app to process the payment"
    }
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Vippspaymentresponse implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String orderId;

    private java.lang.String merchantSerialNumber;

    private java.util.List<net.apispark.webapi.representation.vippspaymentresponse.TransactionInfo> transactionInfo = new java.util.ArrayList<net.apispark.webapi.representation.vippspaymentresponse.TransactionInfo>();

    /**
     * Returns the value of property "orderId". 
     * 
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
     * Returns the value of property "merchantSerialNumber". 
     * 
     */
    public java.lang.String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    /**
     * Updates the value of property "merchantSerialNumber". 
     */
    public void setMerchantSerialNumber(java.lang.String merchantSerialNumber) {
        this.merchantSerialNumber = merchantSerialNumber;
    }

    /**
     * Returns the value of property "transactionInfo". 
     * 
     */
    public java.util.List<net.apispark.webapi.representation.vippspaymentresponse.TransactionInfo> getTransactionInfo() {
        return transactionInfo;
    }

    /**
     * Updates the value of property "transactionInfo". 
     */
    public void setTransactionInfo(java.util.List<net.apispark.webapi.representation.vippspaymentresponse.TransactionInfo> transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

}
