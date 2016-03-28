package co.omise.models;

import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public abstract class Params {
    private FormBody.Builder formBuilder = null;

    public ImmutableMap<String, String> query() {
        return ImmutableMap.of();
    }

    public RequestBody body() {
        return formBuilder == null ? null : formBuilder.build();
    }

    protected void add(String name, String value) {
        if (formBuilder == null) {
            formBuilder = new FormBody.Builder();
        }

        formBuilder = formBuilder.add(name, value);
    }
}
