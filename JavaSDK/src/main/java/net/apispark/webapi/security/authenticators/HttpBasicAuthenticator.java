package net.apispark.webapi.security.authenticators;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;

/**
 * Configures authentication based on the HTTP Basic authentication scheme.
 */
public class HttpBasicAuthenticator implements Authenticator {
    /** The user identifier. */
    private final String userId;

    /** The user password. */
    private final String password;

    /**
     * Constructor.
     * 
     * @param userId
     *            The user identifier.
     * @param password
     *            The user password.
     */
    public HttpBasicAuthenticator(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    @Override
    public void configure(ClientResource clientResource) {
        clientResource.setChallengeResponse(
                new ChallengeResponse(ChallengeScheme.HTTP_BASIC, userId, password));
    }

}
