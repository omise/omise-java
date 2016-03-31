package co.omise;

import org.junit.Test;

public class ClientTest extends OmiseTest {
    @Test
    public void testCtor() {
        try {
            new Client(null);
            fail("exception expected");
        } catch (NullPointerException e) {
        }
    }
}
