package co.omise.requests;

import co.omise.models.Capability;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class CapabilityRequestTest extends RequestTest {

    @Test
    public void testGet() throws IOException, OmiseException {
        Requester requester = getTestRequester();
        Request<Capability> request = new Capability.GetRequestBuilder().build();
        Capability capability = requester.sendRequest(request);

        assertRequested("GET", "/capability", 200);

        assertEquals("capability", capability.getObject());
        assertEquals("/capability", capability.getLocation());
        assertTrue(capability.getBanks().size() > 0);
        assertTrue(capability.getPaymentMethods().size() > 0);
        assertFalse(capability.isZeroInterestInstallments());
    }
}
