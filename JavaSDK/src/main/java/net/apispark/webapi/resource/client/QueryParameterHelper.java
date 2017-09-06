package net.apispark.webapi.resource.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.restlet.resource.ClientResource;

public class QueryParameterHelper {

    private QueryParameterHelper() {

    }

    public static void addQueryParameter(ClientResource client, String name, String value) {
        if (value != null) {
            client.addQueryParameter(name, value);
        }
    }

    public static void addQueryParameter(ClientResource client, String name, Integer value) {
        addQueryParameter(client, name, Integer.toString(value));
    }

    public static void addQueryParameter(ClientResource client, String name, Long value) {
        addQueryParameter(client, name, Long.toString(value));
    }

    public static void addQueryParameter(ClientResource client, String name, Double value) {
        addQueryParameter(client, name, Double.toString(value));
    }

    public static void addQueryParameter(ClientResource client, String name, Float value) {
        addQueryParameter(client, name, Float.toString(value));
    }

    public static void addQueryParameter(ClientResource client, String name, Boolean value) {
        addQueryParameter(client, name, Boolean.toString(value));
    }

    public static void addTimestampQueryParameter(ClientResource client, String name, Date value) {
        addQueryParameter(client, name, value.getTime());
    }

    public static void addDateQueryParameter(ClientResource client, String name, Date value) {
        DateFormat dateFormat = new SimpleDateFormat(net.apispark.webapi.Config.DATE_FORMAT);
        addQueryParameter(client, name, dateFormat.format(value));
    }

    public static void addDatetimeQueryParameter(ClientResource client, String name, Date value) {
        DateFormat datetimeFormat = new SimpleDateFormat(net.apispark.webapi.Config.DATETIME_FORMAT);
        addQueryParameter(client, name, datetimeFormat.format(value));
    }

}
