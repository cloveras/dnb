package net.apispark.webapi.security.ssl;

import org.restlet.Client;
import org.restlet.engine.ssl.DefaultSslContextFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientSslContextFactory extends DefaultSslContextFactory {

    /** Store of the trusted certificates. */
    private KeyStore trustStore;

    private List<InputStream> trustedCertificates = new ArrayList<>();

    private Client client;

    public ClientSslContextFactory(Client client) {
        this.client = client;
    }

    public ClientSslContextFactory addTrustedCertificates(List<InputStream> trustedCertificateInputStreams) {
        trustedCertificates.addAll(trustedCertificateInputStreams);
        return this;
    }

    public ClientSslContextFactory addTrustedCertificate(InputStream trustedCertificateInputStream) {
        trustedCertificates.add(trustedCertificateInputStream);
        return this;
    }

    public ClientSslContextFactory setTrustStore(KeyStore trustStore) {
        this.trustStore = trustStore;
        return this;
    }

    @Override
    public SSLContext createSslContext() throws Exception {
        init(client.getContext().getParameters());

        TrustManagerFactory tmf = createTrustManagerFactory();

        // Creates the SSL context
        SSLContext sslContext = SSLContext.getInstance(getProtocol());

        SecureRandom sr = null;
        if (getSecureRandomAlgorithm() != null) {
            sr = SecureRandom.getInstance(getSecureRandomAlgorithm());
        }

        sslContext.init(null,
                tmf != null ? tmf.getTrustManagers() : null,
                sr);

        // Wraps the SSL context to be able to set cipher suites and other
        // properties after SSL engine creation for example
        return createWrapper(sslContext);
    }

    public TrustManagerFactory createTrustManagerFactory() {
        try {
            if (trustStore == null) {
                trustStore = createKeyStore(getTrustStoreType(), getTrustStoreProvider());
                trustStore.load(null, null);
                // Load all trusted certificates
                loadCertificates(trustStore, trustedCertificates);
            }

            // Creates the trust-manager factory.
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);

            return tmf;
        } catch (Exception e) {
            throw new RuntimeException("Trust store initialization error", e);
        }
    }

    /**
     * Creates an empty keystore.
     *
     * @throws KeyStoreException
     * @throws NoSuchProviderException
     */
    private KeyStore createKeyStore(String keyStoreType, String keyStoreProvider) throws KeyStoreException,
            NoSuchProviderException {
        String notNullKeyStoreType = (keyStoreType != null) ? keyStoreType : KeyStore.getDefaultType();

        return (keyStoreProvider != null) ?
                KeyStore.getInstance(notNullKeyStoreType, keyStoreProvider) :
                KeyStore.getInstance(notNullKeyStoreType);
    }

    private void loadCertificates(KeyStore keyStore, List<InputStream> certificates) throws CertificateException,
            KeyStoreException, IOException {
        for (InputStream certificate : certificates) {
            try (InputStream caInput = new BufferedInputStream(certificate)) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                Certificate ca = cf.generateCertificate(caInput);

                // add a certificate with a unique key
                keyStore.setCertificateEntry(UUID.randomUUID().toString(), ca);
            }
        }
    }

}
