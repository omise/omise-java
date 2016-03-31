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
        String key = request.url().host().contains(Endpoint.VAULT.host()) ?
                config.publicKey() :
                config.secretKey();

        return chain.proceed(request.newBuilder()
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", Credentials.basic(key, "x"))
                .build());
    }
}
