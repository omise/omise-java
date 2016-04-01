package co.omise.models;

/**
 * Represents Omise Error object.
 *
 * @see <a href="https://www.omise.co/api-errors">Errors</a>
 */
public class OmiseError extends Error implements OmiseObject {
    private String object;
    private String location;
    private String code;
    private int httpStatusCode;
    private String message;

    @Override
    public String getObject() {
        return object;
    }

    @Override
    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(httpStatusCode) + "/" + code + ") " + message;
    }
}
