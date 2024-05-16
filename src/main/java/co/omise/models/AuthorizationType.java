package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AuthorizationType {
    @JsonProperty("pre_auth")
    PreAuth,
    @JsonProperty("final_auth")
    FinalAuth
}
