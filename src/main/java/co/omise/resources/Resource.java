package co.omise.resources;

import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.OmiseError;
import co.omise.models.OmiseObject;
import co.omise.models.Params;
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

    protected Operation httpGet(String path) {
        return new Operation().method("GET").path(path);
    }

    protected Operation httpPost(String path) {
        return new Operation().method("POST").path(path);
    }

    protected Operation httpDelete(String path) {
        return new Operation().method("DELETE").path(path);
    }

    protected final class Operation {
        private Endpoint endpoint;
        private String method;
        private String path;
        private Params params;

        public Operation endpoint(Endpoint endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Operation method(String method) {
            this.method = method;
            return this;
        }

        public Operation path(String path) {
            this.path = path;
            return this;
        }

        public Operation params(Params params) {
            this.params = params;
            return this;
        }

        public <T extends OmiseObject> T returns(Class<T> resultKlass) throws IOException {
            RequestBody body = null;
            HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                    .host(endpoint.host())
                    .addPathSegments(path);

            if (params != null) {
                ImmutableMap<String, String> queries = params.query();
                if (queries != null && !queries.isEmpty()) {
                    for (ImmutableMap.Entry<String, String> pair : queries.entrySet()) {
                        urlBuilder = urlBuilder.addQueryParameter(pair.getKey(), pair.getValue());
                    }
                }

                body = params.body();
            }

            Call call = httpClient.newCall(new Request.Builder()
                    .method(method, body)
                    .url(urlBuilder.build())
                    .build());

            Response response = call.execute();
            InputStream stream = response.body().byteStream();
            if (200 <= response.code() && response.code() < 300) {
                return serializer.deserialize(stream, resultKlass);
            } else {
                throw serializer.deserialize(stream, OmiseError.class);
            }
        }
    }
}
