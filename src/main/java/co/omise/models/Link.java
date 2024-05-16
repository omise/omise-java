package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Links object
 *
 * @see <a href="https://www.omise.co/links-api">Links API</a>
 */
public class Link extends Model {
    private long amount;
    private ScopedList<Charge> charges;
    private String currency;
    private String description;
    private String location;
    private boolean multiple;
    @JsonProperty("payment_uri")
    private String paymentUri;
    private String title;
    private boolean used;
    @JsonProperty("used_at")
    private DateTime usedAt;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public ScopedList<Charge> getCharges() {
        return this.charges;
    }

    public void setCharges(ScopedList<Charge> charges) {
        this.charges = charges;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isMultiple() {
        return this.multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getPaymentUri() {
        return this.paymentUri;
    }

    public void setPaymentUri(String paymentUri) {
        this.paymentUri = paymentUri;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public DateTime getUsedAt() {
        return this.usedAt;
    }

    public void setUsedAt(DateTime usedAt) {
        this.usedAt = usedAt;
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Link> {
        private final String linkId;
        public DeleteRequestBuilder(String linkId) {
            this.linkId = linkId;
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "links", linkId);
        }

        @Override
        protected ResponseType<Link> type() {
            return new ResponseType<>(Link.class);
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Link> {
        private final String linkId;
        public GetRequestBuilder(String linkId) {
            this.linkId = linkId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "links", linkId);
        }

        @Override
        protected ResponseType<Link> type() {
            return new ResponseType<>(Link.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Link>> {
        private ScopedList.Options options;

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "links", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Link>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Link>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Link> {

        @JsonProperty
        private long amount;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String description;
        @JsonProperty
        private boolean multiple;
        @JsonProperty
        private String title;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "links");
        }

        @Override
        protected ResponseType<Link> type() {
            return new ResponseType<>(Link.class);
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreateRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateRequestBuilder multiple(boolean multiple) {
            this.multiple = multiple;
            return this;
        }

        public CreateRequestBuilder title(String title) {
            this.title = title;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }
    }

    public static class ListChargesRequestBuilder extends RequestBuilder<ScopedList<Charge>> {
        private final String linkId;
        private ScopedList.Options options;
        public ListChargesRequestBuilder(String linkId) {
            this.linkId = linkId;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "links", serializer())
                  .segments(linkId, "charges")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Charge>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Charge>>() {});
        }

        public ListChargesRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}