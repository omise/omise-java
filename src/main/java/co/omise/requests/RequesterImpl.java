package co.omise.requests;

import co.omise.Client;
import co.omise.Serializer;
import co.omise.models.OmiseException;
import co.omise.models.OmiseObjectBase;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;

import java.io.IOException;
import java.io.InputStream;

/**
 * RequesterImpl is the concrete Implementation of {@link Requester} that is used in {@link Client} to carry the actual HTTP operation
 *
 * @see OkHttpClient
 * @see Serializer
 */
public class RequesterImpl implements Requester {
    private OkHttpClient httpClient;
    private Serializer serializer;

    /**
     * Instantiates a new RequesterImpl instance.
     *
     * @param httpClient the {@link OkHttpClient} used to send HTTP requests
     * @param serializer the {@link Serializer} used to deserialize the responses
     */
    public RequesterImpl(OkHttpClient httpClient, Serializer serializer) {
        this.httpClient = httpClient;
        this.serializer = serializer;
    }

    @Override
    public <T extends OmiseObjectBase, R extends Request<T>> T sendRequest(R request) throws IOException, OmiseException {
        InputStream stream = preProcess(roundTrip(request.getPath(), request.getPayload(), request.getMethod()));
        if (stream == null) {
            return null;
        }

        try {
            if (request.getType().isClassType()) {
                return serializer.deserialize(stream, request.getType().getClassType());
            } else {
                return serializer.deserialize(stream, request.getType().getTypeReference());
            }
        } finally {
            stream.close();
        }
    }

    @Override
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Process the reponse returned by the API
     *
     * @param response {@link Response} response object returned by {@link OkHttpClient}
     * @return {@link InputStream} the input response turned into an input stream
     * @throws IOException    the general I/O error caused by issues during deserialization in the {@link Serializer}
     * @throws OmiseException the custom exception thrown for response errors
     */
    private InputStream preProcess(Response response) throws IOException, OmiseException {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }

        InputStream stream = body.byteStream();
        if (200 < response.code()) {
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

    /**
     * Carries out the HTTP request
     *
     * @param url    api url path
     * @param body   additional request params (if available)
     * @param method HTTP method
     * @return {@link Response} response object returned by {@link OkHttpClient}
     * @throws IOException I/O exception thrown by {@link OkHttpClient} during the HTTP request
     */
    private Response roundTrip(HttpUrl url, RequestBody body, String method) throws IOException {
        if (body == null && HttpMethod.requiresRequestBody(method)) { // params == null
            body = new FormBody.Builder().build();
        }

        okhttp3.Request request = new okhttp3.Request.Builder()
                .method(method, body)
                .url(url)
                .build();

        return httpClient.newCall(request).execute();
    }
}