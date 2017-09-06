package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * For now: One common Insurance object for all types (house, car, health, etc).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Insurance implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String type;

    private java.lang.String name;

    private java.lang.Integer amount;

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
     * Returns the value of property "name". 
     * 
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Updates the value of property "name". 
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Returns the value of property "amount". 
     * 
     */
    public java.lang.Integer getAmount() {
        return amount;
    }

    /**
     * Updates the value of property "amount". 
     */
    public void setAmount(java.lang.Integer amount) {
        this.amount = amount;
    }

}
