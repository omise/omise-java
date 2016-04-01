package co.omise;

import com.google.common.base.Preconditions;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

final class Configurer implements Interceptor {
    private final Config config;
    private final String userAgent;

    Configurer(Config config) {
        Preconditions.checkNotNull(config);
        this.config = config;

        String ua = "OmiseJava/" + Client.class.getPackage().getImplementationVersion();
        if (config.apiVersion() != null && !config.apiVersion().isEmpty()) {
            ua += " OmiseAPI/" + config.apiVersion();
        }

        ua += " Java/" + System.getProperty("java.version");
        this.userAgent = ua;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
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
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", Credentials.basic(key, "x"));

        if (apiVersion != null && !apiVersion.isEmpty()) {
            builder = builder.addHeader("Omise-Version", apiVersion);
        }

        return chain.proceed(builder.build());
    }
}
