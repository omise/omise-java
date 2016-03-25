package co.omise;

import co.omise.resources.*;
import com.google.common.base.Preconditions;
import okhttp3.OkHttpClient;

public class Client {
    private final Config config;
    private final OkHttpClient httpClient;

    private final AccountResource account;
    private final BalanceResource balance;
    private final ChargeResource charges;
    private final CustomerResource customers;
    private final DisputeResource disputes;
    private final EventResource events;
    private final RecipientResource recipients;
    private final TokenResource tokens;
    private final TransactionResource transactions;
    private final TransferResource transfers;

    public Client(String secretKey) {
        this(null, null, secretKey);
    }

    public Client(String publicKey, String secretKey) {
        this(null, publicKey, secretKey);
    }

    public Client(String apiVersion, String publicKey, String secretKey) {
        Preconditions.checkNotNull(secretKey);

        config = new Config(apiVersion, publicKey, secretKey);
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(config))
                .build();

        account = new AccountResource(httpClient);
        balance = new BalanceResource(httpClient);
        charges = new ChargeResource(httpClient);
        customers = new CustomerResource(httpClient);
        disputes = new DisputeResource(httpClient);
        events = new EventResource(httpClient);
        recipients = new RecipientResource(httpClient);
        tokens = new TokenResource(httpClient);
        transactions = new TransactionResource(httpClient);
        transfers = new TransferResource(httpClient);
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
        return account;
    }

    public BalanceResource balance() {
        return balance;
    }

    public ChargeResource charges() {
        return charges;
    }

    public ChargeSpecificResource charge(String chargeId) {
        return new ChargeSpecificResource(httpClient, chargeId);
    }

    public CustomerResource customers() {
        return customers;
    }

    public CustomerSpecificResource customer(String customerId) {
        return customers.withId(customerId);
    }

    public DisputeResource disputes() {
        return disputes;
    }

    public EventResource events() {
        return events;
    }

    public RecipientResource recipients() {
        return recipients;
    }

    public TokenResource tokens() {
        return tokens;
    }

    public TransactionResource transactions() {
        return transactions;
    }

    public TransferResource transfers() {
        return transfers;
    }
}
