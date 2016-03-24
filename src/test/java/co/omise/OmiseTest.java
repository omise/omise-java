package co.omise;

import com.google.common.io.ByteStreams;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;

public abstract class OmiseTest extends TestCase {
    protected byte[] getResourceBytes(String path) throws IOException {
        InputStream stream = getClass().getResourceAsStream(path);
        return ByteStreams.toByteArray(stream);
    }
}
