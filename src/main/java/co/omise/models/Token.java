package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * <p>
 * <strong>Full Credit Card data should never go through your server.</strong>
 * Do not send the credit card data to Omise from your servers directly.
 * You must send the card data from the client browser via Javascript (Omise-JS).
 * Code involving this class should only be used either with fake data in test mode
 * (e.g.: quickly creating some fake data, testing our API from a terminal, etc.),
 * or if you are PCI-DSS compliant.
 * </p>
 * <p>
 * Sending card data from server requires a valid PCI-DSS certification.
 * You can learn more about this in <a href="https://www.omise.co/security-best-practices">Security Best Practices</a>.
 * </p>
 * <p>
 * This class represents Omise Token object.
 * </p>
 *
 * @see <a href="https://www.omise.co/tokens-api">Tokens API</a>
 */
public class Token extends Model {
    private boolean used;
    private Card card;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * The {@link RequestBuilder} class for creating a Token.
     */
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
        protected RequestBody payload() throws IOException {
            return serialize();
        }

        @Override
        protected ResponseType<Token> type() {
            return new ResponseType<>(Token.class);
        }

        public CreateRequestBuilder card(Card.Create card) {
            this.card = card;
            return this;
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular Token.
     */
    public static class GetRequestBuilder extends RequestBuilder<Token> {
        private String tokenId;

        public GetRequestBuilder(String tokenId) {
            this.tokenId = tokenId;
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