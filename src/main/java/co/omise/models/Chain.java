package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

public class Chain extends Model {
    private String email;
    private String key;
    private String location;
    private boolean revoked;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRevoked() {
        return this.revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Chain>> {
        private ScopedList.Options options;

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "chains", serializer())
                  .segments()
                  .params(options)
                  .build();
        }

        @Override
        protected ResponseType<ScopedList<Chain>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Chain>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Chain> {
        private String chainId;
        public GetRequestBuilder(String chainId) {
            this.chainId = chainId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "chains", chainId);
        }

        @Override
        protected ResponseType<Chain> type() {
            return new ResponseType<>(Chain.class);
        }
    }

    public static class RevokeRequestBuilder extends RequestBuilder<Chain> {
        private String chainId;
        public RevokeRequestBuilder(String chainId) {
            this.chainId = chainId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "chains", chainId, "revoke");
        }

        @Override
        protected ResponseType<Chain> type() {
            return new ResponseType<>(Chain.class);
        }
    }
}