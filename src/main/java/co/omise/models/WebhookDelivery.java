package co.omise.models;

public class WebhookDelivery extends Model {
    private long status;
    private String uri;

    public long getStatus() {
        return this.status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}