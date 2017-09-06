package net.apispark.webapi.security;

import static java.util.Objects.requireNonNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.apispark.webapi.security.AuthenticatorNotFoundException;
import net.apispark.webapi.security.ClientFactory;
import net.apispark.webapi.security.authenticators.Authenticator;
import net.apispark.webapi.security.authenticators.NoAuthAuthenticator;
import net.apispark.webapi.security.authenticators.CustomGlobalAuthenticator;

public class SecurityConfig {

    /**
     * Authenticator that does not provide authentication.
     */
    private static final NoAuthAuthenticator NO_AUTH_AUTHENTICATOR = new NoAuthAuthenticator();

    /**
     * Configurator for client runtime.
     */
    private final SecurityRuntimeConfigurator securityRuntimeConfigurator = new SecurityRuntimeConfigurator(this);

    /**
     * The list of schemes listed as API-level security requirements.
     */
    private final List<Class<? extends Authenticator>> globalRequirements = new ArrayList<>();

    /**
     * Maps schemes to configured authenticators to use for API calls.
     */
    private final Map<Class<? extends Authenticator>, Authenticator> configuredAuthenticators = new HashMap<>();

    /** The TLS Protocol to use for all token calls. */
    private String specificTlsProtocol;

    /** List of trusted certificates. */
    private final List<InputStream> trustedCertificates = new ArrayList<>();

    /** The ClientFactory used for the requests. */
    private ClientFactory clientFactory;

    public SecurityConfig() {
        // API Global requirements
    }

    ClientFactory getClientFactory() {
        // lazy initialization of ClientFactory to get all settings
        if (clientFactory == null) {
            ClientFactory.ClientFactoryBuilder clientFactoryBuilder = ClientFactory.builder();
            if (specificTlsProtocol != null) {
                clientFactoryBuilder.setSpecificTlsProtocol(specificTlsProtocol);
            }
            for (InputStream certificate : trustedCertificates) {
                clientFactoryBuilder.addTrustedCertificate(certificate);
            }
            clientFactory = clientFactoryBuilder.build();
        }
        return clientFactory;
    }

    /**
     * Sets the TLS Protocol to use for all token calls.
     *
     * @param specificTlsProtocol
     *            The TLS Protocol to use for all token calls.
     */
    public SecurityConfig setSpecificTlsProtocol(String specificTlsProtocol) {
        this.specificTlsProtocol = specificTlsProtocol;
        return this;
    }

    /**
     * Add SSL certificate to the list of trusted certificates.
     * Note: InputSteams are closed at first request.
     */
    public SecurityConfig addTrustedCertificate(InputStream... trustedCertificates) {
        for (InputStream certificate : trustedCertificates) {
            this.trustedCertificates.add(certificate);
        }
        return this;
    }

    // API Security Schemes

    // End API Security Schemes

    /**
     * Registers an {@link Authenticator} to be used as a fallback global authentication mechanism. This should only
     * be useful if the API definition defines no security schemes while the actual API uses one.
     *
     * For example, if the API definitions contains no security scheme definition, but the actual API expects call to
     * be authenticated using HTTP Basic, then this method should be called like this:
     *
     * <code>
     * configureCustomGlobalAuth(new HttpBasicAuthenticator(userId, password));
     * </code>
     *
     * @param globalAuth
     * @return this {@link SecurityConfig}
     */
    public SecurityConfig configureCustomGlobalAuth(Authenticator globalAuth) {
        requireNonNull(globalAuth);
        if (!globalRequirements.contains(CustomGlobalAuthenticator.class)) {
            globalRequirements.add(CustomGlobalAuthenticator.class);
        }
        configureCustomAuth(CustomGlobalAuthenticator.class, globalAuth);
        return this;
    }

    /**
     * Overrides an existing schemes by a possibly completely different authentication mechanism. This should only be
     * useful if the API definition defines security schemes that are incorrect.
     *
     * For example, if the API definition defines a security schemes named "queryToken" which says the caller should
     * place a token in a query parameter named "token", but the actual API expects a token named "t", then this method
     * should be called like this:
     *
     * <code>
     * configureCustomAuth(QueryTokenAuthenticator.class, new QueryApiKeyAuthenticator("t", tokenValue));
     * </code>
     *
     * @param scheme
     *            the scheme to override ; should be a class from net.apispark.webapi.security.authenticators
     * @param replacement
     *            the authenticator to use when the scheme is required, instead of the scheme's authenticator
     * @return this {@link SecurityConfig}
     */
    public SecurityConfig configureCustomAuth(
            Class<? extends Authenticator> scheme,
            Authenticator replacement) {
        requireNonNull(scheme);
        requireNonNull(replacement);
        configuredAuthenticators.put(scheme, replacement);
        return this;
    }

    private SecurityConfig configureAuth(Authenticator authenticator) {
        requireNonNull(authenticator);
        configuredAuthenticators.put(authenticator.getClass(), authenticator);
        return this;
    }

    protected Authenticator getConfiguredGlobalAuthenticator() {
        // Search configured Authenticator for global requirements
        for (Class<? extends Authenticator> authenticatorId : globalRequirements) {
            if (configuredAuthenticators.containsKey(authenticatorId)) {
                return configuredAuthenticators.get(authenticatorId);
            }
        }
        // No configured Authenticator found. Returns an instance of {@link NoAuthAuthenticator}.
        return NO_AUTH_AUTHENTICATOR;
    }

    /**
     * Picks a configured {@link Authenticator} instance corresponding to one of the provided schemes. If no schemes
     * are provided, then picks among the global ones.
     *
     * @param operationRequirements
     *            List of {@link Authenticator} accepted by the operation
     * @throws AuthenticatorNotFoundException
     *             if no compatible and configured {@link Authenticator} was found
     */
    protected Authenticator getConfiguredAuthenticator(Class<? extends Authenticator>... operationRequirements) {
        for (Class<? extends Authenticator> authenticatorId : operationRequirements) {
            if (configuredAuthenticators.containsKey(authenticatorId)) {
                return configuredAuthenticators.get(authenticatorId);
            }
        }
        throw new AuthenticatorNotFoundException("None of the security requirements are configured."
                + " Accepted security requirement: " + Arrays.toString(operationRequirements));
    }

    /**
     * Returns the configurator for client runtime
     */
    public SecurityRuntimeConfigurator getSecurityRuntimeConfigurator() {
        return securityRuntimeConfigurator;
    }

}
