package co.omise.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlatformTypeTest {

    @Test
    public void checkStringValue() {
        assertEquals("WEB", PlatformType.Web.toString());
        assertEquals("IOS", PlatformType.iOS.toString());
        assertEquals("ANDROID", PlatformType.Android.toString());
    }
}
