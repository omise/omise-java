package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
 *
 * This class represents Omise Token object.
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

    public static class Create extends Params {
        @JsonProperty
        private Card.Create card;

        public Create card(Card.Create card) {
            this.card = card;
            return this;
        }
    }
}
