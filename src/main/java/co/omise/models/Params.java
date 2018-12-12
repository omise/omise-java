package co.omise.models;

import co.omise.Serializer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Params class encapsulate request parameterization and provides a way to builds a parameter builder
 * for creating requests. This class is meant to be subclassed and serialized to JSON to send along
 * with any API request.
 * <p>
 * Subclass should:
 * </p>
 * <ul>
 * <li>Annotate any JSON properties with {@link com.fasterxml.jackson.annotation.JsonProperty}</li>
 * <li>Overrides the {@link #query(Serializer)} method to add query string parameters.</li>
 * <li>Overrides the {@link #body(Serializer)} method to returns a custom OkHttp {@link RequestBody}.</li>
 * </ul>
 * <p>
 * For an example, see the {@link ScopedList.Options}
 * </p>
 *
 * @see ScopedList.Options
 */
public abstract class Params {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * Returns a map of query string key values to add to an HTTP operation's URL.
     *
     * @param serializer The {@link Serializer} to use to serialize parameter data.
     *                   Or `null` to use the default serializer.
     * @return An {@link Map} containing keys and values to adds to the URL.
     */
    public Map<String, String> query(Serializer serializer) {
        return Collections.unmodifiableMap(new HashMap<String, String>());
    }

    /**
     * Returns the {@link RequestBody} to send with the HTTP request. The default implementation serializes
     * the instance into JSON format and builds an HTTP {@link RequestBody} containing the serialized data.
     *
     * @param serializer The {@link Serializer} to use to serialize parameter data.
     *                   Or `null` to use the default serializer.
     * @return An {@link RequestBody} to send with the HTTP request.
     * @throws IOException on serialization errors.
     */
    public RequestBody body(Serializer serializer) throws IOException {
        if (serializer == null) {
            serializer = Serializer.defaultSerializer();
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream(4096);
        serializer.serializeParams(stream, this);
        return RequestBody.create(JSON_MEDIA_TYPE, stream.toByteArray());
    }
}
