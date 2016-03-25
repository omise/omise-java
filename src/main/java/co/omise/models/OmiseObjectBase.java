package co.omise.models;

public abstract class OmiseObjectBase implements OmiseObject {
    private String object;
    private String location;

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
}
