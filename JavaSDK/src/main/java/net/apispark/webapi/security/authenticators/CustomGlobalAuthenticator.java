package net.apispark.webapi.security.authenticators;

import net.apispark.webapi.security.authenticators.Authenticator;
import net.apispark.webapi.security.SecurityConfig;
import org.restlet.resource.ClientResource;

/**
 * A marker class that serves as the id of the Authenticator that can be configured through
 * {@link SecurityConfig#configureCustomGlobalAuth}.
 */
public class CustomGlobalAuthenticator implements Authenticator {

    @Override
    public void configure(ClientResource clientResource) {
        // nothing
    }

}