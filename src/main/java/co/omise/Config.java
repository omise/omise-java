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
    private final String userAgent;

    /**
     * Creates a new {@link Config} instance.
     *
     * @param apiVersion The API version to use.
     * @param publicKey  The key with the {@code pkey_} prefix.
     * @param secretKey  The key with the {@code skey_} prefix.
     */
    public Config(String apiVersion, String publicKey, String secretKey) {
        this.apiVersion = apiVersion;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        this.userAgent = buildUserAgent();
    }

    private String buildUserAgent() {
        StringBuilder builder = new StringBuilder();
        builder.append("OmiseJava/");
        builder.append(Client.class.getPackage().getImplementationVersion());
        if (apiVersion != null && !apiVersion.isEmpty()) {
            builder.append(" OmiseAPI/");
            builder.append(apiVersion);
        }

        builder.append(" Java/");
        builder.append(System.getProperty("java.version"));
        return builder.toString();
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

    /**
     * Returns a valid user agent string for use with HTTP clients.
     *
     * @return A {@link String} containing the user agent.
     */
    public String userAgent() {
        return userAgent;
    }
}
