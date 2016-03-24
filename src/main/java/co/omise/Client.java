package co.omise;

import com.google.common.base.Preconditions;
import okhttp3.OkHttpClient;

public class Client {
    private final Config config;
    private final OkHttpClient client;

    public Client(String secretKey) {
        this(null, null, secretKey);
    }

    public Client(String publicKey, String secretKey) {
        this(null, publicKey, secretKey);
    }

    public Client(String apiVersion, String publicKey, String secretKey) {
        Preconditions.checkNotNull(secretKey);

        this.config = new Config(apiVersion, publicKey, secretKey);
        this.client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(config))
                .build();
    }

    public String getApiVersion() {
        return config.getApiVersion();
    }

    public String getPublicKey() {
        return config.getPublicKey();
    }

    public String getSecretKey() {
        return config.getSecretKey();
    }
}
