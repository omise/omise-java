package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount extends Model {
    private String brand;
    private String number;
    @JsonProperty("last_digits")
    private String lastDigits;
    private String name;

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

    public static class BankAccountParams {
        private String brand;
        private String number;
        private String name;

        public BankAccountParams brand(String brand) {
            this.brand = brand;
            return this;
        }

        public BankAccountParams number(String number) {
            this.number = number;
            return this;
        }

        public BankAccountParams name(String name) {
            this.name = name;
            return this;
        }

        public void addTo(Params params) {
            if (brand != null) {
                params.add("bank_account[brand]", brand);
            }

            if (number != null) {
                params.add("bank_account[number]", number);
            }

            if (name != null) {
                params.add("bank_account[name]", name);
            }
        }
    }
}
