package co.omise.requests;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import org.junit.Test;

import java.io.IOException;

public class TokenRequestTest extends RequestTest {
    private static final String TOKEN_ID = "tokn_test_4yq8lbecl0q6dsjzxr5";

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Token> request = new Token.GetRequestBuilder(TOKEN_ID).build();
        Token token = getTestRequester().sendRequest(request, Token.class);

        assertRequested("GET", "/tokens/" + TOKEN_ID, 200);
        assertVaultRequest();

        assertEquals(TOKEN_ID, token.getId());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", token.getCard().getId());
        assertEquals("sRF/oMw2UQJJp/WbU+2/ZbVzwROjpMf1lyhOHhOqziw=", token.getCard().getFingerprint());
    }

    private void assertVaultRequest() {
        assertEquals(Endpoint.VAULT.host(), lastRequest().url().host());
    }
}