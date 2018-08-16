package co.omise.live;

import co.omise.Client;
import co.omise.models.Link;
import co.omise.models.Ordering;
import co.omise.models.ScopedList;
import co.omise.requests.Request;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LiveLinkRequestTest extends BaseLiveTest {

    @Test
    @Ignore("only hit when test on live.")
    public void testCreateLinkSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Link> request =
                new Link.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .title("Omise Sale")
                        .description("Medium size T-Shirt (Blue)")
                        .multiple(true) // can be used for multiple payments
                        .build();

        Link link = client.sendRequest(request);

        System.out.printf("Link created: %s", link.getId());

        assertEquals(100000, link.getAmount());
        assertEquals("thb", link.getCurrency());
        assertEquals("Omise Sale", link.getTitle());
        assertEquals("Medium size T-Shirt (Blue)", link.getDescription());
        assertTrue(link.isMultiple());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void testGetLinkSuccess() throws Exception {
        Client client = getLiveClient();

        Request<Link> createRequest =
                new Link.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .title("Omise Sale")
                        .description("Medium size T-Shirt (Blue)")
                        .multiple(true) // can be used for multiple payments
                        .build();

        Link createdLink = client.sendRequest(createRequest);

        System.out.printf("Link created: %s \n", createdLink.getId());

        Request<Link> getRequest =
                new Link.GetRequestBuilder(createdLink.getId())
                        .build();

        Link retrievedLink = client.sendRequest(getRequest);

        System.out.printf("Link retrieved: %s", retrievedLink.getId());

        assertEquals(createdLink.getId(), retrievedLink.getId());
    }

    @Test
    @Ignore("only hit when test on live.")
    public void testLinkListGet() throws Exception {
        Client client = getLiveClient();

        Request<ScopedList<Link>> request =
                new Link.ListRequestBuilder()
                        .options(new ScopedList.Options()
                                .limit(10)
                                .order(Ordering.Chronological))
                        .build();

        ScopedList<Link> links = client.sendRequest(request);

        assertEquals(10, links.getLimit());
        assertEquals(18, links.getTotal()); // This can easily break as you add charges, not sure how to do it better
        assertEquals(Ordering.Chronological, links.getOrder());

        Link link = links.getData().get(0);
        assertNotNull(link);
    }
}