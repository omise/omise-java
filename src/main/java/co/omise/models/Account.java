package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Account object
 *
 * @see <a href="https://www.omise.co/account-api">Account API</a>
 */
public class Account extends Model {
    @JsonProperty("api_version")
    private String apiVersion;
    @JsonProperty("auto_activate_recipients")
    private boolean autoActivateRecipients;
    @JsonProperty("chain_enabled")
    private boolean chainEnabled;
    @JsonProperty("chain_return_uri")
    private String chainReturnUri;
    private String country;
    private String currency;
    private String email;
    private String location;
    @JsonProperty("metadata_export_keys")
    private Map<String, Object> metadataExportKeys;
    @JsonProperty("supported_currencies")
    private List<String> supportedCurrencies;
    @JsonProperty("team")
    private String teamId;
    @JsonProperty("webhook_uri")
    private String webhookUri;
    @JsonProperty("zero_interest_installments")
    private boolean zeroInterestInstallments;

    public String getApiVersion() {
        return this.apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public boolean isAutoActivateRecipients() {
        return this.autoActivateRecipients;
    }

    public void setAutoActivateRecipients(boolean autoActivateRecipients) {
        this.autoActivateRecipients = autoActivateRecipients;
    }

    public boolean isChainEnabled() {
        return this.chainEnabled;
    }

    public void setChainEnabled(boolean chainEnabled) {
        this.chainEnabled = chainEnabled;
    }

    public String getChainReturnUri() {
        return this.chainReturnUri;
    }

    public void setChainReturnUri(String chainReturnUri) {
        this.chainReturnUri = chainReturnUri;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getMetadataExportKeys() {
        return this.metadataExportKeys;
    }

    public void setMetadataExportKeys(Map<String, Object> metadataExportKeys) {
        this.metadataExportKeys = metadataExportKeys;
    }

    public List<String> getSupportedCurrencies() {
        return this.supportedCurrencies;
    }

    public void setSupportedCurrencies(List<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getWebhookUri() {
        return this.webhookUri;
    }

    public void setWebhookUri(String webhookUri) {
        this.webhookUri = webhookUri;
    }

    public boolean isZeroInterestInstallments() {
        return this.zeroInterestInstallments;
    }

    public void setZeroInterestInstallments(boolean zeroInterestInstallments) {
        this.zeroInterestInstallments = zeroInterestInstallments;
    }

    public static class GetRequestBuilder extends RequestBuilder<Account> {

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "account");
        }

        @Override
        protected ResponseType<Account> type() {
            return new ResponseType<>(Account.class);
        }
    }

    public static class UpdateRequestBuilder extends RequestBuilder<Account> {

        @JsonProperty("chain_enabled")
        private boolean chainEnabled;
        @JsonProperty("chain_return_uri")
        private String chainReturnUri;
        @JsonProperty("metadata_export_keys")
        private Map<String, Object> metadataExportKeys;
        @JsonProperty("webhook_uri")
        private String webhookUri;
        @JsonProperty("zero_interest_installments")
        private boolean zeroInterestInstallments;

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "account");
        }

        @Override
        protected ResponseType<Account> type() {
            return new ResponseType<>(Account.class);
        }

        public UpdateRequestBuilder chainEnabled(boolean chainEnabled) {
            this.chainEnabled = chainEnabled;
            return this;
        }

        public UpdateRequestBuilder chainReturnUri(String chainReturnUri) {
            this.chainReturnUri = chainReturnUri;
            return this;
        }

        public UpdateRequestBuilder metadataExportKeys(Map<String, Object> metadataExportKeys) {
            this.metadataExportKeys = metadataExportKeys;
            return this;
        }

        public UpdateRequestBuilder webhookUri(String webhookUri) {
            this.webhookUri = webhookUri;
            return this;
        }

        public UpdateRequestBuilder zeroInterestInstallments(boolean zeroInterestInstallments) {
            this.zeroInterestInstallments = zeroInterestInstallments;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }
    }
}