package co.omise;

import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;

import java.util.*;

/**
 * Endpoints encapsulates information about a particular Omise API endpoint.
 * Currently there exists 2 endpoints, the API and the VAULT.
 * <p>
 * This class encapsulates the following information for each endpoint:
 * <ul>
 * <li>Host and network scheme (defaults to HTTPS.)</li>
 * <li>The certificate hash to pin against.</li>
 * <li>Whether to use the public key or the secret key.</li>
 * </ul>
 */
public abstract class Endpoint {
    public static final Endpoint VAULT = new Endpoint() {
        @Override
        public String host() {
            return "vault.omise.co";
        }

        @Override
        public String authenticationKey(Config config) {
            return config.publicKey();
        }
    };

    public static final Endpoint API = new Endpoint() {
        @Override
        public String host() {
            return "api.omise.co";
        }

        @Override
        public String authenticationKey(Config config) {
            return config.secretKey();
        }
    };

    public static List<Endpoint> getAllEndpoints() {
        List<Endpoint> endpoints = new ArrayList<>();
        endpoints.add(VAULT);
        endpoints.add(API);
        return Collections.unmodifiableList(endpoints);
    }

    public static Map<String, Endpoint> getAllEndpointsByHost() {
        Map<String, Endpoint> endpoints = new HashMap<>();
        endpoints.put(VAULT.host(), VAULT);
        endpoints.put(API.host(), API);
        return Collections.unmodifiableMap(endpoints);
    }

    public static final String API_VERSION = "2017-11-02";

    /**
     * The scheme to use, defaults to HTTPS.
     *
     * @return A {@link String} containing the network scheme to use.
     */
    public String scheme() {
        return "https";
    }

    /**
     * The host name to connect to.
     *
     * @return A {@link String} containing the host name to connect to.
     */
    public abstract String host();

    /**
     * The certificate hash to use with OkHttp's {@link CertificatePinner}.
     * The default implementation returns a certificate hash for {@code *.omise.co} domains.
     *
     * @return A {@link String} containing the cert hash to pin against or {@code null} to
     * pin no certificate.
     */
    public String certificateHash() {
        return "sha256/maqNsxEnwszR+xCmoGUiV636PvSM5zvBIBuupBn9AB8=";
    }

    /**
     * The authentication key to use. The key should be taken from the given {@link Config} object.
     * Either {@link Config#publicKey()} or {@link Config#secretKey()} should be returned.
     *
     * @param config A {@link Config} instance.
     * @return A {@link String} containing the authentication key.
     */
    public abstract String authenticationKey(Config config);

    public HttpUrl.Builder buildUrl() {
        return new HttpUrl.Builder()
                .scheme(scheme())
                .host(host());
    }
}