package co.omise.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
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
    @JsonProperty("mobile_banking_scb")
    MobileBankingScb,
    @JsonProperty("alipay")
    Alipay,
    @JsonProperty("bill_payment_tesco_lotus")
    BillPaymentTescoLotus,
    @JsonProperty("barcode_alipay")
    BarcodeAlipay,
    @JsonProperty("econtext")
    Econtext,
    @JsonProperty("truemoney")
    TrueMoney,
    @JsonProperty("installment_bay")
    InstBankingBay,
    @JsonProperty("installment_first_choice")
    InstFirstChoice,
    @JsonProperty("installment_bbl")
    InstBbl,
    @JsonProperty("installment_ktc")
    InstKtc,
    @JsonProperty("installment_kbank")
    InstKBank,
    @JsonProperty("installment_scb")
    InstScb,
    @JsonProperty("installment_citi")
    InstCiti,
    @JsonEnumDefaultValue
    Unknown,
    @JsonProperty("paynow")
    Paynow,
    @JsonProperty("points_citi")
    PointsCiti,
    @JsonProperty("promptpay")
    PromptPay;

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
            case MobileBankingScb:
                return "mobile_banking_scb";
            case BillPaymentTescoLotus:
                return "bill_payment_tesco_lotus";
            case Alipay:
                return "alipay";
            case BarcodeAlipay:
                return "barcode_alipay";
            case Econtext:
                return "econtext";
            case TrueMoney:
                return "truemoney";
            case InstBankingBay:
                return "installment_bay";
            case InstFirstChoice:
                return "installment_first_choice";
            case InstBbl:
                return "installment_bbl";
            case InstKtc:
                return "installment_ktc";
            case InstKBank:
                return "installment_kbank";
            case InstScb:
                return "installment_scb";
            case InstCiti:
                return "installment_citi";
            case Paynow:
                return "paynow";
            case PointsCiti:
                return "points_citi";
            case PromptPay:
                return "promptpay";
            default:
                return "";
        }
    }
}
