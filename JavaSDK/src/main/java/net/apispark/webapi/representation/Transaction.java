package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ```
{
    "transactions": [
        {
				    "id": "<id>",
            "merchant_id": "<id>",
            "account_balance": 12325,
            "amount": -20,
            "created": "2016-08-05T08:00:00Z",
            "currency": "NOK",
            "description": "DNB Børsbaren",
            "notes": "Dobbel espresso",
            "settled": "2016-08-05T08:00:00Z ",
            "category": "restaurant"
        },
        {
             "id": "<id>",
            "merchant_id": "<id>",
						"account_balance": 12305,
            "amount": -20,
            "created": "2016-08-05T08:05:00Z",
            "currency": "NOK",
            "description": "DNB Børsbaren",
            "notes": "Dobbel macchiato",
            "settled": "2016-08-05T08:05:00Z ",
            "category": "restaurant"
        },
    ]
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Transaction implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String account;

    private java.lang.String type;

    private java.lang.String currency;

    private java.lang.String amount;

    private java.lang.String reference;

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
     * Returns the value of property "account". 
     * 
     */
    public java.lang.String getAccount() {
        return account;
    }

    /**
     * Updates the value of property "account". 
     */
    public void setAccount(java.lang.String account) {
        this.account = account;
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
     * Returns the value of property "currency". 
     * 
     */
    public java.lang.String getCurrency() {
        return currency;
    }

    /**
     * Updates the value of property "currency". 
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }

    /**
     * Returns the value of property "amount". 
     * 
     */
    public java.lang.String getAmount() {
        return amount;
    }

    /**
     * Updates the value of property "amount". 
     */
    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    /**
     * Returns the value of property "reference". 
     * Description
     */
    public java.lang.String getReference() {
        return reference;
    }

    /**
     * Updates the value of property "reference". 
     */
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }

    /**
     * Returns the value of property "Notesplaceholder". 
     * 
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
