package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reference extends Model {

    @JsonProperty("va_code")
    private String vaCode;

    public String getVaCode() {
        return vaCode;
    }

    public void setVaCode(String vaCode) {
        this.vaCode = vaCode;
    }

}
