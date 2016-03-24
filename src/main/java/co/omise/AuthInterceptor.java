package co.omise;

import com.google.common.base.Preconditions;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

class AuthInterceptor implements Interceptor {
    private final Config config;

    AuthInterceptor(Config config) {
        Preconditions.checkNotNull(config);
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String key = request.url().host().contains("vault.omise.co") ?
                config.getPublicKey() :
                config.getSecretKey();

        return chain.proceed(request
                .newBuilder()
                .addHeader("Authorization", Credentials.basic(key, "x"))
                .build()
        );
    }
}
