package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("sku")
    public String sku;
    @JsonProperty("name")
    public String name;
    @JsonProperty("amount")
    public String amount;
    @JsonProperty("quantity")
    public String quantity;
}
