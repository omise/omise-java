package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import org.joda.time.YearMonth;

import java.io.IOException;

/**
 * Card object
 *
 * @see <a href="https://www.omise.co/cards-api">Card API</a>
 */
public class Card extends Model {
    private String bank;
    private String brand;
    private String city;
    private String country;
    @JsonProperty("expiration_month")
    private int expirationMonth;
    @JsonProperty("expiration_year")
    private int expirationYear;
    private String financing;
    private String fingerprint;
    @JsonProperty("first_digits")
    private String firstDigits;
    @JsonProperty("last_digits")
    private String lastDigits;
    private String location;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("security_code_check")
    private boolean securityCodeCheck;
    private String state;
    private String street1;
    private String street2;

    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getExpirationMonth() {
        return this.expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return this.expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getFinancing() {
        return this.financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFirstDigits() {
        return this.firstDigits;
    }

    public void setFirstDigits(String firstDigits) {
        this.firstDigits = firstDigits;
    }

    public String getLastDigits() {
        return this.lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isSecurityCodeCheck() {
        return this.securityCodeCheck;
    }

    public void setSecurityCodeCheck(boolean securityCodeCheck) {
        this.securityCodeCheck = securityCodeCheck;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet1() {
        return this.street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return this.street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    @JsonIgnore
    public YearMonth getExpiration() {
        return new YearMonth(expirationYear, expirationMonth);
    }

    public static class Create extends co.omise.models.Params {
        @JsonProperty
        private String city;

        @JsonProperty
        private String country;

        @JsonProperty("expiration_month")
        private int expirationMonth;

        @JsonProperty("expiration_year")
        private int expirationYear;

        @JsonProperty
        private String name;

        @JsonProperty
        private String number;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("postal_code")
        private String postalCode;

        @JsonProperty("security_code")
        private String securityCode;

        @JsonProperty
        private String state;

        @JsonProperty
        private String street1;

        @JsonProperty
        private String street2;

        public Create city(String city) {
            this.city = city;
            return this;
        }

        public Create country(String country) {
            this.country = country;
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

        public Create name(String name) {
            this.name = name;
            return this;
        }

        public Create number(String number) {
            this.number = number;
            return this;
        }

        public Create phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Create postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Create securityCode(String securityCode) {
            this.securityCode = securityCode;
            return this;
        }

        public Create state(String state) {
            this.state = state;
            return this;
        }

        public Create street1(String street1) {
            this.street1 = street1;
            return this;
        }

        public Create street2(String street2) {
            this.street2 = street2;
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

    public static class DeleteRequestBuilder extends RequestBuilder<Card> {
        private final String customerId;
        private final String cardId;
        public DeleteRequestBuilder(String customerId, String cardId) {
            this.customerId = customerId;
            this.cardId = cardId;
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId, "cards", cardId);
        }

        @Override
        protected ResponseType<Card> type() {
            return new ResponseType<>(Card.class);
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Card> {
        private final String customerId;
        private final String cardId;
        public GetRequestBuilder(String customerId, String cardId) {
            this.customerId = customerId;
            this.cardId = cardId;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId, "cards", cardId);
        }

        @Override
        protected ResponseType<Card> type() {
            return new ResponseType<>(Card.class);
        }
    }

    public static class UpdateRequestBuilder extends RequestBuilder<Card> {
        private final String customerId;
        private final String cardId;

        @JsonProperty
        private String city;
        @JsonProperty("expiration_month")
        private int expirationMonth;
        @JsonProperty("expiration_year")
        private int expirationYear;
        @JsonProperty
        private String name;
        @JsonProperty("postal_code")
        private String postalCode;
        public UpdateRequestBuilder(String customerId, String cardId) {
            this.customerId = customerId;
            this.cardId = cardId;
        }

        @Override
        protected String method() {
            return PATCH;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "customers", customerId, "cards", cardId);
        }

        @Override
        protected ResponseType<Card> type() {
            return new ResponseType<>(Card.class);
        }

        public UpdateRequestBuilder city(String city) {
            this.city = city;
            return this;
        }

        public UpdateRequestBuilder expirationMonth(int expirationMonth) {
            this.expirationMonth = expirationMonth;
            return this;
        }

        public UpdateRequestBuilder expirationYear(int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public UpdateRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateRequestBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public UpdateRequestBuilder expiration(YearMonth expiration) {
            return expirationMonth(expiration.getMonthOfYear())
               .expirationYear(expiration.getYear());
        }

        public UpdateRequestBuilder expiration(int month, int year) {
            return expirationMonth(month)
               .expirationYear(year);
        }

        @Override
        protected RequestBody payload() throws IOException {
            return serialize();
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Card>> {
        private final String customerId;
        private ScopedList.Options options;
        public ListRequestBuilder(String customerId) {
            this.customerId = customerId;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "customers", serializer())
                  .segments(customerId, "cards")
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Card>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Card>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }
}