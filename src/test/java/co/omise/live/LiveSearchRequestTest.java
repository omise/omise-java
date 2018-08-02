package co.omise.live;

import co.omise.Client;
import co.omise.models.*;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LiveSearchRequestTest extends BaseLiveTest {

    private static final String LIVETEST_CHARGE = "[YOUR_CHARGE]";
    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSearchGet() throws IOException, OmiseException {
        SearchResult.Options options = new SearchResult.Options()
                .scope(SearchScope.Charge);
        Request<SearchResult<Charge>> request = new SearchResult.SearchRequestBuilder<Charge>(options).build();

        SearchResult<Charge> result = client.sendRequest(request, new TypeReference<SearchResult<Charge>>() {});

        Charge charge = result.getData().get(0);
        assertNotNull(charge);
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSearchSpecificCharge() throws IOException, OmiseException {
        SearchResult.Options options = new SearchResult.Options()
                .scope(SearchScope.Charge)
                .query(LIVETEST_CHARGE);
        Request<SearchResult<Charge>> request = new SearchResult.SearchRequestBuilder<Charge>(options).build();

        SearchResult<Charge> result = client.sendRequest(request, new TypeReference<SearchResult<Charge>>() {});

        Charge charge = result.getData().get(0);
        assertEquals(1, result.getTotal());
        assertNotNull(charge);
        assertEquals(LIVETEST_CHARGE, charge.getId());
    }

}
