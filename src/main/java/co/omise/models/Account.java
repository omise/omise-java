package co.omise.models;

/**
 * Represents Omise Account object.
 *
 * @see <a href="https://www.omise.co/account-api">Account API</a>
 */
public class Account extends Model {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
