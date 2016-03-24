package co.omise.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

public class Event<T extends Model> extends Model {
    private String key;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.CUSTOM,
            include = JsonTypeInfo.As.EXISTING_PROPERTY,
            property = "object",
            visible = true
    )
    @JsonTypeIdResolver(ModelTypeResolver.class)
    private T data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
