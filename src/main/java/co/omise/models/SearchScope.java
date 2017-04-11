package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SearchScope {
    @JsonProperty("dispute")Dispute,
    @JsonProperty("charge")Charge,
    @JsonProperty("customer")Customer,
    @JsonProperty("recipient")Recipient,
    @JsonProperty("refund")Refund,
    @JsonProperty("transfer")Transfer,
    @JsonProperty("link")Link,
}
