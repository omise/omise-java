package co.omise;

import org.junit.Test;

public class ConfigTest extends OmiseTest {
    static final String JAVA_VERSION = System.getProperty("java.version");
    static final String PKG_VERSION = Client.class.getPackage().getImplementationVersion();

    static final String API_VERSION = "new-shiny-version";
    static final String PKEY = "pkey_test_123";
    static final String SKEY = "skey_test_123";

    @Test
    public void testCtor() {
        Config config = config();
        assertEquals(API_VERSION, config.apiVersion());
        assertEquals(PKEY, config.publicKey());
        assertEquals(SKEY, config.secretKey());
    }

    @Test
    public void testUserAgent() {
        StringBuilder builder = new StringBuilder();
        builder.append("OmiseJava/");
        builder.append(PKG_VERSION);
        builder.append(" OmiseAPI/");
        builder.append(API_VERSION);
        builder.append(" Java/");
        builder.append(JAVA_VERSION);

        assertEquals(builder.toString(), config().userAgent());
    }

    static Config config() {
        return new Config(API_VERSION, PKEY, SKEY);
    }
}