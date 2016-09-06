package co.omise.models;

/**
 * Represents Omise Event object.
 *
 * @see <a href="https://www.omise.co/events-api">Events API</a>
 */
public class Event<T extends Model> extends Model {
    private String key;

    private T data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
