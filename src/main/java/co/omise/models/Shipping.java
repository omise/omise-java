package co.omise.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public class Shipping {
    @JsonProperty("address")
    public String address;
    @JsonProperty("postal_code")
    public String postalCode;
    @JsonProperty("country")
    public String country;
    @JsonProperty("street1")
    public String street1;
}
