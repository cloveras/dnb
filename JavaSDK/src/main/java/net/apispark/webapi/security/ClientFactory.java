package net.apispark.webapi.security;

import net.apispark.webapi.security.authenticators.HeaderApiKeyAuthenticator;
import net.apispark.webapi.security.authenticators.HttpBasicAuthenticator;
import net.apispark.webapi.security.authenticators.OAuth2Authenticator;
import net.apispark.webapi.security.authenticators.OAuth2ImplicitFlowAuthenticator;
import net.apispark.webapi.security.authenticators.OAuth2ResourceOwnerPasswordFlowAuthenticator;
import net.apispark.webapi.security.authenticators.QueryApiKeyAuthentication;
import net.apispark.webapi.security.authenticators.Authenticator;
import net.apispark.webapi.security.ssl.ClientSslContextFactory;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;

import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

public class ClientFactory {


    private Client client;
    private Authenticator authenticator;

    public static ClientFactoryBuilder builder() {
        return new ClientFactory().new ClientFactoryBuilder();
    }

    private ClientFactory() {
        client = new Client(new Context(), Arrays.asList(Protocol.HTTP, Protocol.HTTPS));
    }

    public ClientResource newClientResource(String uri) {
        return configureClientResource(new ClientResource(uri));
    }

    public ClientResource newClientResource(URI uri) {
        return configureClientResource(new ClientResource(uri));
    }

    public ClientResource newClientResource(Method method, String uri) {
        return configureClientResource(new ClientResource(method, uri));
    }

    public ClientResource newClientResource(Method method, URI uri) {
        return configureClientResource(new ClientResource(method, uri));
    }

    /**
     * Lower-level method to configure a client resource according to factory settings
     */
    public ClientResource configureClientResource(ClientResource clientResource) {
        // add template dispatcher to resolve path variables in URL via clientResource.setAttribute()
        org.restlet.engine.util.TemplateDispatcher templateDispatcher = new org.restlet.engine.util.TemplateDispatcher();
        templateDispatcher.setContext(client.getContext());
        clientResource.setNext(templateDispatcher);

        // add client
        templateDispatcher.setNext(client);

        if (authenticator != null) {
            authenticator.configure(clientResource);
        }
        
        return clientResource;
    }

    public class ClientFactoryBuilder {

        private ClientSslContextFactory sslContextFactory;

        /**
         * Use the TLS v1.2 protocol.
         */
        public ClientFactoryBuilder setTlsv12Protocol() {
            return setSpecificTlsProtocol("TLSv1.2");
        }

        /**
         * Define the TLS protocol to use.
         * For example: "TLS" (default), "TLSv1.1", "TLSv1.2".
         */
        public ClientFactoryBuilder setSpecificTlsProtocol(String setSpecificTlsProtocol) {
            client.getContext().getParameters().add("protocol", setSpecificTlsProtocol);
            return this;
        }

        /**
         * Add SSL certificate to the list of trusted certificates.
         * Note: InputSteams are closed when calling {@link #build()}.
         */
        public ClientFactoryBuilder addTrustedCertificate(InputStream... trustedCertificates) {
            for (InputStream certificate : trustedCertificates) {
                withSslContextFactory().addTrustedCertificate(certificate);
            }
            return this;
        }

        public ClientFactoryBuilder withBasicAuth(String userid, String password) {
            authenticator = new HttpBasicAuthenticator(userid, password);
            return this;
        }
        public ClientFactoryBuilder withHeaderApiKey(String headerName, String headerValue) {
            authenticator = new HeaderApiKeyAuthenticator(headerName, headerValue);
            return this;

        }
        public ClientFactoryBuilder withQueryParameterApiKey(String queryParameterName, String queryParameterValue) {
            authenticator = new QueryApiKeyAuthentication(queryParameterName, queryParameterValue);
            return this;

        }
        public ClientFactoryBuilder withOAuth2(String token) {
            authenticator = new OAuth2Authenticator(token);
            return this;

        }
        public ClientFactoryBuilder withOAuth2Implicit(OAuth2ImplicitFlowAuthenticator oAuth2ImplicitFlowAuthenticator) {
            authenticator = oAuth2ImplicitFlowAuthenticator;
            return this;

        }
        public ClientFactoryBuilder withOAuth2ResourceOwnerPassword(OAuth2ResourceOwnerPasswordFlowAuthenticator oAuth2ResourceOwnerPasswordFlowAuthenticator) {
            authenticator = oAuth2ResourceOwnerPasswordFlowAuthenticator;
            return this;

        }

        private ClientSslContextFactory withSslContextFactory() {
            if (sslContextFactory == null) {
                sslContextFactory = new ClientSslContextFactory(client);
                client.getContext().getAttributes().put("sslContextFactory", sslContextFactory);
            }
            return sslContextFactory;
        }

        public ClientFactory build() {
            if (sslContextFactory != null) {
                sslContextFactory.createTrustManagerFactory();
            }
            return ClientFactory.this;
        }

    }
}

