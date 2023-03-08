package co.omise.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public enum PlatformType {
    @JsonEnumDefaultValue
    @JsonProperty("unknown")
    Unknown,
    @JsonProperty("WEB")
    Web,
    @JsonProperty("IOS")
    iOS,
    @JsonProperty("ANDROID")
    Android;

    @Override
    public String toString() {
        String name = super.toString();
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields) {
            if (field.getName().equals(name) && field.isAnnotationPresent(JsonProperty.class)) {
                return field.getAnnotation(JsonProperty.class).value();
            }
        }
        return name;
    }
}
