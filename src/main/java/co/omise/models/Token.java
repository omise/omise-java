package co.omise.models;

import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Token extends Model {
    boolean used;
    Card card;

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
        private String name;
        private String number;
        private int expirationMonth;
        private int expirationYear;
        private String securityCode;
        private String city;
        private String postalCode;

        public Create name(String name) {
            this.name = name;
            return this;
        }

        public Create number(String number) {
            this.number = number;
            return this;
        }

        public Create expirationMonth(int expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public Create expirationYear(int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public Create securityCode(String securityCode) {
            this.securityCode = securityCode;
            return this;
        }

        public Create city(String city) {
            this.city = city;
            return this;
        }

        public Create postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query() {
            return null;
        }

        @Override
        public RequestBody body() {
            return new FormBody.Builder()
                    .add("card[name]", name)
                    .add("card[number]", number)
                    .add("card[expiration_month]", Integer.toString(expirationMonth))
                    .add("card[expiration_year]", Integer.toString(expirationYear))
                    .add("card[security_code]", securityCode)
                    .add("card[city]", city)
                    .add("card[postal_code]", postalCode)
                    .build();
        }
    }
}
