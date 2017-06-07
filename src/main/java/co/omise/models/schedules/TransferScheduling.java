package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferScheduling {
    private String recipient;
    private long amount;
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
   
    public float getPercentageOfBalance() {
        return percentageOfBalance;
    }

    public void setPercentageOfBalance(float percentageOfBalance) {
        this.percentageOfBalance = percentageOfBalance;
    }
}
