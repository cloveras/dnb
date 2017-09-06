package net.apispark.webapi.security.authenticators;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;

/**
 * Configures authentication based on OAuth2 token. This authenticator assumes the token is valid.
 */
public class OAuth2Authenticator implements Authenticator {
    /** The OAuth2 token value. */
    private final String token;

    /**
     * Constructor.
     * 
     * @param token
     *            The OAuth2 token value.
     */
    public OAuth2Authenticator(String token) {
        this.token = token;
    }

    @Override
    public void configure(ClientResource clientResource) {
        ChallengeResponse challengeResponse = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        challengeResponse.setRawValue(token);
        clientResource.setChallengeResponse(challengeResponse);
    }

}