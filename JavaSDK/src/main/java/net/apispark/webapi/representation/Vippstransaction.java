package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * From 8.7.5: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf

Example:
```
{
    "orderId": "219930212",
    "transactionSummary": {
        "capturedAmount":"0",
        "remainingAmoutTocapture":"0",
        "refundedAmount":"1200",
        "remainingAmountToRefund":"0"
      },
     "transactionLogHistory": [{
         "timeStamp": "",
        "operation": "",
        "amount": "",
        "transactionId":"",
       "transactionText"
       "requestId": ""
}]
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Vippstransaction implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String orderId;

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

}
