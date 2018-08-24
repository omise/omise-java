package co.omise;

import co.omise.models.OmiseObjectBase;
import co.omise.models.Params;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SerializerTest extends OmiseTest {
    private static final String DUMMY_JSON = "{\"object\":\"dummy\",\"location\":\"/404\",\"hello\":\"world\"}";
    private static final String DUMMY_PARAMS_JSON = "{\"hello\":\"world\",\"what\":\"field\"}";

    @Test
    public void testSharedInstance() {
        assertNotNull(Serializer.defaultSerializer());
        assertSame(Serializer.defaultSerializer(), Serializer.defaultSerializer());
    }

    @Test
    public void testSerialize() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        serializer().serialize(outputStream, new Dummy());

        byte[] bytes = outputStream.toByteArray();
        assertEquals(DUMMY_JSON, new String(bytes, 0, bytes.length));
    }

    @Test
    public void testSerializeParams() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        serializer().serializeParams(outputStream, new DummyParams());

        byte[] bytes = outputStream.toByteArray();
        assertEquals(DUMMY_PARAMS_JSON, new String(bytes, 0, bytes.length));
    }

    @Test
    public void testDeserialize() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(DUMMY_JSON.getBytes());
        Dummy dummy = serializer().deserialize(inputStream, Dummy.class);

        assertNotNull(dummy);
        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());
    }

    @Test
    public void testDeserializeTypeRef() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(DUMMY_JSON.getBytes());
        Dummy dummy = serializer().deserialize(inputStream, new TypeReference<Dummy>() {
        });

        assertNotNull(dummy);
        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());
    }

    @Test
    public void testSerializeMap() {
        Map<String, Object> map = serializer().serializeToMap(new Dummy());

        assertNotNull(map);
        assertEquals("/404", map.get("location"));
        assertEquals("dummy", map.get("object"));
        assertEquals("world", map.get("hello"));
    }

    @Test
    public void testDeserializeMap() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("location", "/404");
        map.put("object", "dummy");
        map.put("hello", "world");

        Dummy dummy = serializer().deserializeFromMap(map, Dummy.class);
        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());
    }

    @Test
    public void testDeserializeMapTypeRef() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("location", "/404");
        map.put("object", "dummy");
        map.put("hello", "world");

        Dummy dummy = serializer().deserializeFromMap(map, new TypeReference<Dummy>() {
        });

        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());

    }


    private Serializer serializer() {
        return Serializer.defaultSerializer();
    }

    public static final class Dummy extends OmiseObjectBase {
        private String hello;

        public Dummy() {
            setLocation("/404");
            setObject("dummy");
            setHello("world");
        }

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }
    }

    public static final class DummyParams extends Params {
        @JsonProperty
        private String hello;
        @JsonProperty("what")
        private String another;

        public DummyParams() {
            hello = "world";
            another = "field";
        }
    }

}
