package co.omise.requests;

import co.omise.Client;
import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.OmiseObjectBase;
import co.omise.models.Params;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * RequestBuilder is base class, all extending classes would be used to generate a {@link Request} that would then be passed on to {@link Client} in order
 * to carry out a certain task through an HTTP request
 *
 * @param <T> the generic type for any Model that would need to be returned by the {@link Client} when this request is passed to it
 */
public abstract class RequestBuilder<T extends OmiseObjectBase> {
    private final Serializer serializer = Serializer.defaultSerializer();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    protected static final String POST = "POST";
    protected static final String GET = "GET";
    protected static final String PATCH = "PATCH";
    protected static final String DELETE = "DELETE";

    /**
     * Builds request with all its enclosing information and extra params (if available).
     *
     * @return built {@link Request} of type {@link OmiseObjectBase}
     * @throws IOException the I/O when {@link Serializer} is unable to correctly serialize the content of the payload using Jackson
     */
    public Request<T> build() throws IOException {
        return new Request<>(method(), path(), contentType(), payload(), type());
    }

    /**
     * Default HTTP method.
     *
     * @return the HTTP method as a string
     */
    protected String method() {
        return GET;
    }

    /**
     * Abstract method that needs to be implement by all children of this class to provide API Path
     *
     * @return the url path as {@link HttpUrl}
     */
    protected abstract HttpUrl path();

    /**
     * Default Content type of the HTTP Request.
     *
     * @return the Content type as a string
     */
    protected String contentType() {
        return "application/json";
    }

    /**
     * Additional parameters for the request, which is null by default to avoid crashes for requests that do not accept params (eg: GET)
     *
     * @return the params as a {@link RequestBody}
     * @throws IOException the I/O when {@link Serializer} is unable to correctly serialize the content of the class using Jackson
     */
    protected RequestBody payload() throws IOException {
        //Has to be null as it would fail for GET requests
        return null;
    }

    /**
     * Abstract method that needs to be implement by all children of this class to provide response type
     *
     * @return ResponseType type of response
     */
    protected abstract ResponseType<T> type();

    /**
     * Serializes all the enclosed parameters in a child RequestBuilder. This method should be called in the return statement of the overridden payload() method.
     *
     * @return the {@link RequestBody}
     * @throws IOException the I/O when {@link Serializer} is unable to correctly serialize the content of the class using Jackson
     */
    protected RequestBody serialize() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(4096);
        serializer().serializeRequestBuilder(stream, this);
        return RequestBody.Companion.create(stream.toByteArray(), JSON_MEDIA_TYPE);
    }

    /**
     * Gets the instance of  {@link Serializer} used in serlialize() method here.
     *
     * @return the {@link Serializer}
     */
    protected Serializer serializer() {
        return serializer;
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
        return new HttpUrlBuilder(endpoint, path, serializer).segments(segments).build();
    }

    /**
     * Builds and returns a valid {@link HttpUrl} pointing to the given {@link Endpoint}'s host
     * and with all the supplied params concatenated to the url.
     *
     * @param endpoint The Omise API {@link Endpoint} to point to.
     * @param path     The base API path.
     * @param params   Additional URL params that should be appended.
     * @return An {@link HttpUrl} instance.
     */
    protected HttpUrl buildUrl(Endpoint endpoint, String path, Params params) {
        Objects.requireNonNull(params);
        return new HttpUrlBuilder(endpoint, path, serializer).params(params).build();
    }

    public static class HttpUrlBuilder {
        private final Endpoint endpoint;
        private final String path;
        private String[] segments;
        private Params params;
        private final Serializer serializer;

        public HttpUrlBuilder(Endpoint endpoint, String path, Serializer serializer) {
            this.endpoint = endpoint;
            this.path = path;
            this.serializer = serializer;
        }

        public HttpUrlBuilder segments(String... segments) {
            this.segments = segments.clone();
            return this;
        }

        public HttpUrlBuilder params(Params params) {
            this.params = params;
            return this;
        }

        public HttpUrl build() {
            Objects.requireNonNull(endpoint);
            Objects.requireNonNull(path);

            HttpUrl.Builder builder = endpoint.buildUrl().addPathSegment(path);

            if (segments != null) {
                for (String segment : segments) {
                    if (segment == null || segment.isEmpty()) {
                        continue;
                    }

                    builder.addPathSegment(segment);
                }
            }

            if (params != null) {
                Map<String, String> queries = params.query(serializer);
                if (!queries.isEmpty()) {
                    for (Map.Entry<String, String> pair : queries.entrySet()) {
                        builder.addQueryParameter(pair.getKey(), pair.getValue());
                    }
                }
            }

            return builder.build();
        }
    }
}