package net.apispark.webapi.security;

import net.apispark.webapi.security.authenticators.Authenticator;
import org.restlet.resource.ClientResource;

/**
 * Configure client security for runtime.
 */
public class SecurityRuntimeConfigurator {

    private final SecurityConfig securityConfig;

    SecurityRuntimeConfigurator(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    /**
     * Configure the {@link ClientResource} according to global security requirements.
     */
    public void configure(ClientResource clientResource) {
        securityConfig.getClientFactory().configureClientResource(clientResource);
        securityConfig.getConfiguredGlobalAuthenticator().configure(clientResource);
    }

    public OperationRequirementConfig accept(Class<? extends Authenticator>... operationRequirements) {
        return new OperationRequirementConfig(operationRequirements);
    }

    public class OperationRequirementConfig {

        private final Class<? extends Authenticator>[] operationRequirements;

        private OperationRequirementConfig(Class<? extends Authenticator>[] operationRequirements) {
            this.operationRequirements = operationRequirements;
        }

        /**
         * Configure the {@link ClientResource} according to operation security requirements.
         */
        public void configure(ClientResource clientResource) {
            securityConfig.getClientFactory().configureClientResource(clientResource);
            securityConfig.getConfiguredAuthenticator(operationRequirements).configure(clientResource);
        }

    }
}
