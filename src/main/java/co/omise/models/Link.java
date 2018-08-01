package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * Represents Omise Link object.
 *
 * @see <a href="https://www.omise.co/links-api">Link API</a>
 */
public class Link extends Model {
    private long amount;
    private String currency;
    private boolean used;
    private boolean multiple;
    private String title;
    private String description;
    private ScopedList<Charge> charges;
    @JsonProperty("payment_uri")
    private String paymentUri;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScopedList<Charge> getCharges() {
        return charges;
    }

    public void setCharges(ScopedList<Charge> charges) {
        this.charges = charges;
    }

    public String getPaymentUri() {
        return paymentUri;
    }

    public void setPaymentUri(String paymentUri) {
        this.paymentUri = paymentUri;
    }

    /**
     * The {@link RequestBuilder} class for creating a Link.
     */
    public static class CreateRequestBuilder extends RequestBuilder<Link> {
        @JsonProperty
        private long amount;
        @JsonProperty
        private String currency;
        @JsonProperty
        private String title;
        @JsonProperty
        private String description;
        @JsonProperty
        private Boolean multiple;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "links");
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        public CreateRequestBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public CreateRequestBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreateRequestBuilder title(String title) {
            this.title = title;
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
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular Link.
     */
    public static class GetRequestBuilder extends RequestBuilder<Link> {
        private String linkId;

        public GetRequestBuilder(String linkId) {
            this.linkId = linkId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "links", linkId);
        }
    }
}