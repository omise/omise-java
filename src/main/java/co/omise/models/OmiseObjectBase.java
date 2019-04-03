package co.omise.models;

import java.io.Serializable;

public abstract class OmiseObjectBase implements OmiseObject, Serializable {

    private String object;
    private String location;

    public OmiseObjectBase() {
    }

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
