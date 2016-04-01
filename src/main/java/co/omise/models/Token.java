package co.omise.models;

import org.joda.time.YearMonth;

/**
 * Represents Omise Transfer object.
 *
 * @see <a href="https://www.omise.co/transfers-api">Transfers API</a>
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
        public Create name(String name) {
            add("card[name]", name);
            return this;
        }

        public Create number(String number) {
            add("card[number]", number);
            return this;
        }

        public Create expirationMonth(int expirationMonth) {
            add("card[expiration_month]", Integer.toString(expirationMonth));
            return this;
        }

        public Create expirationYear(int expirationYear) {
            add("card[expiration_year]", Integer.toString(expirationYear));
            return this;
        }

        public Create expiration(YearMonth expiration) {
            add("card[expiration_month]", Integer.toString(expiration.getMonthOfYear()));
            add("card[expiration_year]", Integer.toString(expiration.getYear()));
            return this;
        }

        public Create securityCode(String securityCode) {
            add("card[security_code]", securityCode);
            return this;
        }

        public Create city(String city) {
            add("card[city]", city);
            return this;
        }

        public Create postalCode(String postalCode) {
            add("card[postal_code]", postalCode);
            return this;
        }
    }
}
