package co.omise;

final class Config {
    private final String apiVersion;
    private final String publicKey;
    private final String secretKey;

    Config(String apiVersion, String publicKey, String secretKey) {
        this.apiVersion = apiVersion;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
    }

    String apiVersion() {
        return apiVersion;
    }

    String publicKey() {
        return publicKey;
    }

    String secretKey() {
        return secretKey;
    }
}
