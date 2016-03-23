package co.omise;

import co.omise.models.Model;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializerTest extends OmiseTest {
    private static final String DUMMY_JSON = "{\"object\":\"dummy\",\"id\":\"dummymodel\",\"location\":\"/404\",\"deleted\":false,\"hello\":\"world\",\"livemode\":false,\"created_at\":null}";

    public void testSharedInstance() {
        assertNotNull(Serializer.sharedInstance());
        assertSame(Serializer.sharedInstance(), Serializer.sharedInstance());
    }

    @Test
    public void testSerialize() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Serializer.sharedInstance().serialize(outputStream, new Dummy());

        byte[] bytes = outputStream.toByteArray();
        assertEquals(DUMMY_JSON, new String(bytes, 0, bytes.length));
    }

    @Test
    public void testDeserialize() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(DUMMY_JSON.getBytes());
        Dummy dummy = Serializer.sharedInstance().deserialize(inputStream, Dummy.class);

        assertNotNull(dummy);
        assertEquals("dummymodel", dummy.getId());
        assertEquals("/404", dummy.getLocation());
        assertEquals("dummy", dummy.getObject());
        assertEquals("world", dummy.getHello());
    }

    public static class Dummy extends Model {
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
