package co.omise.requests;

import co.omise.Client;
import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.Model;
import com.google.common.base.Preconditions;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * RequestBuilder is base class, all extending classes would be used to generate a {@link Request<T>} that would then be passed on to {@link Client} in order
 * to carry out a certain task through an HTTP request
 *
 * @param <T> the generic type for any Model that would need to be returned by the {@link Client} when this request is passed to it
 */
public abstract class RequestBuilder<T extends Model> {
    private final Serializer serializer = Serializer.defaultSerializer();
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    protected static final String POST = "POST";
    protected static final String GET = "GET";
    protected static final String PATCH = "PATCH";

    /**
     * Builds request with all its enclosing information and extra params (if available).
     *
     * @return built {@link Request<T>}
     * @throws IOException the I/O when {@link Serializer} is unable to correctly serialize the content of the payload using Jackson
     */
    public Request<T> build() throws IOException {
        return new Request<T>(method(), path(), contentType(), payload());
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
     * * @throws IOException the I/O when {@link Serializer} is unable to correctly serialize the content of the class using Jackson
     */
    protected RequestBody payload() throws IOException {
        //Has to be null as it would fail for GET requests
        return null;
    }

    /**
     * Serializes all the enclosed parameters in a child RequestBuilder. This method should be called in the return statement of the overridden payload() method.
     *
     * @return the {@link RequestBody}
     * @throws IOException the I/O when {@link Serializer} is unable to correctly serialize the content of the class using Jackson
     */
    protected RequestBody serialize() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(4096);
        serializer().serializeRequestBuilder(stream, this);
        return RequestBody.create(JSON_MEDIA_TYPE, stream.toByteArray());
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
}