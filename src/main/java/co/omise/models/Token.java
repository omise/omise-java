package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * Token object
 *
 * @see <a href="https://www.omise.co/tokens-api">Token API</a>
 */
public class Token extends Model {
    private Card card;
    @JsonProperty("charge_status")
    private ChargeStatus chargeStatus;
    private String location;
    private boolean used;

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ChargeStatus getChargeStatus() {
        return this.chargeStatus;
    }

    public void setChargeStatus(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public static class CreateRequestBuilder extends RequestBuilder<Token> {

        @JsonProperty
        private Card.Create card;

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.VAULT, "tokens");
        }

        @Override
        protected ResponseType<Token> type() {
            return new ResponseType<>(Token.class);
        }

        public CreateRequestBuilder card(Card.Create card) {
            this.card = card;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException  {
            return serialize();
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Token> {
        private String tokenId;
        public GetRequestBuilder(String tokenId) {
            this.tokenId = tokenId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.VAULT, "tokens", tokenId);
        }

        @Override
        protected ResponseType<Token> type() {
            return new ResponseType<>(Token.class);
        }
    }
}