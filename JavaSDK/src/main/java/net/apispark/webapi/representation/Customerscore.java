package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Customerscore implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String customerid;

    private java.lang.Long score;

    private java.lang.String score_details;

    /**
     * Returns the value of property "id". 
     * Id of this score
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
     * Customer id
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
     * Returns the value of property "score". 
     * The score
     */
    public java.lang.Long getScore() {
        return score;
    }

    /**
     * Updates the value of property "score". 
     */
    public void setScore(java.lang.Long score) {
        this.score = score;
    }

    /**
     * Returns the value of property "score_details". 
     * Optional details
     */
    public java.lang.String getScore_details() {
        return score_details;
    }

    /**
     * Updates the value of property "score_details". 
     */
    public void setScore_details(java.lang.String score_details) {
        this.score_details = score_details;
    }

}
