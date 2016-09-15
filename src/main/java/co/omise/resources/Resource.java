package co.omise.resources;

import co.omise.Client;
import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.OmiseException;
import co.omise.models.OmiseObject;
import co.omise.models.Params;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource is the base class providing HTTP requests functionality
 * to the child resource classes.
 *
 * @see Client
 * @see Serializer
 * @see OkHttpClient
 * @see Operation
 */
public abstract class Resource {
    private final OkHttpClient httpClient;
    private final Serializer serializer = Serializer.defaultSerializer();

    /**
     * Creates the Resource that calls Omise API using the given {@link OkHttpClient}.
     *
     * @param httpClient A valid {@link OkHttpClient} instance.
     */
    protected Resource(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Returns {@link OkHttpClient} given in the constructor.
     *
     * @return An {@link OkHttpClient} instance.
     */
    protected OkHttpClient httpClient() {
        return httpClient;
    }

    /**
     * Starts an HTTP GET {@link Operation} on the given {@link HttpUrl}.
     *
     * @param httpUrl The {@link HttpUrl} to GET from. Use the {@link #buildUrl(Endpoint, String, String...)} method
     *                to build a valid URL.
     * @return An {@link Operation} builder.
     */
    protected Operation httpGet(HttpUrl httpUrl) {
        return httpOp("GET", httpUrl);
    }

    /**
     * Starts an HTTP POST {@link Operation} on the given {@link HttpUrl}.
     *
     * @param httpUrl The {@link HttpUrl} to POST to. Use the {@link #buildUrl(Endpoint, String, String...)} method
     *                to build a valid URL.
     * @return An {@link Operation} builder.
     */
    protected Operation httpPost(HttpUrl httpUrl) {
        return httpOp("POST", httpUrl);
    }

    /**
     * Starts an HTTP PATCH {@link Operation} on the given {@link HttpUrl}.
     *
     * @param httpUrl The {@link HttpUrl} to PATCH to. Use the {@link #buildUrl(Endpoint, String, String...)} method
     *                to build a valid URL.
     * @return An {@link Operation} builder.
     */
    protected Operation httpPatch(HttpUrl httpUrl) {
        return httpOp("PATCH", httpUrl);
    }

    /**
     * Starts an HTTP DELETE {@link Operation} on the given {@link HttpUrl}.
     *
     * @param httpUrl The {@link HttpUrl} to DELETE from. Use the {@link #buildUrl(Endpoint, String, String...)} method
     *                to build a valid URL.
     * @return An {@link Operation} builder.
     */
    protected Operation httpDelete(HttpUrl httpUrl) {
        return httpOp("DELETE", httpUrl);
    }

    /**
     * Starts an HTTP {@link Operation} with the given method and {@link HttpUrl}.
     *
     * @param method The uppercased HTTP method to use.
     * @param url    An {@link HttpUrl} target.
     * @return An {@link Operation} builder.
     */
    protected Operation httpOp(String method, HttpUrl url) {
        return new Operation().method(method).httpUrl(url);
    }

    /**
     * Builds and returns a valid {@link HttpUrl} pointing to the given {@link Endpoint}'s host
     * and with all the supplied segments concatenated.
     *
     * @param endpoint The Omise API {@link Endpoint} to point to.
     * @param path     The base API path.
     * @param segments Additional URL path segments that should be appended.
     * @return An {@link HttpUrl} instance.
     */
    protected HttpUrl buildUrl(Endpoint endpoint, String path, String... segments) {
        Preconditions.checkNotNull(endpoint);
        Preconditions.checkNotNull(path);

        HttpUrl.Builder builder = endpoint.buildUrl()
                .addPathSegment(path);

        for (String segment : segments) {
            if (segment == null || segment.isEmpty()) {
                continue;
            }

            builder = builder.addPathSegment(segment);
        }

        return builder.build();
    }

    /**
     * The Operation class is a builder that lets you builds an HTTP operation
     * from HTTP method, {@link Params} and {@link HttpUrl} and also handles
     * result deserialization when a response is received.
     * <p>
     * Use the {@link #returns(Class)} method to finalize building and actually
     * sends the HTTP request.
     * </p>
     *
     * @see Resource
     */
    protected final class Operation {
        private String method;
        private HttpUrl httpUrl;
        private Params params;

        /**
         * Sets the HTTP method.
         *
         * @param method The HTTP method to use.
         * @return Current {@link Operation}.
         */
        protected Operation method(String method) {
            Preconditions.checkNotNull(method);
            this.method = method;
            return this;
        }

        /**
         * Sets the {@link HttpUrl}.
         *
         * @param httpUrl The {@link HttpUrl}.
         * @return Current {@link Operation}.
         */
        public Operation httpUrl(HttpUrl httpUrl) {
            this.httpUrl = httpUrl;
            return this;
        }

        /**
         * Sets the {@link Params} to send.
         *
         * @param params The {@link Params} to send.
         * @return Current {@link Operation}.
         */
        public Operation params(Params params) {
            this.params = params;
            return this;
        }

        // TODO:
        // DRY #returns(Class) and #returns(TypeReference) or change our code
        // to stick to a one type and not the other.

        /**
         * Executes the operation using the parent {@link Resource}'s {@link OkHttpClient},
         * sending any given {@link Params} and automatically deserializes the result into
         * an instance of the given class.
         *
         * @param klass The {@link Class} of the result.
         * @param <T>   The type of the result to return.
         * @return An instance of the type T deserialized from the response body.
         * @throws IOException when encountering general network communications problem.
         * @throws OmiseException  when receiving an error object from the Omise API.
         */
        public <T extends OmiseObject> T returns(Class<T> klass) throws IOException, OmiseException {
            Response response = roundtrip();
            ResponseBody body = response.body();
            if (body == null) {
                return null;
            }

            InputStream stream = body.byteStream();
            if (200 < response.code() || response.code() >= 300) {
                OmiseException ex = serializer.deserialize(stream, OmiseException.class);
                ex.setHttpStatusCode(response.code());
                throw ex;
            }

            return serializer.deserialize(stream, klass);
        }

        /**
         * Executes the operation using the parent {@link Resource}'s {@link OkHttpClient},
         * sending any given {@link Params} and automatically deserializes the result into
         * an instance of the given generic type reference.
         *
         * @param typeRef The generic {@link TypeReference} of the result.
         * @param <T>     The type of the result to return.
         * @return An instance of the type T deserialized from the response body.
         * @throws IOException when encountering general network communications problem.
         * @throws OmiseException  when receiving an error object from the Omise API.
         */
        public <T extends OmiseObject> T returns(TypeReference<T> typeRef) throws IOException, OmiseException {
            Response response = roundtrip();
            ResponseBody body = response.body();
            if (body == null) {
                return null;
            }

            InputStream stream = body.byteStream();
            if (200 < response.code() || response.code() >= 300) {
                OmiseException ex = serializer.deserialize(stream, OmiseException.class);
                ex.setHttpStatusCode(response.code());
                throw ex;
            }

            return serializer.deserialize(stream, typeRef);
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

            } else if (HttpMethod.requiresRequestBody(method)) { // params == null
                body = new FormBody.Builder().build();
            }

            Request request = new Request.Builder()
                    .method(method, body)
                    .url(urlBuilder.build())
                    .build();

            return httpClient.newCall(request).execute();
        }
    }
}
