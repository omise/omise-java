package co.omise;

import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class OmiseTest extends Assert {
    public byte[] getResourceBytes(String path) throws IOException {
        InputStream stream = getClass().getResourceAsStream(path);
        return stream == null ? null : InputStreamToByteArray(stream);
    }

    private static byte[] InputStreamToByteArray(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }
}