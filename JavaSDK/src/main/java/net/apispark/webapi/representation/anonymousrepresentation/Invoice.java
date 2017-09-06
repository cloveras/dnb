package net.apispark.webapi.representation.anonymousrepresentation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Invoice implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.util.Date date;

    private java.lang.Long amount;

    private java.lang.Boolean paid;

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
     * Returns the value of property "date". 
     * 
     */
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = net.apispark.webapi.Config.DATETIME_FORMAT)
    public java.util.Date getDate() {
        return date;
    }

    /**
     * Updates the value of property "date". 
     */
    public void setDate(java.util.Date date) {
        this.date = date;
    }

    /**
     * Returns the value of property "amount". 
     * 
     */
    public java.lang.Long getAmount() {
        return amount;
    }

    /**
     * Updates the value of property "amount". 
     */
    public void setAmount(java.lang.Long amount) {
        this.amount = amount;
    }

    /**
     * Returns the value of property "paid". 
     * 
     */
    public java.lang.Boolean getPaid() {
        return paid;
    }

    /**
     * Updates the value of property "paid". 
     */
    public void setPaid(java.lang.Boolean paid) {
        this.paid = paid;
    }

}
