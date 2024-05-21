package co.omise.models;

/**
 * Omise Exception object contains information about errors returned from the Omise API.
 *
 * @see <a href="https://www.omise.co/api-errors">Errors</a>
 */
public class OmiseException extends Exception implements OmiseObject {
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
        return "(" + httpStatusCode + "/" + code + ") " + message;
    }
}
