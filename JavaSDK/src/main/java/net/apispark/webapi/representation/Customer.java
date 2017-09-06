package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Draft, needs update from actual properties below.

Also doubles as "Person", i.e: Not all Customer objects are DNB customers. A Customer is to be seen as a human person with certain attributes.

```
{
	“email”: "rune.bjerke@dnb.no", 
	“first_name”: “Rune”, 
	“last_name”: “Bjerke”, 
	“gender”: “m”, 
	“adr_street”: “Dronning Eufemias gate 30”, 
	“adr_post_code”: “0191”,
	"adr_city”: “Oslo”, 
	“adr_country”: “NO”, 
	“phone": “+47 04800”, 
	“birthday”: “1960-06-17”, 
	“created”: “2015-07-01T10:42:00+01:00”, 
	“updated”: “2015-10-01T10:42:00+01:00”,
}
```
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Customer implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String Id;

    private java.lang.String status;

    private java.lang.String program;

    private java.lang.String Name;

    private java.lang.String Address;

    private java.lang.String Postcode;

    private java.lang.String City;

    private java.lang.String Country;

    private java.lang.String Email;

    private java.lang.String Phone;

    private java.lang.String Website;

    private java.lang.String welcomepackage;

    private java.lang.String Notesplaceholder;

    /**
     * Returns the value of property "Id". 
     * Person: Personal number, 11 digits.
Company: Organization number, 9 digits.
     */
    public java.lang.String getId() {
        return Id;
    }

    /**
     * Updates the value of property "Id". 
     */
    public void setId(java.lang.String Id) {
        this.Id = Id;
    }

    /**
     * Returns the value of property "status". 
     * Active, pending, closed, etc
     */
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * Updates the value of property "status". 
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    /**
     * Returns the value of property "program". 
     * SAGA, etc
     */
    public java.lang.String getProgram() {
        return program;
    }

    /**
     * Updates the value of property "program". 
     */
    public void setProgram(java.lang.String program) {
        this.program = program;
    }

    /**
     * Returns the value of property "Name". 
     * Person: Rune Bjerke.
Company: DNB
     */
    public java.lang.String getName() {
        return Name;
    }

    /**
     * Updates the value of property "Name". 
     */
    public void setName(java.lang.String Name) {
        this.Name = Name;
    }

    /**
     * Returns the value of property "Address". 
     * Street address.
     */
    public java.lang.String getAddress() {
        return Address;
    }

    /**
     * Updates the value of property "Address". 
     */
    public void setAddress(java.lang.String Address) {
        this.Address = Address;
    }

    /**
     * Returns the value of property "Postcode". 
     * 4 digits if in Norway,
     */
    public java.lang.String getPostcode() {
        return Postcode;
    }

    /**
     * Updates the value of property "Postcode". 
     */
    public void setPostcode(java.lang.String Postcode) {
        this.Postcode = Postcode;
    }

    /**
     * Returns the value of property "City". 
     * Oslo
     */
    public java.lang.String getCity() {
        return City;
    }

    /**
     * Updates the value of property "City". 
     */
    public void setCity(java.lang.String City) {
        this.City = City;
    }

    /**
     * Returns the value of property "Country". 
     * "NO". http://www.iso.org/iso/country_codes.htm
     */
    public java.lang.String getCountry() {
        return Country;
    }

    /**
     * Updates the value of property "Country". 
     */
    public void setCountry(java.lang.String Country) {
        this.Country = Country;
    }

    /**
     * Returns the value of property "Email". 
     * example@example.com
     */
    public java.lang.String getEmail() {
        return Email;
    }

    /**
     * Updates the value of property "Email". 
     */
    public void setEmail(java.lang.String Email) {
        this.Email = Email;
    }

    /**
     * Returns the value of property "Phone". 
     * +47 04800
     */
    public java.lang.String getPhone() {
        return Phone;
    }

    /**
     * Updates the value of property "Phone". 
     */
    public void setPhone(java.lang.String Phone) {
        this.Phone = Phone;
    }

    /**
     * Returns the value of property "Website". 
     * dnb.no
     */
    public java.lang.String getWebsite() {
        return Website;
    }

    /**
     * Updates the value of property "Website". 
     */
    public void setWebsite(java.lang.String Website) {
        this.Website = Website;
    }

    /**
     * Returns the value of property "welcomepackage". 
     * Id/tracking details for welcome package
     */
    public java.lang.String getWelcomepackage() {
        return welcomepackage;
    }

    /**
     * Updates the value of property "welcomepackage". 
     */
    public void setWelcomepackage(java.lang.String welcomepackage) {
        this.welcomepackage = welcomepackage;
    }

    /**
     * Returns the value of property "Notesplaceholder". 
     * {
    "email": "rune.bjerke@dnb.no",
    "first_name": "Rune",
    "last_name": "Bjerke",
    "gender": "m",
    "adr_street": "Dronning Eufemias gate 30",
    "adr_post_code": "0191",
    "adr_city": "Oslo",
    "adr_country": "NO",
    “phone": "+47 04800",
    "birthday": "1960-06-17",
    "created": "2015-07-01T10:42:00+01:00",     "updated": "2015-10-01T10:42:00+01:00",
}
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
