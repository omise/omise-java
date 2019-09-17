package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SourceType {
    @JsonProperty("alipay")Alipay,
    @JsonProperty("barcode_alipay")BarcodeAlipay,
    @JsonProperty("bill_payment_tesco_lotus")BillPaymentTescoLotus,
    @JsonProperty("econtext")Econtext,
    @JsonProperty("installment_bay")InstallmentBay,
    @JsonProperty("installment_bbl")InstallmentBbl,
    @JsonProperty("installment_first_choice")InstallmentFirstChoice,
    @JsonProperty("installment_kbank")InstallmentKbank,
    @JsonProperty("installment_ktc")InstallmentKtc,
    @JsonProperty("internet_banking_bay")InternetBankingBay,
    @JsonProperty("internet_banking_bbl")InternetBankingBbl,
    @JsonProperty("internet_banking_ktb")InternetBankingKtb,
    @JsonProperty("internet_banking_scb")InternetBankingScb,
    @JsonProperty("truemoney")TrueMoney,
    @JsonProperty("points")Points,
    @JsonProperty("paynow")Paynow,
}