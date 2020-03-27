package co.omise.models;


public class Barcode extends Model {
    private Document image;
    private String type;

    public Document getImage() {
        return this.image;
    }

    public void setImage(Document image) {
        this.image = image;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}