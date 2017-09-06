package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * For now: One kind of Account for all.

```
{
		"number": "05000000000",
		"type": "1",
		"name": "Gordon Gecko's savings account",
		"currency": "NOK",
		"balance": 999999999,
		"IBAN": "NO93 8601 1117 947",
		"SWIFT": "DNBANOKKXXX",
		"created": "2010-07-01T10:42:00+01:00",
		"updated": "2015-07-01T10:42:00+01:00",
		"locked": 0,
		"notes": "No lunch for you"
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Account implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String number;

    private java.lang.String type;

    private java.lang.String name;

    private java.lang.String currency;

    private java.lang.Integer balance;

    private java.lang.String IBAN;

    private java.lang.String swift;

    private java.util.Date created;

    private java.util.Date updated;

    private java.lang.Boolean locked;

    private java.lang.String notes;

    /**
     * Returns the value of property "number". 
     * 11 digits. Bank, etc can be determined from this: https://no.wikipedia.org/wiki/Kontonummer
     */
    public java.lang.String getNumber() {
        return number;
    }

    /**
     * Updates the value of property "number". 
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }

    /**
     * Returns the value of property "type". 
     * Internal use, probably.
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
     * Friendly name.
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
     * Returns the value of property "currency". 
     * ISO 4217: alpha 3-letter upcase e.g EUR
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
     * Returns the value of property "balance". 
     * 
     */
    public java.lang.Integer getBalance() {
        return balance;
    }

    /**
     * Updates the value of property "balance". 
     */
    public void setBalance(java.lang.Integer balance) {
        this.balance = balance;
    }

    /**
     * Returns the value of property "IBAN". 
     * ISO 13616:2007: https://www.iso.org/standard/41031.html
     */
    public java.lang.String getIBAN() {
        return IBAN;
    }

    /**
     * Updates the value of property "IBAN". 
     */
    public void setIBAN(java.lang.String IBAN) {
        this.IBAN = IBAN;
    }

    /**
     * Returns the value of property "swift". 
     * ISO 9362: https://en.wikipedia.org/wiki/ISO_9362
     */
    public java.lang.String getSwift() {
        return swift;
    }

    /**
     * Updates the value of property "swift". 
     */
    public void setSwift(java.lang.String swift) {
        this.swift = swift;
    }

    /**
     * Returns the value of property "created". 
     * ISO 8601: www.iso.org/iso/home/standards/iso8601.htm
     */
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = net.apispark.webapi.Config.DATETIME_FORMAT)
    public java.util.Date getCreated() {
        return created;
    }

    /**
     * Updates the value of property "created". 
     */
    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    /**
     * Returns the value of property "updated". 
     * ISO 8601: www.iso.org/iso/home/standards/iso8601.htm
     */
    @com.fasterxml.jackson.annotation.JsonFormat(shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING, pattern = net.apispark.webapi.Config.DATETIME_FORMAT)
    public java.util.Date getUpdated() {
        return updated;
    }

    /**
     * Updates the value of property "updated". 
     */
    public void setUpdated(java.util.Date updated) {
        this.updated = updated;
    }

    /**
     * Returns the value of property "locked". 
     * Is this account locked?
     */
    public java.lang.Boolean getLocked() {
        return locked;
    }

    /**
     * Updates the value of property "locked". 
     */
    public void setLocked(java.lang.Boolean locked) {
        this.locked = locked;
    }

    /**
     * Returns the value of property "notes". 
     * 
     */
    public java.lang.String getNotes() {
        return notes;
    }

    /**
     * Updates the value of property "notes". 
     */
    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }

}
