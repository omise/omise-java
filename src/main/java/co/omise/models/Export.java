package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Export extends Model {
    @JsonProperty("download_uri")
    private String downloadUri;
    @JsonProperty("file_type")
    private String fileType;
    @JsonProperty("filter_params")
    private Map<String, Object> filterParams;
    @JsonProperty("filter_type")
    private String filterType;
    private String location;
    private String name;
    @JsonProperty("object_type")
    private String objectType;
    private long rows;
    private String status;
    private String team;

    public String getDownloadUri() {
        return this.downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Map<String, Object> getFilterParams() {
        return this.filterParams;
    }

    public void setFilterParams(Map<String, Object> filterParams) {
        this.filterParams = filterParams;
    }

    public String getFilterType() {
        return this.filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public long getRows() {
        return this.rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}