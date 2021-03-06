package co.omise.live;

import co.omise.Client;
import co.omise.models.Balance;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class LiveBalanceRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void getBalance_Success() throws Exception {
        Client client = getLiveClient();

        Request<Balance> getBalanceRequest = new Balance.GetRequestBuilder().build();
        Balance actualBalance = client.sendRequest(getBalanceRequest);

        System.out.println("Balance retrieved total: " + actualBalance.getTotal()
                + " , transferable: " + actualBalance.getTransferable());

        assertTrue(actualBalance.getTotal() > 100000);
        assertTrue(actualBalance.getTransferable() > 0);
    }
}
