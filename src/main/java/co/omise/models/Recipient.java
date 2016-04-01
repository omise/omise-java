package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents Omise Recipient object.
 *
 * @see <a href="https://www.omise.co/recipients-api">Recipients API</a>
 */
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
        private BankAccount.BankAccountParams bankAccount;

        public RecipientParams name(String name) {
            add("name", name);
            return this;
        }

        public RecipientParams email(String email) {
            add("email", email);
            return this;
        }

        public RecipientParams description(String description) {
            add("description", description);
            return this;
        }

        public RecipientParams type(RecipientType type) {
            add("type", type.name().toLowerCase());
            return this;
        }

        public RecipientParams taxId(String taxId) {
            add("tax_id", taxId);
            return this;
        }

        public RecipientParams bankAccount(BankAccount.BankAccountParams bankAccount) {
            if (bankAccount != null) {
                bankAccount.addTo(this);
            }
            return this;
        }
    }
}
