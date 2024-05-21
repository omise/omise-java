package co.omise.live;

import co.omise.Client;
import co.omise.models.Capability;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LiveCapabilityRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void getCapabilities_Success() throws Exception {
        Client client = getLiveClient();

        Request<Capability> request = new Capability.GetRequestBuilder().build();
        Capability capability = client.sendRequest(request);

        System.out.println("Capabilities include: " + capability.getBanks().toString());

        assertTrue(capability.getBanks().size() > 0);
        assertTrue(capability.getPaymentMethods().size() > 0);
        assertFalse(capability.isZeroInterestInstallments());
    }
}
