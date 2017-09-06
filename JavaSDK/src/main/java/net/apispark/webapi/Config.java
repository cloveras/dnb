package net.apispark.webapi;

import net.apispark.webapi.security.SecurityConfig;

public class Config {

    /**
     * Pattern used for serialization and deserialization of values of type "date" in payloads and query parameters.
     * Note that timestamps are still accepted as input for deserialization.
     *
     * @see java.text.DateFormat
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Pattern used for serialization and deserialization of values of type "datetime" in payloads and query parameters.
     * Note that timestamps are still accepted as input for deserialization.
     *
     * @see java.text.DateFormat
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private String basePath = "https://a1r3mp0.restletmocks.net";

    private final SecurityConfig securityConfig = new SecurityConfig();

    /**
     * Returns the base path of the API.
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Define the base path of the API.
     */
    public Config setBasePath(String basePath) {
        if (basePath == null || basePath.isEmpty()) {
            throw new IllegalArgumentException("basePath cannot be null or empty");
        }
        this.basePath = basePath;
        return this;
    }

    /**
     * Returns the security configuration used to configure the API authentication schemes.
     */
    public SecurityConfig getSecurityConfig() {
        return securityConfig;
    }

}
