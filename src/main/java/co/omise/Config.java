package co.omise;

/**
 * Config class bundles configuration values supplied to
 * the {@link Client} constructor.
 *
 * @see Client
 */
public final class Config {
    private final String apiVersion;
    private final String publicKey;
    private final String secretKey;

    Config(String apiVersion, String publicKey, String secretKey) {
        this.apiVersion = apiVersion;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
    }

    /**
     * Returns the API version configuration. This value will be added as
     * a {@code Omise-Version} HTTP header during API calls.
     *
     * @return A {@link String} containing the configured API version.
     */
    public String apiVersion() {
        return apiVersion;
    }

    /**
     * Returns the configured public key. Public keys always have the {@code pkey_} prefix.
     *
     * @return A {@link String} containing the public key.
     */
    public String publicKey() {
        return publicKey;
    }

    /**
     * Returns the configured secret key. Secret keys always have the {@code skey_} prefix.
     *
     * @return A {@link String} containing the secret key.
     */
    public String secretKey() {
        return secretKey;
    }
}
