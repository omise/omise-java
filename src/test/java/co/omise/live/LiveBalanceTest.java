package co.omise.live;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Balance;
import co.omise.models.OmiseException;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class LiveBalanceTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void getBalance_Success() throws ClientException, IOException, OmiseException {
        Client client = new Client(getPublicKey(), getSecretKey());

        Request<Balance> getBalanceRequest = new Balance.GetRequestBuilder().build();
        Balance actualBalance = client.sendRequest(getBalanceRequest, Balance.class);

        System.out.println("Balance retrieved: " + Long.toString(actualBalance.getTotal()));

        assertTrue(actualBalance.getTotal() > 100000);
    }

}
