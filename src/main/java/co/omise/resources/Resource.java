package co.omise.resources;

import co.omise.Client;
import co.omise.Endpoint;
import co.omise.Serializer;
import com.google.common.base.Preconditions;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

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
        return new Operation(httpClient).method(method).httpUrl(url);
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
