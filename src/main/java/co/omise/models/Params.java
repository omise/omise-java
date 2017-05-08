package co.omise.models;

import co.omise.Serializer;
import com.google.common.collect.ImmutableMap;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Params class encapsulate request parameterization and provides a way to builds a parameter builder
 * for resource operations. This class is meant to be subclassed and serialized to JSON to send along
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
 * For an example, see the {@link ScopedList.Options} or the {@link Charge.Create} class
 * </p>
 *
 * @see ScopedList.Options
 * @see Charge.Create
 */
public abstract class Params {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * Returns a map of query string key values to add to an HTTP operation's URL.
     *
     * @param serializer The {@link Serializer} to use to serialize parameter data.
     *                   Or `null` to use the default serializer.
     * @return An {@link ImmutableMap} containing keys and values to adds to the URL.
     */
    public ImmutableMap<String, String> query(Serializer serializer) {
        return ImmutableMap.of();
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
