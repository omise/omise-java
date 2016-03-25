package co.omise.models;

import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Dispute extends Model {
    private long amount;
    private String currency;
    private DisputeStatus status;
    private String message;
    private String charge;

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

    public DisputeStatus getStatus() {
        return status;
    }

    public void setStatus(DisputeStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public static class Update extends Params {
        private String message;

        public Update message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query() {
            return null;
        }

        @Override
        public RequestBody body() {
            return new FormBody.Builder()
                    .add("message", message)
                    .build();
        }
    }
}
