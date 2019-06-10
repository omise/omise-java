package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionDirection {
    @JsonProperty("credit") Credit,
    @JsonProperty("debit") Debit,
}
