package net.apispark.webapi.representation.vippspaymentrequest;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class CustomerInfo implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String mobileNumber;

    /**
     * Returns the value of property "mobileNumber". 
     * Mobile number of the user who has to pay for the  transaction from ViPPS.
Allowed format:
+47-xxxxxxxx
+47xxxxxxxx
xxxxxxxx
     */
    public java.lang.String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Updates the value of property "mobileNumber". 
     */
    public void setMobileNumber(java.lang.String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
