package net.apispark.webapi.security.authenticators.defined;

import org.restlet.resource.ClientResource;

public class APIkeyAuthenticator implements net.apispark.webapi.security.authenticators.Authenticator {

    public APIkeyAuthenticator(String token) {
        // Define your own authenticator.
    }

    @Override
    public void configure(ClientResource clientResource) {
      // Add you custom ChallengeResponse to the ClientResource
    }
}
