package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AnonymousRepresentation2 implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.Long day;

    private java.lang.Long open_hour;

    private java.lang.Long open_minute;

    private java.lang.Long close_hour;

    private java.lang.String close_minutes;

    /**
     * Returns the value of property "day". 
     * Monday: 1, Tuesday: 2, Wednesday: 3, Thursday: 4, Friday: 5, Saturday: 6, Sunday: 7
     */
    public java.lang.Long getDay() {
        return day;
    }

    /**
     * Updates the value of property "day". 
     */
    public void setDay(java.lang.Long day) {
        this.day = day;
    }

    /**
     * Returns the value of property "open_hour". 
     * The "07" in "07:30".
     */
    public java.lang.Long getOpen_hour() {
        return open_hour;
    }

    /**
     * Updates the value of property "open_hour". 
     */
    public void setOpen_hour(java.lang.Long open_hour) {
        this.open_hour = open_hour;
    }

    /**
     * Returns the value of property "open_minute". 
     * The "30" in "07:30".
     */
    public java.lang.Long getOpen_minute() {
        return open_minute;
    }

    /**
     * Updates the value of property "open_minute". 
     */
    public void setOpen_minute(java.lang.Long open_minute) {
        this.open_minute = open_minute;
    }

    /**
     * Returns the value of property "close_hour". 
     * The "17" in "17:30".
     */
    public java.lang.Long getClose_hour() {
        return close_hour;
    }

    /**
     * Updates the value of property "close_hour". 
     */
    public void setClose_hour(java.lang.Long close_hour) {
        this.close_hour = close_hour;
    }

    /**
     * Returns the value of property "close_minutes". 
     * The "30" in "17:30".
     */
    public java.lang.String getClose_minutes() {
        return close_minutes;
    }

    /**
     * Updates the value of property "close_minutes". 
     */
    public void setClose_minutes(java.lang.String close_minutes) {
        this.close_minutes = close_minutes;
    }

}
