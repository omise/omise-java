package co.omise.requests;

import co.omise.Endpoint;
import co.omise.models.Card;
import co.omise.models.OmiseException;
import co.omise.models.Token;

import java.time.YearMonth;
import org.junit.Test;

import java.io.IOException;

public class TokenRequestTest extends RequestTest {
    private static final String TOKEN_ID = "tokn_test_4yq8lbecl0q6dsjzxr5";

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Token> request = new Token.GetRequestBuilder(TOKEN_ID).build();
        Token token = getTestRequester().sendRequest(request);

        assertRequested("GET", "/tokens/" + TOKEN_ID, 200);
        assertVaultRequest();

        assertEquals(TOKEN_ID, token.getId());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", token.getCard().getId());
        assertEquals("sRF/oMw2UQJJp/WbU+2/ZbVzwROjpMf1lyhOHhOqziw=", token.getCard().getFingerprint());
    }

    @Test
    public void testCreate() throws IOException, OmiseException {
        Request<Token> request = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("JOHN DOE")
                        .number("4242424242424242")
                        .expiration(YearMonth.of(2026, 3))
                        .securityCode("123")
                        .city("Bangkok")
                        .postalCode("10240")
                        .email("email@omise.co")
                        )
                .build();

        Token token = getTestRequester().sendRequest(request);

        assertRequested("POST", "/tokens", 200);
        assertVaultRequest();
        assertRequestBody("{\"card\":{\"city\":\"Bangkok\",\"country\":null,\"name\":\"JOHN DOE\",\"number\":\"4242424242424242\",\"state\":null,\"street1\":null,\"street2\":null,\"email\":\"email@omise.co\",\"expiration_month\":3,\"expiration_year\":2026,\"phone_number\":null,\"postal_code\":\"10240\",\"security_code\":\"123\"}}");

        assertEquals(TOKEN_ID, token.getId());
        assertFalse(token.isLiveMode());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", token.getCard().getId());
    }

    private void assertVaultRequest() {
        assertEquals(Endpoint.VAULT.host(), lastRequest().url().host());
    }
}