package co.omise;

import co.omise.models.OmiseObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public final class Serializer {
    private static Serializer defaultInstance;

    public static Serializer defaultSerializer() {
        if (defaultInstance == null) {
            defaultInstance = new Serializer();
        }

        return defaultInstance;
    }


    private final ObjectMapper objectMapper;

    private Serializer() {
        objectMapper = new ObjectMapper();
        JodaModule joda = new JodaModule();
        joda.addSerializer(DateTime.class, new DateTimeSerializer()
                .withFormat(new JacksonJodaDateFormat(ISODateTimeFormat.dateTimeNoMillis())));

        objectMapper.registerModule(new GuavaModule());
        objectMapper.registerModule(joda);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    public <T extends OmiseObject> T deserialize(InputStream input, Class<T> klass) throws IOException {
        return objectMapper.readerFor(klass).readValue(input);
    }

    public <T extends OmiseObject> T deserializeFromMap(Map<String, Object> map, Class<T> klass) {
        return objectMapper.convertValue(map, klass);
    }

    public <T extends OmiseObject> void serialize(OutputStream output, T model) throws IOException {
        objectMapper.writerFor(model.getClass()).writeValue(output, model);
    }

    public <T extends OmiseObject> Map<String, Object> serializeToMap(T model) {
        return objectMapper.convertValue(model, new TypeReference<Map<String, Object>>() {
        });
    }
}
