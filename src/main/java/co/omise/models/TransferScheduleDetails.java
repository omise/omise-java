package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferScheduleDetails {
    private String recipient;
    private Long amount;
    private String currency;
    @JsonProperty("percentage_of_balance")
    private Float percentageOfBalance;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getPercentageOfBalance() {
        return percentageOfBalance;
    }

    public void setPercentageOfBalance(Float percentageOfBalance) {
        this.percentageOfBalance = percentageOfBalance;
    }
}
