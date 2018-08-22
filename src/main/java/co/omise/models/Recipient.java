package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * Represents Omise Recipient object.
 *
 * @see <a href="https://www.omise.co/recipients-api">Recipients API</a>
 */
public class Recipient extends Model {
    private boolean verified;
    private boolean active;
    private String name;
    private String email;
    private String description;
    private RecipientType type;
    @JsonProperty("tax_id")
    private String taxId;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    @JsonProperty("failure_code")
    private String failureCode;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipientType getType() {
        return type;
    }

    public void setType(RecipientType type) {
        this.type = type;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    /**
     * The {@link RequestBuilder} class for creating a Recipient.
     */
    public static class CreateRequestBuilder extends RequestBuilder<Recipient> {
        @JsonProperty("bank_account")
        private BankAccount.Params bankAccount;
        @JsonProperty
        private String name;
        @JsonProperty
        private String email;
        @JsonProperty
        private String description;
        @JsonProperty
        private RecipientType type;
        @JsonProperty
        private String taxId;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "recipients");
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }

        public CreateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder type(RecipientType type) {
            this.type = type;
            return this;
        }

        public CreateRequestBuilder taxId(String taxId) {
            this.taxId = taxId;
            return this;
        }

        public CreateRequestBuilder bankAccount(BankAccount.Params bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving a Recipient.
     */
    public static class GetRequestBuilder extends RequestBuilder<Recipient> {
        private String recipientId;

        public GetRequestBuilder(String recipientId) {
            this.recipientId = recipientId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "recipients", recipientId);
        }

        @Override
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }
    }

    /**
     * The {@link RequestBuilder} class for updating a Recipient.
     */
    public static class UpdateRequestBuilder extends RequestBuilder<Recipient> {
        private String recipientId;

        @JsonProperty("bank_account")
        private BankAccount.Params bankAccount;
        @JsonProperty
        private String name;
        @JsonProperty
        private String email;
        @JsonProperty
        private String description;
        @JsonProperty
        private RecipientType type;
        @JsonProperty
        private String taxId;

        public UpdateRequestBuilder(String recipientId) {
            this.recipientId = recipientId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "recipients", recipientId);
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }

        public UpdateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UpdateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateRequestBuilder type(RecipientType type) {
            this.type = type;
            return this;
        }

        public UpdateRequestBuilder taxId(String taxId) {
            this.taxId = taxId;
            return this;
        }

        public UpdateRequestBuilder bankAccount(BankAccount.Params bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }
    }

    /**
     * The {@link RequestBuilder} class for deleting a Recipient.
     */
    public static class DeleteRequestBuilder extends RequestBuilder<Recipient> {
        private String recipientId;

        public DeleteRequestBuilder(String recipientId) {
            this.recipientId = recipientId;
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "recipients", recipientId);
        }

        @Override
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }
    }
}