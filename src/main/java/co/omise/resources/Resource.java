package co.omise.resources;

import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.models.Model;
import co.omise.models.Params;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public abstract class Resource {
    private final OkHttpClient httpClient;

    protected Resource(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected <T extends Model, TParams extends Params> T request(String method, Endpoint endpoint, String path, TParams params, Class<T> klass) throws IOException {
        // TODO: If method == GET add params from params to httpurl
        HttpUrl url = new HttpUrl.Builder()
                .host(endpoint.host())
                .addPathSegments(path)
                .build();

        Request request = new Request.Builder()
                .method(method, params.build())
                .url(url)
                .build();

        Response response = httpClient.newCall(request).execute();
        if (200 <= response.code() && response.code() < 300) {
            return Serializer.defaultSerializer()
                    .deserialize(response.body().byteStream(), klass);

        } else { // error
            // TODO: Error model.
            return null;

        }
    }
}
