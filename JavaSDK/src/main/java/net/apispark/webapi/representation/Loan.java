package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * For now: One common loan type for mortgage, car, credit card, etc
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Loan implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String type;

    private java.lang.String totalamount;

    private java.lang.String remainingamount;

    private java.lang.Integer interest;

    private java.lang.String customerid;

    private java.util.Date created;

    private java.util.Date expires;

    private java.lang.String detailsplaceholder;

    /**
     * Returns the value of property "id". 
     * 
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Updates the value of property "id". 
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * Returns the value of property "type". 
     * 
     */
    public java.lang.String getType() {
        return type;
    }

    /**
     * Updates the value of property "type". 
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    /**
     * Returns the value of property "totalamount". 
     * 
     */
    public java.lang.String getTotalamount() {
        return totalamount;
    }

    /**
     * Updates the value of property "totalamount". 
     */
    public void setTotalamount(java.lang.String totalamount) {
        this.totalamount = totalamount;
    }

    /**
     * Returns the value of property "remainingamount". 
     * 
     */
    public java.lang.String getRemainingamount() {
        return remainingamount;
    }

    /**
     * Updates the value of property "remainingamount". 
     */
    public void setRemainingamount(java.lang.String remainingamount) {
        this.remainingamount = remainingamount;
    }

    /**
     * Returns the value of property "interest". 
     * 
     */
    public java.lang.Integer getInterest() {
        return interest;
    }

    /**
     * Updates the value of property "interest". 
     */
    public void setInterest(java.lang.Integer interest) {
        this.interest = interest;
    }

    /**
     * Returns the value of property "customerid". 
     * 11 digits
     */
    public java.lang.String getCustomerid() {
        return customerid;
    }

    /**
     * Updates the value of property "customerid". 
     */
    public void setCustomerid(java.lang.String customerid) {
        this.customerid = customerid;
    }

    /**
     * Returns the value of property "created". 
     * 
     */
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = net.apispark.webapi.Config.DATETIME_FORMAT)
    public java.util.Date getCreated() {
        return created;
    }

    /**
     * Updates the value of property "created". 
     */
    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    /**
     * Returns the value of property "expires". 
     * Remaining time can be calculated from this.
     */
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = net.apispark.webapi.Config.DATETIME_FORMAT)
    public java.util.Date getExpires() {
        return expires;
    }

    /**
     * Updates the value of property "expires". 
     */
    public void setExpires(java.util.Date expires) {
        this.expires = expires;
    }

    /**
     * Returns the value of property "detailsplaceholder". 
     * 
     */
    @com.fasterxml.jackson.annotation.JsonProperty("details (placeholder)")
    public java.lang.String getDetailsplaceholder() {
        return detailsplaceholder;
    }

    /**
     * Updates the value of property "detailsplaceholder". 
     */
    public void setDetailsplaceholder(java.lang.String detailsplaceholder) {
        this.detailsplaceholder = detailsplaceholder;
    }

}
