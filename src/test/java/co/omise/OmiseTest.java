package co.omise;

import com.google.common.io.ByteStreams;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;

public abstract class OmiseTest extends TestCase {
    public byte[] getResourceBytes(String path) throws IOException {
        InputStream stream = getClass().getResourceAsStream(path);
        return stream == null ? null : ByteStreams.toByteArray(stream);
    }
}
