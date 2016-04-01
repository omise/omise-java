package co.omise.models;

import org.joda.time.DateTime;

/**
 * Represents Omise Transaction object.
 *
 * @see <a href="https://www.omise.co/transactions-api">Transactions API</a>
 */
public class Transaction extends Model {
    private long amount;
    private String currency;
    private TransactionType type;
    private String source;
    private DateTime transferable;

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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public DateTime getTransferable() {
        return transferable;
    }

    public void setTransferable(DateTime transferable) {
        this.transferable = transferable;
    }
}
