package co.omise.models;

import com.google.common.collect.ImmutableMap;
import okhttp3.RequestBody;

public abstract class Params {
    public abstract ImmutableMap<String, String> query();

    public abstract RequestBody body();
}
