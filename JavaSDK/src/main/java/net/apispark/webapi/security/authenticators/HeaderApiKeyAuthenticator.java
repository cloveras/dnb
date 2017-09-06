package net.apispark.webapi.security.authenticators;

import static java.lang.String.format;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.engine.header.HeaderConstants;
import org.restlet.engine.util.StringUtils;
import org.restlet.resource.ClientResource;

/**
 * Configures authentication based on the value of a specific header.
 */
public class HeaderApiKeyAuthenticator implements Authenticator {
    /** The name of the authentication header. */
    private final String headerName;

    /** The value of the header. */
    private final String token;

    /**
     * Constructor.
     * 
     * @param headerName
     *            The name of the authentication header.
     * @param token
     *            The value of the header.
     */
    public HeaderApiKeyAuthenticator(String headerName, String token) {
        // check format of Authorization Header
        if (HeaderConstants.HEADER_AUTHORIZATION.equals(headerName)) {
            checkAuthorizationHeader(token);
        }
        this.headerName = headerName;
        this.token = token;
    }

    @Override
    public void configure(ClientResource clientResource) {
        if (HeaderConstants.HEADER_AUTHORIZATION.equals(headerName)) {
            clientResource.getRequest().setChallengeResponse(getChallengeResponse(token));
        } else {
            clientResource.getRequest().getHeaders().set(headerName, token);
        }
    }

    private static ChallengeResponse getChallengeResponse(String token) {
        int sep = token.indexOf(" ");
        String scheme = token.substring(0, sep);
        String rawValue = token.substring(sep + 1);
        ChallengeResponse challengeResponse = new ChallengeResponse(new ChallengeScheme("HTTP_" + scheme, scheme));
        challengeResponse.setRawValue(rawValue);
        return challengeResponse;
    }

    private static void checkAuthorizationHeader(String token) {
        if (StringUtils.isNullOrEmpty(token) || token.indexOf(" ") == -1) {
            throw new IllegalArgumentException(
                    format("Authorization header invalid: %s.Should be '<scheme> <value>'.", token));
        }
    }
}
