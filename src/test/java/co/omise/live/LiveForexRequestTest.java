package co.omise.live;

import co.omise.models.Forex;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiveForexRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void TestGetForex() throws Exception {
        Request<Forex> request = new Forex.GetRequestBuilder("usd")
                .build();

        Forex forex = getLiveClient().sendRequest(request);

        assertNotNull(forex);
        assertEquals("usd", forex.getFrom());
        assertEquals("thb", forex.getTo());
        assertTrue(forex.getRate() > 0);
    }
}