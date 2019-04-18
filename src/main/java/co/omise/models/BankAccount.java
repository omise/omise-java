package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents Omise Bank Account object.
 *
 * @see <a href="https://www.omise.co/bank-account-api">Bank Account API</a>
 */
public class BankAccount extends Model {
    private String brand;
    private String number;
    @JsonProperty("last_digits")
    private String lastDigits;
    private String name;

    public BankAccount() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty
        private String brand;
        @JsonProperty
        private String number;
        @JsonProperty
        private String name;

        public Params brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Params number(String number) {
            this.number = number;
            return this;
        }

        public Params name(String name) {
            this.name = name;
            return this;
        }
    }
}
