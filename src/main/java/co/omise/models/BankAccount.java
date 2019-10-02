package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount extends Model {
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonProperty("branch_code")
    private String branchCode;
    private String brand;
    @JsonProperty("last_digits")
    private String lastDigits;
    private String name;
    @JsonProperty("type")
    private String accountType;

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLastDigits() {
        return this.lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public static class Params extends co.omise.models.Params {
        @JsonProperty("account_type")
        private String accountType;

        @JsonProperty("bank_code")
        private String bankCode;

        @JsonProperty("branch_code")
        private String branchCode;

        @JsonProperty
        private String brand;

        @JsonProperty
        private String name;

        @JsonProperty
        private String number;

        public Params accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Params bankCode(String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Params branchCode(String branchCode) {
            this.branchCode = branchCode;
            return this;
        }

        public Params brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Params name(String name) {
            this.name = name;
            return this;
        }

        public Params number(String number) {
            this.number = number;
            return this;
        }
    }
}