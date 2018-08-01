package co.omise.live;

import co.omise.Client;
import co.omise.models.Link;
import co.omise.requests.Request;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LiveLinkRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void createLinkSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Link> request =
                new Link.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .title("Omise Sale")
                        .description("Medium size T-Shirt (Blue)")
                        .multiple(true) // can be used for multiple payments
                        .build();

        Link link = client.sendRequest(request, Link.class);

        System.out.printf("Link created: %s", link.getId());

        assertEquals(100000, link.getAmount());
        assertEquals("thb", link.getCurrency());
        assertEquals("Omise Sale", link.getTitle());
        assertEquals("Medium size T-Shirt (Blue)", link.getDescription());
        assertTrue(link.isMultiple());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void getLinkSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Link> createRequest =
                new Link.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .title("Omise Sale")
                        .description("Medium size T-Shirt (Blue)")
                        .multiple(true) // can be used for multiple payments
                        .build();

        Link createdLink = client.sendRequest(createRequest, Link.class);

        System.out.printf("Link created: %s \n", createdLink.getId());

        Request<Link> getRequest =
                new Link.GetRequestBuilder(createdLink.getId())
                        .build();

        Link retrievedLink = client.sendRequest(getRequest, Link.class);

        System.out.printf("Link retrieved: %s", retrievedLink.getId());

        assertEquals(createdLink.getId(), retrievedLink.getId());
    }
}