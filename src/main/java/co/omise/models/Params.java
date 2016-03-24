package co.omise.models;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public abstract class Params {
    private FormBody.Builder builder = new FormBody.Builder();

    protected final void add(String key, String value) {
        builder = builder.add(key, value);
    }

    protected final void add(String key, int value) {
        builder = builder.add(key, Integer.toString(value));
    }

    public final RequestBody build() {
        return builder.build();
    }
}
