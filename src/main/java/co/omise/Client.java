package co.omise;

import co.omise.models.Model;
import co.omise.models.OmiseException;
import co.omise.models.OmiseObjectBase;
import co.omise.requests.Request;
import co.omise.requests.Requester;
import co.omise.requests.RequesterImpl;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Client is the main entry point to the Omise Java library.
 * Use this client to send {@link Request} to the Omise API
 * <p>
 * Clients are thread-safe and a single instance can be shared
 * for use by multiple threads.
 * </p>
 *
 * @see Config
 * @see Requester
 */
public class Client {

    private final OkHttpClient httpClient;
    private Requester requester;

    /**
     * Creates a Client with just the secret key. Always use this constructor to avoid transmitting any card data
     * through your own server. (since token creation will fail without a public key.)
     *
     * @param secretKey The key with {@code skey_} prefix.
     * @throws ClientException if client configuration fails (e.g. when TLSv1.2 is not supported)
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     */
    public Client(String secretKey) throws ClientException {
        this(null, secretKey);
    }

    /**
     * Creates a Client that sends the specified API version string in the header to access an earlier version
     * of the Omise API.
     *
     * <p>
     * Note: Please ensure to have at least one of the keys supplied to have the client function correctly.
     * </p>
     *
     * @param publicKey The key with {@code pkey_} prefix.
     * @param secretKey The key with {@code skey_} prefix.
     * @throws ClientException if client configuration fails (e.g. when TLSv1.2 is not supported)
     * @see Serializer
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     * @see <a href="https://www.omise.co/api-versioning">Versioning</a>
     */
    public Client(String publicKey, String secretKey) throws ClientException {
        Config config = new Config(Endpoint.API_VERSION, publicKey, secretKey);
        httpClient = buildHttpClient(config);

        Serializer serializer = Serializer.defaultSerializer();
        requester = new RequesterImpl(httpClient, serializer);
    }

    /**
     * Creates a Client that sends the specified API version string in the header to access an earlier version
     * of the Omise API. This is an overloaded version of the previous constructor to make it easy for users
     * to supply their own implementations of Requester.
     *
     * @param requester Requester implementation that will be used to send requests and parse their results.
     * @see Serializer
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     * @see <a href="https://www.omise.co/api-versioning">Versioning</a>
     */
    public Client(Requester requester) {
        this.requester = requester;
        this.httpClient = requester.getHttpClient();
    }

    /**
     * Returns a new {@link OkHttpClient} to use for performing {@link Request}(s). Override this to customize the HTTP
     * client. This method will be called once during construction and the result will be cached internally.
     * <p>
     * It is generally a good idea to implement this by adding to the builder created from
     * <code>super.buildHttpClient(config).newBuilder()</code> so that all configurations are properly applied and SSL
     * certificates are pinned.
     * </p>
     *
     * @param config A {@link Config} object built from constructor parameters.
     * @return A new {@link OkHttpClient} object for connecting to the Omise API.
     * @throws ClientException if client configuration fails (e.g. when TLSv1.2 is not supported)
     */
    protected OkHttpClient buildHttpClient(Config config) throws ClientException {
        CertificatePinner.Builder pinner = new CertificatePinner.Builder();
        for (Endpoint endpoint : Endpoint.all) {
            pinner = pinner.add(endpoint.host(), endpoint.certificateHash());
        }

        SSLContext sslContext;
        X509TrustManager trustManager;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);

            trustManager = getX509TrustManager();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new ClientException(e);
        }

        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .build();

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                .addInterceptor(new Configurer(config))
                .connectionSpecs(Collections.singletonList(spec))
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Gets x509 trust manager.
     *
     * @return the x509 trust manager
     * @throws KeyStoreException        the key store exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    protected X509TrustManager getX509TrustManager() throws KeyStoreException, NoSuchAlgorithmException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);

        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }

        return (X509TrustManager) trustManagers[0];
    }

    /**
     * Returns the internally cached {@link OkHttpClient} object used for performing {@link Request}(s).
     *
     * @return Internally cached {@link OkHttpClient} object.
     */
    protected OkHttpClient httpClient() {
        return httpClient;
    }

    /**
     * Relays the user generated {@link Request} to {@link Requester} for it to be carried out
     *
     * @param <T>     the {@link OmiseObjectBase} object type that is expected to be returned
     * @param <R>     the {@link Request} object type that is passed in from the user
     * @param request the {@link Request} user generated request
     * @return the {@link Model} object that contains the response from the API
     * @throws IOException    the general I/O error that could happen during deserialization
     * @throws OmiseException the custom exception thrown for response errors
     */
    public <T extends OmiseObjectBase, R extends Request<T>> T sendRequest(R request) throws IOException, OmiseException {
        if (requester == null) return null;

        return requester.sendRequest(request);
    }
}
