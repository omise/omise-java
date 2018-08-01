package co.omise.requests;

import co.omise.models.Link;
import co.omise.models.OmiseException;
import org.junit.Test;

import java.io.IOException;

public class LinkRequestTest extends RequestTest {
    private static final String LINK_ID = "link_test_5csdepgdzorb7de56ff";

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Link> request =
                new Link.CreateRequestBuilder()
                        .amount(100000)
                        .currency("thb")
                        .title("Omise Sale")
                        .description("Medium size T-Shirt (Blue)")
                        .multiple(true)
                        .build();

        Link link = getTestRequester().sendRequest(request, Link.class);

        assertRequested("POST", "/links", 200);
        assertEquals(LINK_ID, link.getId());
        assertTrue(link.isMultiple());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Link> request =
                new Link.GetRequestBuilder(LINK_ID)
                        .build();

        Link link = getTestRequester().sendRequest(request, Link.class);

        assertRequested("GET", "/links/" + LINK_ID, 200);
        assertEquals(LINK_ID, link.getId());
        assertTrue(link.isMultiple());
    }
}
