package co.omise;

import org.junit.Test;

public class ClientTest extends OmiseTest {
    @Test
    public void testClient() {
        Client client = new Client();
        assertEquals(1337, client.arg);
    }
}
