package co.omise;

import co.omise.resources.AccountResource;
import co.omise.resources.BalanceResource;
import com.google.common.base.Preconditions;
import okhttp3.OkHttpClient;

public class Client {
    private final Config config;
    private final OkHttpClient httpClient;

    private AccountResource account;
    private BalanceResource balance;

    public Client(String secretKey) {
        this(null, null, secretKey);
    }

    public Client(String publicKey, String secretKey) {
        this(null, publicKey, secretKey);
    }

    public Client(String apiVersion, String publicKey, String secretKey) {
        Preconditions.checkNotNull(secretKey);

        this.config = new Config(apiVersion, publicKey, secretKey);
        this.httpClient = new OkHttpClient.Builder()
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

    public AccountResource account() {
        if (this.account == null) {
            this.account = new AccountResource(httpClient);
        }

        return this.account;
    }

    public BalanceResource balance() {
        if (this.balance == null) {
            this.balance = new BalanceResource(httpClient);
        }

        return this.balance;
    }
}
