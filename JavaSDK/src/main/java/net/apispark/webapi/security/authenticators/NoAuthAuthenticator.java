package net.apispark.webapi.security.authenticators;

import org.restlet.resource.ClientResource;

/**
 * Authenticator that does not provide authentication.
 */
public class NoAuthAuthenticator implements Authenticator {

    @Override
    public void configure(ClientResource clientResource) {
        // Nothing
    }

}