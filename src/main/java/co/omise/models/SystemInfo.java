package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import okhttp3.HttpUrl;

import java.util.List;

public class SystemInfo extends Model {
    private String location;
    private List<String> versions;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getVersions() {
        return this.versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public static class GetRequestBuilder extends RequestBuilder<SystemInfo> {

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "");
        }

        @Override
        protected ResponseType<SystemInfo> type() {
            return new ResponseType<>(SystemInfo.class);
        }
    }
}