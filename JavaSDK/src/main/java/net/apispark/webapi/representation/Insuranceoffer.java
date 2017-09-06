package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Insuranceoffer implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String detailsplaceholder;

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
