package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Used for car insurance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Car implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String licensenumber;

    private java.lang.Double milage;

    private java.lang.Double value_today;

    private java.lang.Double value_new;

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
     * Returns the value of property "licensenumber". 
     * Regnr
     */
    public java.lang.String getLicensenumber() {
        return licensenumber;
    }

    /**
     * Updates the value of property "licensenumber". 
     */
    public void setLicensenumber(java.lang.String licensenumber) {
        this.licensenumber = licensenumber;
    }

    /**
     * Returns the value of property "milage". 
     * In kilometers. No date specified for now.
     */
    public java.lang.Double getMilage() {
        return milage;
    }

    /**
     * Updates the value of property "milage". 
     */
    public void setMilage(java.lang.Double milage) {
        this.milage = milage;
    }

    /**
     * Returns the value of property "value_today". 
     * Estimated value, NOK
     */
    public java.lang.Double getValue_today() {
        return value_today;
    }

    /**
     * Updates the value of property "value_today". 
     */
    public void setValue_today(java.lang.Double value_today) {
        this.value_today = value_today;
    }

    /**
     * Returns the value of property "value_new". 
     * New value, NOK
     */
    public java.lang.Double getValue_new() {
        return value_new;
    }

    /**
     * Updates the value of property "value_new". 
     */
    public void setValue_new(java.lang.Double value_new) {
        this.value_new = value_new;
    }

    /**
     * Returns the value of property "description". 
     * Make, model, etc. This can be fetched from Statens Vegvesen (perhaps no API, just a web page? https://www.vegvesen.no/Kjoretoy/Kjop+og+salg/Kj%C3%B8ret%C3%B8yopplysninger)
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
