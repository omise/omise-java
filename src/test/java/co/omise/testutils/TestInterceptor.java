package co.omise.testutils;

import co.omise.OmiseTest;
import okhttp3.*;

import java.io.IOException;

public class TestInterceptor implements Interceptor {
    private static final MediaType jsonMediaType = MediaType.parse("application/json");

    private final OmiseTest test;
    private Request lastRequest;
    private Response lastResponse;

    public TestInterceptor(OmiseTest test) {
        this.test = test;
    }

    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        HttpUrl url = request.url();
        lastRequest = request;

        int code = 200;
        byte[] responseData = fixtureResourceBytes(request.method(), url.host(), url.encodedPath());
        if (responseData == null) {
            code = 404;
            responseData = fixtureNotFoundBytes(request.method(), url.host(), url.encodedPath());
        }

        Response.Builder builder = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(code);

        if (responseData != null) {
            builder = builder.body(ResponseBody.create(jsonMediaType, responseData));
        }

        return lastResponse = builder.build();
    }

    public Request lastRequest() {
        return lastRequest;
    }

    public Response lastResponse() {
        return lastResponse;
    }

    private byte[] fixtureResourceBytes(String method, String host, String path) {
        try {
            return test.getResourceBytes(fixtureResourcePath(method, host, path));
        } catch (IOException e) {
            return null;
        }
    }

    private byte[] fixtureNotFoundBytes(String method, String host, String path) {
        try {
            return test.getResourceBytes(fixtureNotFoundPath(method, host, path));
        } catch (IOException e1) {
            return null;
        }
    }

    private String fixtureResourcePath(String method, String host, String path) {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        return "/testdata/fixtures/" + host + path + "-" + method.toLowerCase() + ".json";
    }

    private String fixtureNotFoundPath(String method, String host, String path) {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        return fixtureResourcePath(method, host, path)
                .substring(path.lastIndexOf('/')) +
                ("/404-" + method.toLowerCase() + ".json");
    }
}
