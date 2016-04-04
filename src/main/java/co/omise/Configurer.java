package co.omise;

import com.google.common.base.Preconditions;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Configurer handles HTTP requests configuration. You can use the {@link #configure(Config, Request)} method
 * to setup your own {@link okhttp3.OkHttpClient} and avoid using the {@link Client} directly, for example.
 */
public final class Configurer implements Interceptor {
    private final Config config;

    Configurer(Config config) {
        Preconditions.checkNotNull(config);
        this.config = config;
    }

    /**
     * Configures a {@link Request} according to the given {@link Config}.
     *
     * @param config  A {@link Config} to use for configuration.
     * @param request An HTTP {@link Request} to configure.
     * @return A new {@link Request} instance with configurations from {@link Config} applied.
     */
    public static Request configure(Config config, Request request) {
        String apiVersion = config.apiVersion();

        // TODO: Avoid this loop.
        String key = null;
        for (Endpoint endpoint : Endpoint.all()) {
            if (!request.url().host().equals(endpoint.host())) {
                continue;
            }

            key = endpoint.authenticationKey(config);
            break;
        }

        Request.Builder builder = request.newBuilder()
                .addHeader("User-Agent", config.userAgent())
                .addHeader("Authorization", Credentials.basic(key, "x"));

        if (apiVersion != null && !apiVersion.isEmpty()) {
            builder = builder.addHeader("Omise-Version", apiVersion);
        }

        return builder.build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(configure(config, chain.request()));
    }
}
