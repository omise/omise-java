package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Field;

public enum FlowType {
    @JsonProperty("offline")
    Offline,
    @JsonProperty("redirect")
    Redirect,
    @JsonProperty("app_redirect")
    AppRedirect;

    @Override
    public String toString() {
        String name = super.toString();
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields) {
            if (field.getName() == name && field.isAnnotationPresent(JsonProperty.class)) {
                return field.getAnnotation(JsonProperty.class).value();
            }
        }
        return name;
    }
}