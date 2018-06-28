package co.omise.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum SourceType {
    @JsonProperty("internet_banking_bay")
    InternetBankingBay,
    @JsonProperty("internet_banking_ktb")
    InternetBankingKtb,
    @JsonProperty("internet_banking_scb")
    InternetBankingScb,
    @JsonProperty("internet_banking_bbl")
    InternetBankingBbl,
    @JsonProperty("alipay")
    Alipay,
    @JsonProperty("bill_payment_tesco_lotus")
    BillPaymentTescoLotus,
    @JsonProperty("barcode_alipay")
    BarcodeAlipay;

    @Override
    public String toString() {
        switch (this) {
            case InternetBankingBay:
                return "internet_banking_bay";
            case InternetBankingKtb:
                return "internet_banking_ktb";
            case InternetBankingScb:
                return "internet_banking_scb";
            case InternetBankingBbl:
                return "internet_banking_bbl";
            case BillPaymentTescoLotus:
                return "bill_payment_tesco_lotus";
            case Alipay:
                return "alipay";
            case BarcodeAlipay:
                return "barcode_alipay";
            default:
                return "";
        }
    }
}
