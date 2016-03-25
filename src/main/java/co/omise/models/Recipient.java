package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Recipient extends Model {
    private boolean verified;
    private boolean active;
    private String name;
    private String email;
    private String description;
    private RecipientType type;
    @JsonProperty("tax_id")
    private String taxId;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    @JsonProperty("failure_code")
    private String failureCode;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipientType getType() {
        return type;
    }

    public void setType(RecipientType type) {
        this.type = type;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public static class Create extends RecipientParams {
    }

    public static class Update extends RecipientParams {
    }

    public static class RecipientParams extends Params {
        private String name;
        private String email;
        private String description;
        private RecipientType type;
        private String taxId;
        private BankAccount.BankAccountParams bankAccount;

        public RecipientParams name(String name) {
            this.name = name;
            return this;
        }

        public RecipientParams email(String email) {
            this.email = email;
            return this;
        }

        public RecipientParams description(String description) {
            this.description = description;
            return this;
        }

        public RecipientParams type(RecipientType type) {
            this.type = type;
            return this;
        }

        public RecipientParams taxId(String taxId) {
            this.taxId = taxId;
            return this;
        }

        public RecipientParams bankAccount(BankAccount.BankAccountParams bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query() {
            return null;
        }

        @Override
        public RequestBody body() {
            FormBody.Builder builder = new FormBody.Builder()
                    .add("name", name)
                    .add("email", email)
                    .add("description", description)
                    .add("tax_id", taxId)
                    .add("type", type.name().toLowerCase());

            if (bankAccount != null) {
                builder = bankAccount.addTo(builder);
            }

            return builder.build();
        }
    }
}
