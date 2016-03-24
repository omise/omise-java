package co.omise;

import co.omise.models.Model;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class SerializerTest extends OmiseTest {
    private static final String DUMMY_JSON = "{\"object\":\"dummy\",\"id\":\"dummymodel\",\"location\":\"/404\",\"hello\":\"world\",\"livemode\":false,\"created\":null}";

    @Test
    public void testSharedInstance() {
        assertNotNull(Serializer.defaultSerializer());
        assertSame(Serializer.defaultSerializer(), Serializer.defaultSerializer());
    }

    @Test
    public void testSerialize() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        getSerializer().serialize(outputStream, new Dummy());

        byte[] bytes = outputStream.toByteArray();
        assertEquals(DUMMY_JSON, new String(bytes, 0, bytes.length));
    }

    @Test
    public void testDeserialize() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(DUMMY_JSON.getBytes());
        Dummy dummy = getSerializer().deserialize(inputStream, Dummy.class);

        assertNotNull(dummy);
        assertEquals("dummymodel", dummy.getId());
        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());
    }

    @Test
    public void testSerializeMap() {
        Map<String, Object> map = getSerializer().serializeToMap(new Dummy());

        assertNotNull(map);
        assertEquals("dummymodel", map.get("id"));
        assertEquals("/404", map.get("location"));
        assertEquals("dummy", map.get("object"));
        assertEquals("world", map.get("hello"));
    }

    @Test
    public void testDeserializeMap() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(4);
        map.put("id", "dummymodel");
        map.put("location", "/404");
        map.put("object", "dummy");
        map.put("hello", "world");

        Dummy dummy = getSerializer().deserializeFromMap(map, Dummy.class);
        assertEquals("dummymodel", dummy.getId());
        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());
    }


    private Serializer getSerializer() {
        return Serializer.defaultSerializer();
    }

    public static final class Dummy extends Model {
        private String hello;

        public Dummy() {
            setId("dummymodel");
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
}
