package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;
import org.joda.time.YearMonth;

/**
 * Represents Omise Card object.
 *
 * @see <a href="https://www.omise.co/cards-api">Cards API</a>
 */
public class Card extends Model {
    private String country;
    private String city;
    private String bank;
    @JsonProperty("postal_code")
    private String postalCode;
    private String financing;
    @JsonProperty("last_digits")
    private String lastDigits;
    private String brand;
    @JsonProperty("expiration_month")
    private int expirationMonth;
    @JsonProperty("expiration_year")
    private int expirationYear;
    private String fingerprint;
    private String name;
    @JsonProperty("security_code_check")
    private boolean securityCodeCheck;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonIgnore
    public YearMonth getExpiration() {
        return new YearMonth(expirationYear, expirationMonth);
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSecurityCodeCheck() {
        return securityCodeCheck;
    }

    public void setSecurityCodeCheck(boolean securityCodeCheck) {
        this.securityCodeCheck = securityCodeCheck;
    }

    // Duplicated, for now. Since we don't people trying to update card numbers.
    public static class Create extends co.omise.models.Params {
        @JsonProperty
        private String name;
        @JsonProperty
        private String number;
        @JsonProperty("security_code")
        private String securityCode;
        @JsonProperty
        private String city;
        @JsonProperty("postal_code")
        private String postalCode;
        @JsonProperty("expiration_month")
        private int expirationMonth;
        @JsonProperty("expiration_year")
        private int expirationYear;

        public Create name(String name) {
            this.name = name;
            return this;
        }

        public Create number(String number) {
            this.number = number;
            return this;
        }

        public Create securityCode(String code) {
            this.securityCode = code;
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

        public Create expirationMonth(int expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public Create expirationYear(int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public Create expiration(YearMonth expiration) {
            return expirationMonth(expiration.getMonthOfYear())
                    .expirationYear(expiration.getYear());
        }

        public Create expiration(int month, int year) {
            return expirationMonth(month)
                    .expirationYear(year);
        }
    }

    public static class Update extends co.omise.models.Params {
        @JsonProperty
        private String name;
        @JsonProperty
        private String city;
        @JsonProperty("postal_code")
        private String postalCode;
        @JsonProperty("expiration_month")
        private int expirationMonth;
        @JsonProperty("expiration_year")
        private int expirationYear;

        public Update name(String name) {
            this.name = name;
            return this;
        }

        public Update city(String city) {
            this.city = city;
            return this;
        }

        public Update postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Update expirationMonth(int expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public Update expirationYear(int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public Update expiration(YearMonth expiration) {
            return expirationMonth(expiration.getMonthOfYear())
                    .expirationYear(expiration.getYear());
        }

        public Update expiration(int month, int year) {
            return expirationMonth(month)
                    .expirationYear(year);
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving a particular Card.
     */
    public static class GetRequestBuilder extends RequestBuilder<Card> {
        private String cardId;
        private String customerId;

        public GetRequestBuilder(String cardId, String customerId) {
            this.cardId = cardId;
            this.customerId = customerId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId, "cards", cardId);
        }


    }
}