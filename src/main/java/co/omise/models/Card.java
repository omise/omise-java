package co.omise.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.YearMonth;

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

    public static class Update extends Params {
        public Update name(String name) {
            add("name", name);
            return this;
        }

        public Update city(String city) {
            add("city", city);
            return this;
        }

        public Update postalCode(String postalCode) {
            add("postal_code", postalCode);
            return this;
        }

        public Update expiration(YearMonth expiration) {
            add("expiration_month", expiration.getMonthOfYear());
            add("expiration_year", expiration.getYear());
            return this;
        }

        public Update expirationMonth(int month) {
            add("expiration_month", month);
            return this;
        }

        public Update expirationYear(int year) {
            add("expiration_year", year);
            return this;
        }
    }
}
