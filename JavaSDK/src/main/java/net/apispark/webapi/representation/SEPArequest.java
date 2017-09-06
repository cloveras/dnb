package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ```
{
  "account_id" : "123456789",
  "external_uid" : "99",
  "remote_iban": "XXXXX",
  "remote_bic": "XXXXX",
  "remote_name" : "The Man,
  "amount" : 100000,
  "subject" : "Enjoy"
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class SEPArequest implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String id;

    private java.lang.String account;

    private java.lang.String remote_iban;

    private java.lang.String remote_bic;

    private java.lang.String remote_account;

    private java.lang.Integer amount;

    private java.lang.String currency;

    /**
     * Returns the value of property "id". 
     * Unique
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
     * From-account
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
     * Returns the value of property "remote_account". 
     * 
     */
    public java.lang.String getRemote_account() {
        return remote_account;
    }

    /**
     * Updates the value of property "remote_account". 
     */
    public void setRemote_account(java.lang.String remote_account) {
        this.remote_account = remote_account;
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
