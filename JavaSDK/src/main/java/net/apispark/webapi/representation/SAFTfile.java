package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Standard Audit File - Tax

Example file: https://github.com/Skatteetaten/saf-t/blob/master/Example%20Files/ExampleFile%20SAF-T%20Financial_999999999_20161125213512.xml
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class SAFTfile implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String type;

    private java.lang.String fileurl;

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
     * Returns the value of property "fileurl". 
     * (Unless all data is included in Object)
     */
    public java.lang.String getFileurl() {
        return fileurl;
    }

    /**
     * Updates the value of property "fileurl". 
     */
    public void setFileurl(java.lang.String fileurl) {
        this.fileurl = fileurl;
    }

}
