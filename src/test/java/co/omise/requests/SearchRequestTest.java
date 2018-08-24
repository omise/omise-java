package co.omise.requests;

import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.SearchResult;
import co.omise.models.SearchScope;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchRequestTest extends RequestTest {
    
    private static final String CHARGE_ID = "chrg_test_558lhfxykl6sogd1wfv";

    @Test
    public void testSearch() throws IOException, OmiseException {
        Request<SearchResult<Charge>> request = new SearchResult.SearchRequestBuilder<Charge>(
                new SearchResult.Options()
                        .scope(SearchScope.Charge)
                        .filter("amount", "4096.69")
                        .query(CHARGE_ID))
                .build();
        SearchResult<Charge> result = getTestRequester().sendRequest(request);

        Map<String, String> expects = new HashMap<>();
        expects.put("scope", "charge");
        expects.put("filters[amount]", "4096.69");
        expects.put("query", CHARGE_ID);

        assertRequested("GET", "/search", 200);

        okhttp3.Request lastRequest = lastRequest();
        for (Map.Entry<String, String> entry : expects.entrySet()) {
            assertEquals(entry.getValue(), lastRequest.url().queryParameter(entry.getKey()));
        }

        assertEquals(1, result.getTotal());
        assertEquals(1, result.getTotalPages());

        Charge charge = result.getData().get(0);
        assertEquals("charge", charge.getObject());
        assertEquals(CHARGE_ID, charge.getId());
        assertEquals(409669, charge.getAmount());
    }

}
