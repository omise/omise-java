package co.omise.resources;

import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.OmiseError;
import co.omise.models.OmiseObject;
import co.omise.models.Params;
import com.fasterxml.jackson.core.type.TypeReference;
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
        return new Operation().method("GET").httpUrl(httpUrl);
    }

    protected Operation httpPost(HttpUrl httpUrl) {
        return new Operation().method("POST").httpUrl(httpUrl);
    }

    protected Operation httpDelete(HttpUrl httpUrl) {
        return new Operation().method("DELETE").httpUrl(httpUrl);
    }

    protected HttpUrl.Builder apiUrl(String path) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(Endpoint.API.host())
                .addPathSegment(path);
    }

    protected HttpUrl.Builder vaultUrl(String path) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(Endpoint.VAULT.host())
                .addPathSegment(path);
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

        public <T extends OmiseObject> T returns(Class<T> resultKlass) throws IOException {
            Response response = roundtrip();
            InputStream stream = response.body().byteStream();
            if (200 <= response.code() && response.code() < 300) {
                return serializer.deserialize(stream, resultKlass);
            } else {
                throw serializer.deserialize(stream, OmiseError.class);
            }
        }

        public <T extends OmiseObject> T returns(TypeReference<T> resultRef) throws IOException {
            Response response = roundtrip();
            InputStream stream = response.body().byteStream();
            if (200 <= response.code() && response.code() < 300) {
                return serializer.deserialize(stream, resultRef);
            } else {
                throw serializer.deserialize(stream, OmiseError.class);
            }
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
