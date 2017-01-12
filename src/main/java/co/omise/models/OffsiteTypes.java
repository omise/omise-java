package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OffsiteTypes {
    @JsonProperty("internet_banking_scb")InternetBankingSCB,
    @JsonProperty("internet_banking_bbl")InternetBankingBBL,
    @JsonProperty("internet_banking_bay")InternetBankingBAY,
    @JsonProperty("internet_banking_ktb")InternetBankingKTB,
}
