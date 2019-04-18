package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.joda.time.DateTime;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "object",
        visible = true
)
@JsonTypeIdResolver(ModelTypeResolver.class)
public abstract class Model extends OmiseObjectBase {
    private String id;
    @JsonProperty("livemode")
    private boolean liveMode;
    @JsonProperty("created")
    private DateTime created;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean deleted;

    public Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLiveMode() {
        return liveMode;
    }

    public void setLiveMode(boolean liveMode) {
        this.liveMode = liveMode;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
