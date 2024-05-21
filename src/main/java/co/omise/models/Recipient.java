package co.omise.models;

import co.omise.Endpoint;
import co.omise.models.schedules.Schedule;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Recipient object
 *
 * @see <a href="https://www.omise.co/recipients-api">Recipient API</a>
 */
public class Recipient extends Model {
    @JsonProperty("activated_at")
    private DateTime activatedAt;
    private boolean active;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    @JsonProperty("default")
    private boolean isDefault;
    private String description;
    private String email;
    @JsonProperty("failure_code")
    private RecipientFailureCode failureCode;
    private String location;
    private Map<String, Object> metadata;
    private String name;
    private String schedule;
    @JsonProperty("tax_id")
    private String taxId;
    private RecipientType type;
    private boolean verified;
    @JsonProperty("verified_at")
    private DateTime verifiedAt;

    public DateTime getActivatedAt() {
        return this.activatedAt;
    }

    public void setActivatedAt(DateTime activatedAt) {
        this.activatedAt = activatedAt;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean IsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RecipientFailureCode getFailureCode() {
        return this.failureCode;
    }

    public void setFailureCode(RecipientFailureCode failureCode) {
        this.failureCode = failureCode;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTaxId() {
        return this.taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public RecipientType getType() {
        return this.type;
    }

    public void setType(RecipientType type) {
        this.type = type;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public DateTime getVerifiedAt() {
        return this.verifiedAt;
    }

    public void setVerifiedAt(DateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Recipient> {
        private final String recipientId;
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

    public static class GetRequestBuilder extends RequestBuilder<Recipient> {
        private final String recipientId;
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

    public static class UpdateRequestBuilder extends RequestBuilder<Recipient> {
        private final String recipientId;

        @JsonProperty("bank_account")
        private BankAccount.Params bankAccount;
        @JsonProperty
        private String description;
        @JsonProperty
        private String email;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private String name;
        @JsonProperty("tax_id")
        private String taxId;
        @JsonProperty
        private RecipientType type;
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
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }

        public UpdateRequestBuilder bankAccount(BankAccount.Params bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }

        public UpdateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UpdateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public UpdateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateRequestBuilder taxId(String taxId) {
            this.taxId = taxId;
            return this;
        }

        public UpdateRequestBuilder type(RecipientType type) {
            this.type = type;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public UpdateRequestBuilder metadata(String key, Object value) {
            HashMap<String, Object> tempMap = new HashMap<>();
            if (metadata != null) {
                tempMap.putAll(metadata);
            }
            tempMap.put(key, value);

            this.metadata = new HashMap<>(tempMap);
            return this;
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Recipient>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "recipients", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Recipient>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Recipient>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Recipient> {

        @JsonProperty("bank_account")
        private BankAccount.Params bankAccount;
        @JsonProperty
        private String description;
        @JsonProperty
        private String email;
        @JsonProperty
        private Map<String, Object> metadata;
        @JsonProperty
        private String name;
        @JsonProperty("tax_id")
        private String taxId;
        @JsonProperty
        private RecipientType type;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "recipients");
        }

        @Override
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }

        public CreateRequestBuilder bankAccount(BankAccount.Params bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CreateRequestBuilder metadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public CreateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateRequestBuilder taxId(String taxId) {
            this.taxId = taxId;
            return this;
        }

        public CreateRequestBuilder type(RecipientType type) {
            this.type = type;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public CreateRequestBuilder metadata(String key, Object value) {
            HashMap<String, Object> tempMap = new HashMap<>();
            if (metadata != null) {
                tempMap.putAll(metadata);
            }
            tempMap.put(key, value);

            this.metadata = new HashMap<>(tempMap);
            return this;
        }
    }

    public static class ListSchedulesRequestBuilder extends RequestBuilder<ScopedList<Schedule>> {
        private final String recipientId;
        private ScopedList.Options options;
        public ListSchedulesRequestBuilder(String recipientId) {
            this.recipientId = recipientId;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "recipients", serializer())
                  .segments(recipientId, "schedules")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Schedule>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Schedule>>() {});
        }

        public ListSchedulesRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class VerifyRequestBuilder extends RequestBuilder<Recipient> {
        private final String recipientId;
        public VerifyRequestBuilder(String recipientId) {
            this.recipientId = recipientId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "recipients", recipientId, "verify");
        }

        @Override
        protected ResponseType<Recipient> type() {
            return new ResponseType<>(Recipient.class);
        }
    }
}