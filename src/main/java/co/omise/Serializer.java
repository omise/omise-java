package co.omise;

import co.omise.models.OmiseList;
import co.omise.models.OmiseObject;
import co.omise.models.Params;
import co.omise.requests.RequestBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Serializer wraps Jackson's {@link ObjectMapper} and provides a
 * one-stop shop for handling Omise API models serialization.
 * <p>
 * Use the {@link #defaultSerializer()} method to obtain an instance.
 * </p>
 *
 * @see ObjectMapper
 */
public final class Serializer {
    private static Serializer defaultInstance;

    /**
     * The default Serializer instance.
     *
     * @return The default {@link Serializer} instance.
     */
    public static Serializer defaultSerializer() {
        if (defaultInstance == null) {
            defaultInstance = new Serializer();
        }

        return defaultInstance;
    }


    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateTimeFormatter;
    private final DateTimeFormatter localDateFormatter;

    private Serializer() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss['Z']");
        localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule()
                        .addSerializer(LocalDate.class, new LocalDateSerializer(localDateFormatter)))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * Returns the pre-configured {@link ObjectMapper} used for
     * serializing and deserializing Omise API objects.
     *
     * @return An {@link ObjectMapper} instance.
     */
    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    /**
     * Returns the pre-configured {@link DateTimeFormatter} used for
     * serializing and deserializing date and times for Omise API objects.
     *
     * @return A {@link DateTimeFormatter} instance.
     */
    public DateTimeFormatter dateTimeFormatter() {
        return dateTimeFormatter;
    }

    /**
     * Returns the pre-configured {@link DateTimeFormatter} used for
     * serializing and deserializing date for Omise API objects.
     *
     * @return A {@link DateTimeFormatter} instance.
     */
    public DateTimeFormatter localDateFormatter() {
        return localDateFormatter;
    }

    /**
     * Deserialize an instance of the given class from the input stream.
     *
     * @param input The {@link InputStream} that contains the data to deserialize.
     * @param klass The {@link Class} to deserialize the result into.
     * @param <T>   The type to deserialize the result into.
     * @return An instance of type T deserialized from the input stream.
     * @throws IOException on general I/O error.
     */
    public <T extends OmiseObject> T deserialize(InputStream input, Class<T> klass) throws IOException {
        return objectMapper.readerFor(klass).readValue(input);
    }

    /**
     * Deserialize an instance of the given type reference from the input stream.
     *
     * @param input The {@link InputStream} that contains the data to deserialize.
     * @param ref   The {@link TypeReference} of the type to deserialize the result into.
     * @param <T>   The type to deserialize the result into.
     * @return An instance of the given type T deserialized from the input stream.
     * @throws IOException on general I/O error.
     */
    public <T extends OmiseObject> T deserialize(InputStream input, TypeReference<T> ref) throws IOException {
        return objectMapper.readerFor(ref).readValue(input);
    }

    /**
     * Deserialize an instance of the given class from the map.
     *
     * @param map   The {@link Map} containing the JSON structure to deserialize from.
     * @param klass The {@link Class} to deserialize the result into.
     * @param <T>   The type to deserialize the result into.
     * @return An instance of type T deserialized from the map.
     */
    public <T extends OmiseObject> T deserializeFromMap(Map<String, Object> map, Class<T> klass) {
        return objectMapper.convertValue(map, klass);
    }

    /**
     * Deserialize an instance of the given type reference from the map.
     *
     * @param map The {@link Map} containing the JSON structure to deserialize from.
     * @param ref The {@link TypeReference} of the type to deserialize the result into.
     * @param <T> The type to deserialize the result into.
     * @return An instance of the given type T deserialized from the map.
     */
    public <T extends OmiseObject> T deserializeFromMap(Map<String, Object> map, TypeReference<T> ref) {
        return objectMapper.convertValue(map, ref);
    }

    /**
     * Serializes the given model to the output stream.
     *
     * @param output The {@link OutputStream} to serializes the model into.
     * @param model  The {@link OmiseObject} to serialize.
     * @param <T>    The type of the model to serialize.
     * @throws IOException on general I/O error.
     */
    public <T extends OmiseObject> void serialize(OutputStream output, T model) throws IOException {
        objectMapper.writerFor(model.getClass()).writeValue(output, model);
    }

    /**
     * Serializes the given parameter object to the output stream.
     *
     * @param output The {@link OutputStream} to serialize the parameter into.
     * @param param  The {@link Params} to serialize.
     * @param <T>    The type of the parameter object to serialize.
     * @throws IOException on general I/O error.
     */
    public <T extends Params> void serializeParams(OutputStream output, T param) throws IOException {
        // TODO: Add params-specific options.
        objectMapper.writerFor(param.getClass()).writeValue(output, param);
    }

    /**
     * Serialize the given model to a map with JSON-like structure.
     *
     * @param model The {@link OmiseObject} to serialize.
     * @param <T>   The type of the model to serialize.
     * @return The map containing the model's data.
     */
    public <T extends OmiseObject> Map<String, Object> serializeToMap(T model) {
        return objectMapper.convertValue(model, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * Serialize the given model to a representation suitable for using as URL query parameters.
     *
     * @param value The value to serialize
     * @param <T>   The type of the value to serialize.
     * @return The string value for using as query parameters.
     */
    public <T extends Enum<T>> String serializeToQueryParams(T value) {
        return objectMapper.convertValue(value, String.class);
    }

    /**
     * Deserialize an instance of the given type reference from the input stream, used for deserializing lists.
     *
     * @param input The {@link InputStream} that contains the data to deserialize.
     * @param ref   The {@link TypeReference} of the type to deserialize the result into.
     * @param <T>   The type to deserialize the result into.
     * @return An instance of the given type T deserialized from the input stream.
     * @throws IOException on general I/O error.
     */
    public <T extends OmiseList> T deserializeList(InputStream input, TypeReference<T> ref) throws IOException {
        return objectMapper.readerFor(ref).readValue(input);
    }

    /**
     * Serializes the given {@link RequestBuilder} object to the provided output stream.
     *
     * @param outputStream The {@link OutputStream} to serialize the parameter into.
     * @param builder      The {@link RequestBuilder} to serialize.
     * @param <T>          The type of the parameter object to serialize.
     * @throws IOException on general I/O error.
     */
    public <T extends RequestBuilder> void serializeRequestBuilder(OutputStream outputStream, T builder) throws IOException {
        objectMapper.writerFor(builder.getClass()).writeValue(outputStream, builder);
    }
}