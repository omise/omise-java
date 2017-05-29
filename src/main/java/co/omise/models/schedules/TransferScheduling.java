package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferScheduling {
    private String recipient;
    private long amount;
    private String currency;
    @JsonProperty("percentage_of_balance")
    private float percentageOfBalance;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPercentageOfBalance() {
        return percentageOfBalance;
    }

    public void setPercentageOfBalance(float percentageOfBalance) {
        this.percentageOfBalance = percentageOfBalance;
    }
}
