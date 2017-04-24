package co.omise.resources;

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
public final class Operation {
    private final Serializer serializer = Serializer.defaultSerializer();
    private final OkHttpClient httpClient;

    private String method;
    private HttpUrl httpUrl;
    private Params params;

    Operation(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Sets the HTTP method.
     *
     * @param method The HTTP method to use.
     * @return Current {@link Operation}.
     */
    public Operation method(String method) {
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

    /**
     * Executes the operation using the parent {@link Resource}'s {@link OkHttpClient},
     * sending any given {@link Params} and automatically deserializes the result into
     * an instance of the given class.
     *
     * @param klass The {@link Class} of the result.
     * @param <T>   The type of the result to return.
     * @return An instance of the type T deserialized from the response body.
     * @throws IOException    when encountering general network communications problem.
     * @throws OmiseException when receiving an error object from the Omise API.
     */
    public <T extends OmiseObject> T returns(Class<T> klass) throws IOException, OmiseException {
        InputStream stream = preprocess(roundtrip());
        if (stream == null) {
            return null;
        }

        try {
            return serializer.deserialize(stream, klass);
        } finally {
            stream.close();
        }
    }

    /**
     * Executes the operation using the parent {@link Resource}'s {@link OkHttpClient},
     * sending any given {@link Params} and automatically deserializes the result into
     * an instance of the given generic type reference.
     *
     * @param typeRef The generic {@link TypeReference} of the result.
     * @param <T>     The type of the result to return.
     * @return An instance of the type T deserialized from the response body.
     * @throws IOException    when encountering general network communications problem.
     * @throws OmiseException when receiving an error object from the Omise API.
     */
    public <T extends OmiseObject> T returns(TypeReference<T> typeRef) throws IOException, OmiseException {
        InputStream stream = preprocess(roundtrip());
        if (stream == null) {
            return null;
        }

        try {
            return serializer.deserialize(stream, typeRef);
        } finally {
            stream.close();
        }
    }

    private InputStream preprocess(Response response) throws IOException, OmiseException {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }

        InputStream stream = body.byteStream();
        if (200 < response.code() || response.code() >= 300) {
            try {
                OmiseException ex = serializer.deserialize(stream, OmiseException.class);
                ex.setHttpStatusCode(response.code());
                throw ex;
            } finally {
                stream.close();
            }
        }

        return stream;
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
