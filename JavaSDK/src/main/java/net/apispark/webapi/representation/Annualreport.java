package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * See SAF-T: https://github.com/Skatteetaten/saf-t
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Annualreport implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String orgnr;

    private java.lang.String year;

    private java.lang.String details;

    /**
     * Returns the value of property "orgnr". 
     * 
     */
    public java.lang.String getOrgnr() {
        return orgnr;
    }

    /**
     * Updates the value of property "orgnr". 
     */
    public void setOrgnr(java.lang.String orgnr) {
        this.orgnr = orgnr;
    }

    /**
     * Returns the value of property "year". 
     * 
     */
    public java.lang.String getYear() {
        return year;
    }

    /**
     * Updates the value of property "year". 
     */
    public void setYear(java.lang.String year) {
        this.year = year;
    }

    /**
     * Returns the value of property "details". 
     * TDB. Around 80 fields.
     */
    public java.lang.String getDetails() {
        return details;
    }

    /**
     * Updates the value of property "details". 
     */
    public void setDetails(java.lang.String details) {
        this.details = details;
    }

}
