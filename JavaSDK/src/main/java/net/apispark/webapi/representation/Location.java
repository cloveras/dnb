package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Location implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.Double latitude;

    private java.lang.Double longditude;

    private java.lang.String description;

    /**
     * Returns the value of property "id". 
     * Internal id
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
     * Returns the value of property "latitude". 
     * 
     */
    public java.lang.Double getLatitude() {
        return latitude;
    }

    /**
     * Updates the value of property "latitude". 
     */
    public void setLatitude(java.lang.Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the value of property "longditude". 
     * 
     */
    public java.lang.Double getLongditude() {
        return longditude;
    }

    /**
     * Updates the value of property "longditude". 
     */
    public void setLongditude(java.lang.Double longditude) {
        this.longditude = longditude;
    }

    /**
     * Returns the value of property "description". 
     * Name of place, or similar
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
