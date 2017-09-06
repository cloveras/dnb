package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ```
{
"id": "01",
"account": "123456789",
"amount": 100000",
"currency": "NOK",
"remote_iban": "XXXXX",
"remote_bic": "XXXXX",
"remote_name": "The Man",
"state": "success",
"created": "<timestamp>",
"updated": "<timestamp>",
}

```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class SEPAresponse implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String account;

    private java.lang.Integer amount;

    private java.lang.String remote_iban;

    private java.lang.String remote_bic;

    private java.lang.String remote_name;

    private java.lang.String state;

    private java.lang.String created;

    private java.lang.String updated;

    private java.lang.String currency;

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

    /**
     * Returns the value of property "remote_iban". 
     * 
     */
    public java.lang.String getRemote_iban() {
        return remote_iban;
    }

    /**
     * Updates the value of property "remote_iban". 
     */
    public void setRemote_iban(java.lang.String remote_iban) {
        this.remote_iban = remote_iban;
    }

    /**
     * Returns the value of property "remote_bic". 
     * 
     */
    @com.fasterxml.jackson.annotation.JsonProperty("remote:_bic")
    public java.lang.String getRemote_bic() {
        return remote_bic;
    }

    /**
     * Updates the value of property "remote_bic". 
     */
    public void setRemote_bic(java.lang.String remote_bic) {
        this.remote_bic = remote_bic;
    }

    /**
     * Returns the value of property "remote_name". 
     * 
     */
    public java.lang.String getRemote_name() {
        return remote_name;
    }

    /**
     * Updates the value of property "remote_name". 
     */
    public void setRemote_name(java.lang.String remote_name) {
        this.remote_name = remote_name;
    }

    /**
     * Returns the value of property "state". 
     * 
     */
    public java.lang.String getState() {
        return state;
    }

    /**
     * Updates the value of property "state". 
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }

    /**
     * Returns the value of property "created". 
     * 
     */
    public java.lang.String getCreated() {
        return created;
    }

    /**
     * Updates the value of property "created". 
     */
    public void setCreated(java.lang.String created) {
        this.created = created;
    }

    /**
     * Returns the value of property "updated". 
     * 
     */
    public java.lang.String getUpdated() {
        return updated;
    }

    /**
     * Updates the value of property "updated". 
     */
    public void setUpdated(java.lang.String updated) {
        this.updated = updated;
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

}
