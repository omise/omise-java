package co.omise.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public class Shipping {
    public String street1;
    public String street2;
    public String city;
    public String state;
    public String country;
    @JsonProperty("postal_code")
    public String postalCode;
}
