package net.apispark.webapi.security.authenticators;

import org.restlet.resource.ClientResource;

/**
 * Configures a client resource so that it is able to connect to a remote resource that requires some kind of
 * authentication.
 */
public interface Authenticator {
    void configure(ClientResource clientResource);
}
