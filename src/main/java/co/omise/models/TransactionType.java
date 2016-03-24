package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {
    @JsonProperty("credit")Credit,
    @JsonProperty("debit")Debit,
}
