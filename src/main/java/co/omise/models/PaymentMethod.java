package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentMethod extends Model {
    @JsonProperty("card_brands")
    private List<String> cardBrands;
    private List<String> currencies;
    @JsonProperty("installment_terms")
    private List<Integer> installmentTerms;
    private String name;
    private List<Bank> banks;

    public List<String> getCardBrands() {
        return this.cardBrands;
    }

    public void setCardBrands(List<String> cardBrands) {
        this.cardBrands = cardBrands;
    }

    public List<String> getCurrencies() {
        return this.currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<Integer> getInstallmentTerms() {
        return this.installmentTerms;
    }

    public void setInstallmentTerms(List<Integer> installmentTerms) {
        this.installmentTerms = installmentTerms;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bank> getBanks() {
        return this.banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
