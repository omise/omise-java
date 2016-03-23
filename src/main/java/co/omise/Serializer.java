package co.omise;

import co.omise.models.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Serializer {
    private static Serializer sharedInstance;

    public static Serializer sharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Serializer();
        }

        return sharedInstance;
    }


    private final ObjectMapper objectMapper;

    private Serializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.registerModule(new GuavaModule());
    }

    public <T extends Model> T deserialize(InputStream input, Class<T> klass) throws IOException {
        return objectMapper.readValue(input, klass);
    }

    public <T extends Model> void serialize(OutputStream output, T model) throws IOException {
        objectMapper.writeValue(output, model);
    }
}
