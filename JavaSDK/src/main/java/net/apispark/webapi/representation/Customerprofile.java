package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Customerprofile implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String customerid;

    private java.lang.String Notesplaceholder;

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
     * Returns the value of property "customerid". 
     * 
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
     * Returns the value of property "Notesplaceholder". 
     * Needs lots more.
     */
    @com.fasterxml.jackson.annotation.JsonProperty("Notes (placeholder)")
    public java.lang.String getNotesplaceholder() {
        return Notesplaceholder;
    }

    /**
     * Updates the value of property "Notesplaceholder". 
     */
    public void setNotesplaceholder(java.lang.String Notesplaceholder) {
        this.Notesplaceholder = Notesplaceholder;
    }

}
