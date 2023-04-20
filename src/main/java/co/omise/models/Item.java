package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    public String sku;
    public String name;
    public String amount;
    public int quantity;
    public String category;
    public String brand;
    @JsonProperty("item_uri")
    public String itemUri;
    @JsonProperty("image_uri")
    public String imageUri;
}
