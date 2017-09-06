package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Vippscustomer implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String phone;

    private java.lang.String name;

    private java.lang.String accountnumber;

    private java.lang.String cardnumber;

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
     * Returns the value of property "phone". 
     * 
     */
    public java.lang.String getPhone() {
        return phone;
    }

    /**
     * Updates the value of property "phone". 
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
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
     * Returns the value of property "accountnumber". 
     * 
     */
    public java.lang.String getAccountnumber() {
        return accountnumber;
    }

    /**
     * Updates the value of property "accountnumber". 
     */
    public void setAccountnumber(java.lang.String accountnumber) {
        this.accountnumber = accountnumber;
    }

    /**
     * Returns the value of property "cardnumber". 
     * 
     */
    public java.lang.String getCardnumber() {
        return cardnumber;
    }

    /**
     * Updates the value of property "cardnumber". 
     */
    public void setCardnumber(java.lang.String cardnumber) {
        this.cardnumber = cardnumber;
    }

}
