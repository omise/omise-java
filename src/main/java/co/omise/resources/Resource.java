package co.omise.resources;

import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.OmiseError;
import co.omise.models.OmiseObject;
import co.omise.models.Params;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;

public abstract class Resource {
    private final OkHttpClient httpClient;
    private final Serializer serializer = Serializer.defaultSerializer();

    protected Resource(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected OkHttpClient httpClient() {
        return httpClient;
    }

    protected Operation httpGet(HttpUrl httpUrl) {
        return httpOp("GET", httpUrl);
    }

    protected Operation httpPost(HttpUrl httpUrl) {
        return httpOp("POST", httpUrl);
    }

    protected Operation httpPatch(HttpUrl httpUrl) {
        return httpOp("PATCH", httpUrl);
    }

    protected Operation httpDelete(HttpUrl httpUrl) {
        return httpOp("DELETE", httpUrl);
    }

    protected Operation httpOp(String method, HttpUrl url) {
        return new Operation().method(method).httpUrl(url);
    }

    protected HttpUrl buildUrl(Endpoint endpoint, String path, String... additionalPathSegments) {
        Preconditions.checkNotNull(endpoint);
        Preconditions.checkNotNull(path);

        HttpUrl.Builder builder = endpoint.buildUrl()
                .addPathSegment(path);

        for (String segment : additionalPathSegments) {
            if (segment == null || segment.isEmpty()) {
                continue;
            }

            builder = builder.addPathSegment(segment);
        }

        return builder.build();
    }

    protected final class Operation {
        private String method;
        private HttpUrl httpUrl;
        private Params params;

        public Operation method(String method) {
            this.method = method;
            return this;
        }

        public Operation httpUrl(HttpUrl httpUrl) {
            this.httpUrl = httpUrl;
            return this;
        }

        public Operation params(Params params) {
            this.params = params;
            return this;
        }

        // TODO: DRY both these 2 variants
        public <T extends OmiseObject> T returns(Class<T> resultKlass) throws IOException {
            Response response = roundtrip();
            ResponseBody body = response.body();
            if (body == null) {
                return null;
            }

            InputStream stream = body.byteStream();
            if (200 < response.code() || response.code() >= 300) {
                throw serializer.deserialize(stream, OmiseError.class);
            }

            return serializer.deserialize(stream, resultKlass);
        }

        public <T extends OmiseObject> T returns(TypeReference<T> resultRef) throws IOException {
            Response response = roundtrip();
            ResponseBody body = response.body();
            if (body == null) {
                return null;
            }

            InputStream stream = body.byteStream();
            if (200 < response.code() || response.code() >= 300) {
                throw serializer.deserialize(stream, OmiseError.class);
            }

            return serializer.deserialize(stream, resultRef);
        }

        private Response roundtrip() throws IOException {
            RequestBody body = null;
            HttpUrl.Builder urlBuilder = httpUrl.newBuilder();

            if (params != null) {
                ImmutableMap<String, String> queries = params.query();
                if (queries != null && !queries.isEmpty()) {
                    for (ImmutableMap.Entry<String, String> pair : queries.entrySet()) {
                        urlBuilder = urlBuilder.addQueryParameter(pair.getKey(), pair.getValue());
                    }
                }

                body = params.body();
            }

            Request request = new Request.Builder()
                    .method(method, body)
                    .url(urlBuilder.build())
                    .build();

            return httpClient.newCall(request).execute();
        }
    }
}
