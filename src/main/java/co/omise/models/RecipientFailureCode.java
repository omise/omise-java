package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RecipientFailureCode {
    @JsonProperty("name_mismatch")NameMismatch,
    @JsonProperty("account_not_found")AccountNotFound,
    @JsonProperty("bank_not_found")BankNotFound,
}