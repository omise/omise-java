package co.omise.requests;

import co.omise.models.Bank;
import co.omise.models.Capability;
import co.omise.models.OmiseException;
import co.omise.models.PaymentMethod;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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

        List<PaymentMethod> paymentMethods = capability.getPaymentMethods();

        for (PaymentMethod paymentMethod : paymentMethods) {
            assertEquals("payment_method", paymentMethod.getObject());
            assertTrue(paymentMethod.getCurrencies().size() > 0);

            if (paymentMethod.getName().equals("fpx")) {
                assertTrue(paymentMethod.getBanks().size() > 0);

                Bank bank = paymentMethod.getBanks().get(0);
                assertEquals("citi", bank.getCode());
                assertEquals("Citibank", bank.getName());
                assertFalse(bank.isActive());
            }
        }
    }
}
