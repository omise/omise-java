package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents Omise Payment method object that is found in the response for the Capability API
 *
 * @see <a href="https://www.omise.co/capability-api">Capability API</a>
 */
public class PaymentMethod extends Model {
    private String name;
    private List<String> currencies;
    @JsonProperty("card_brands")
    private List<String> cardBrands;
    @JsonProperty("installment_terms")
    private List<Integer> installmentTerms;
    private List<Bank> banks;

    public PaymentMethod() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<String> getCardBrands() {
        return cardBrands;
    }

    public void setCardBrands(List<String> cardBrands) {
        this.cardBrands = cardBrands;
    }

    public List<Integer> getInstallmentTerms() {
        return installmentTerms;
    }

    public void setInstallmentTerms(List<Integer> installmentTerms) {
        this.installmentTerms = installmentTerms;
    }

    public List<Bank> getBanks() {
        return this.banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
