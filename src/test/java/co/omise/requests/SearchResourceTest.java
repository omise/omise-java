package co.omise.requests;

import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.SearchResult;
import co.omise.models.SearchScope;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import okhttp3.Request;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class SearchResourceTest extends RequestTest {
    public static final String CHARGE_ID = "chrg_test_558lhfxykl6sogd1wfv";

    @Test
    public void testSearch() throws IOException, OmiseException {
        SearchResult.Options options = new SearchResult.Options()
                .scope(SearchScope.Charge)
                .filter("amount", "4096.69")
                .query(CHARGE_ID);
        co.omise.requests.Request<SearchResult<Charge>> request = new SearchResult.ListRequestBuilder<Charge>(options).build();
        SearchResult<Charge> result = getTestRequester().sendRequest(request, new TypeReference<SearchResult<Charge>>() {
        });

        Map<String, String> expects = ImmutableMap.of(
                "scope", "charge",
                "filters[amount]", "4096.69",
                "query", CHARGE_ID);

        assertRequested("GET", "/search", 200);

        Request lastRequest = lastRequest();
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
