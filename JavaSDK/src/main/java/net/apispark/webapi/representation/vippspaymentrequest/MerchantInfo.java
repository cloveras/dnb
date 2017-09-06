package net.apispark.webapi.representation.vippspaymentrequest;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class MerchantInfo implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String merchantSerialNumber;

    private java.lang.String callBack;

    /**
     * Returns the value of property "merchantSerialNumber". 
     * Identifies a merchant sales channel i.e. website, mobile app etc.
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
     * Returns the value of property "callBack". 
     * ViPPS will use this Call back URL to provide the status of initiated transaction. Length of the string is 255 characters
     */
    public java.lang.String getCallBack() {
        return callBack;
    }

    /**
     * Updates the value of property "callBack". 
     */
    public void setCallBack(java.lang.String callBack) {
        this.callBack = callBack;
    }

}
