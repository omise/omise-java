package co.omise.models;

/**
 * Represents Omise Balance object.
 *
 * @see <a href="https://www.omise.co/balance-api">Balance API</a>
 */
public class Balance extends Model {
    private long available;
    private long total;
    private String currency;

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
