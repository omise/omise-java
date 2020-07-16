package co.omise.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;

public enum SourceType {
    @JsonEnumDefaultValue
    @JsonProperty("unknown")
    Unknown,
    @JsonProperty("alipay")
    Alipay,
    @JsonProperty("barcode_alipay")
    BarcodeAlipay,
    @JsonProperty("barcode_wechat")
    BarcodeWechat,
    @JsonProperty("bill_payment_tesco_lotus")
    BillPaymentTescoLotus,
    @JsonProperty("econtext")
    Econtext,
    @JsonProperty("installment_bay")
    InstallmentBay,
    @JsonProperty("installment_bbl")
    InstallmentBbl,
    @JsonProperty("installment_first_choice")
    InstallmentFirstChoice,
    @JsonProperty("installment_kbank")
    InstallmentKbank,
    @JsonProperty("installment_ktc")
    InstallmentKtc,
    @JsonProperty("internet_banking_bay")
    InternetBankingBay,
    @JsonProperty("internet_banking_bbl")
    InternetBankingBbl,
    @JsonProperty("internet_banking_ktb")
    InternetBankingKtb,
    @JsonProperty("internet_banking_scb")
    InternetBankingScb,
    @JsonProperty("paynow")
    Paynow,
    @JsonProperty("points_citi")
    PointsCiti,
    @JsonProperty("promptpay")
    PromptPay,
    @JsonProperty("truemoney")
    TrueMoney;

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