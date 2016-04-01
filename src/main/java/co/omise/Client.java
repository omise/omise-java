package co.omise;

import co.omise.resources.*;
import com.google.common.base.Preconditions;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Client is the main entry point to the Omise Java library.
 * Use the resource accessor methods to access Omise API resources.
 * <p>
 * Clients are thread-safe and a single instance can be shared
 * for use by multiple threads.
 * </p>
 *
 * @see Resource
 * @see Config
 */
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

    /**
     * Creates a Client with just the secret key. Always use this constructor to avoid transmitting any card data
     * through your own server. (since token creation will fail without a public key.)
     *
     * @param secretKey The key with {@code skey_} prefix.
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     */
    public Client(String secretKey) {
        this(null, null, secretKey);
    }

    /**
     * Creates a Client with both the secret key and public key.
     *
     * @param publicKey The key with {@code pkey_} prefix.
     * @param secretKey The key with {@code skey_} prefix.
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     */
    public Client(String publicKey, String secretKey) {
        this(null, publicKey, secretKey);
    }

    /**
     * Creates a Client that sends the specified API version string in the header to access an earlier version
     * of the Omise API.
     *
     * @param apiVersion API version string that will be sent in {@code Omise-Version} header.
     * @param publicKey  The key with {@code pkey_} prefix.
     * @param secretKey  The key with {@code skey_} prefix.
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     * @see <a href="https://www.omise.co/api-versioning">Versioning</a>
     */
    public Client(String apiVersion, String publicKey, String secretKey) {
        Preconditions.checkNotNull(secretKey);

        config = new Config(apiVersion, publicKey, secretKey);
        httpClient = buildHttpClient(config);

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

    /**
     * Returns a new {@link OkHttpClient} to use for building {@link Resource}(s). Override this to customize the HTTP
     * client. This method will be called once during construction and the result will be cached internally.
     * <p>
     * It is generally a good idea to implement this by adding to the builder created from
     * <code>super.buildHttpClient(config).newBuilder()</code> so that all configurations are properly applied and SSL
     * certificates are pinned.
     * </p>
     *
     * @param config A {@link Config} object built from constructor parameters.
     * @return A new {@link OkHttpClient} object for connecting to the Omise API.
     */
    protected OkHttpClient buildHttpClient(Config config) {
        CertificatePinner.Builder pinner = new CertificatePinner.Builder();
        for (Endpoint endpoint : Endpoint.all()) {
            pinner = pinner.add(endpoint.host(), endpoint.certificateHash());
        }

        return new OkHttpClient.Builder()
                .addInterceptor(new Configurer(config))
                .readTimeout(60, TimeUnit.SECONDS)
                .certificatePinner(pinner.build())
                .build();
    }


    /**
     * Returns the internally cached {@link OkHttpClient} object used for building {@link Resource}(s).
     *
     * @return Internally cached {@link OkHttpClient} object.
     */
    protected OkHttpClient httpClient() {
        return httpClient;
    }

    /**
     * Returns {@link AccountResource} for accessing the
     * <a href="https://www.omise.co/account-api">Account API</a>
     *
     * @return An {@link AccountResource} instance.
     * @see <a href="https://www.omise.co/account-api">Account API</a>
     */
    public AccountResource account() {
        return account;
    }

    /**
     * Returns {@link BalanceResource} for accessing the
     * <a href="https://www.omise.co/balance-api">Balance API</a>
     *
     * @return A {@link BalanceResource} instance.
     * @see <a href="https://www.omise.co/balance-api">Balance API</a>
     */
    public BalanceResource balance() {
        return balance;
    }

    /**
     * Returns {@link ChargeResource} for accessing the
     * <a href="https://www.omise.co/charges-api">Charge API</a>
     *
     * @return A {@link ChargeResource} instance.
     * @see <a href="https://www.omise.co/charges-api">Charge API</a>
     */
    public ChargeResource charges() {
        return charges;
    }

    /**
     * Returns {@link ChargeSpecificResource} instance for accessing
     * charge-specific sub-resources.
     *
     * @param chargeId The id of the related charge.
     * @return A {@link ChargeSpecificResource} instance.
     * @see <a href="https://www.omise.co/refunds-api">Refunds API</a>
     */
    public ChargeSpecificResource charge(String chargeId) {
        return new ChargeSpecificResource(httpClient, chargeId);
    }

    /**
     * Returns {@link CustomerResource} instance for accessing
     * <a href="https://www.omise.co/customers-api">Customer API</a>
     *
     * @return <a href="https://www.omise.co/customers-api">Customer API</a>
     */
    public CustomerResource customers() {
        return customers;
    }

    /**
     * Retruns {@link CustomerSpecificResource} instance for accessing
     * customer-specific sub-resources.
     *
     * @param customerId The id of the related customer.
     * @return A {@link CustomerSpecificResource} instance.
     * @see <a href="https://www.omise.co/cards-api">Card API</a>
     */
    public CustomerSpecificResource customer(String customerId) {
        return customers.withId(customerId);
    }

    /**
     * Returns {@link DisputeResource} for accessing the
     * <a href="https://www.omise.co/disputes-api">Dispute API</a>
     *
     * @return A {@link DisputeResource} instance.
     * @see <a href="https://www.omise.co/disputes-api">Dispute API</a>
     */
    public DisputeResource disputes() {
        return disputes;
    }

    /**
     * Returns {@link EventResource} for accessing the
     * <a href="https://www.omise.co/events-api">Events API</a>
     *
     * @return An {@link EventResource} instance.
     * @see <a href="https://www.omise.co/events-api">Events API</a>
     */
    public EventResource events() {
        return events;
    }

    /**
     * Returns {@link RecipientResource} for accessing the
     * <a href="https://www.omise.co/recipients-api">Recipients API</a>
     *
     * @return A {@link RecipientResource} instance.
     * @see <a href="https://www.omise.co/recipients-api">Recipients API</a>
     */
    public RecipientResource recipients() {
        return recipients;
    }

    /**
     * Returns {@link TokenResource} for acessing the
     * <a href="https://www.omise.co/tokens-api">Tokens API</a>
     * <p>
     * <strong>Full Credit Card data should never go through your server.</strong>
     * This API is to be used if and only if your servers are PCI-DSS certified.
     * See <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     * for more information.
     * </p>
     *
     * @return A {@link TokenResource} instance.
     * @see <a href="https://www.omise.co/tokens-api">Tokens API</a>
     * @see <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>
     */
    public TokenResource tokens() {
        return tokens;
    }

    /**
     * Returns {@link TransactionResource} for accessing the
     * <a href="https://www.omise.co/transactions-api">Transactions API</a>
     *
     * @return A {@link TransactionResource} instance.
     * @see <a href="https://www.omise.co/transactions-api">Transactions API</a>
     */
    public TransactionResource transactions() {
        return transactions;
    }

    /**
     * Returns {@link TransferResource} for accessing the
     * <a href="https://www.omise.co/transfers-api">Transfers API</a>
     *
     * @return A {@link TransferResource} instance.
     * @see <a href="https://www.omise.co/transfers-api">Transfers API</a>
     */
    public TransferResource transfers() {
        return transfers;
    }
}
