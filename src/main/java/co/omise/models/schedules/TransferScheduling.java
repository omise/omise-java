package co.omise.models.schedules;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public static class Params extends co.omise.models.Params {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long amount;
        @JsonProperty("precentage_of_balance")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Float percentageOfBalance;
        @JsonProperty
        private String recipient;

        public Params amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Params percentageOfBalance(Float percent) {
            this.percentageOfBalance = percent;
            return this;
        }

        public Params recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }
    }
}
