package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Used for house insurance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class House implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.Double county;

    private java.lang.String gnr;

    private java.lang.Double bnr;

    private java.lang.Double value;

    private java.lang.String description;

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
     * Returns the value of property "county". 
     * Kommunenummer
     */
    public java.lang.Double getCounty() {
        return county;
    }

    /**
     * Updates the value of property "county". 
     */
    public void setCounty(java.lang.Double county) {
        this.county = county;
    }

    /**
     * Returns the value of property "gnr". 
     * Gårdsnummer
     */
    public java.lang.String getGnr() {
        return gnr;
    }

    /**
     * Updates the value of property "gnr". 
     */
    public void setGnr(java.lang.String gnr) {
        this.gnr = gnr;
    }

    /**
     * Returns the value of property "bnr". 
     * Bruksnummer
     */
    public java.lang.Double getBnr() {
        return bnr;
    }

    /**
     * Updates the value of property "bnr". 
     */
    public void setBnr(java.lang.Double bnr) {
        this.bnr = bnr;
    }

    /**
     * Returns the value of property "value". 
     * NOK
     */
    public java.lang.Double getValue() {
        return value;
    }

    /**
     * Updates the value of property "value". 
     */
    public void setValue(java.lang.Double value) {
        this.value = value;
    }

    /**
     * Returns the value of property "description". 
     * mMay be empty
     */
    public java.lang.String getDescription() {
        return description;
    }

    /**
     * Updates the value of property "description". 
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

}
