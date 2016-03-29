package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Token;
import org.joda.time.Period;
import org.joda.time.YearMonth;
import org.junit.Test;

import java.io.IOException;

public class TokenResourceTest extends ResourceTest {
    private static final String TOKEN_ID = "tokn_test_4yq8lbecl0q6dsjzxr5";

    @Test
    public void testGet() throws IOException {
        Token token = resource().get(TOKEN_ID);
        assertRequested("GET", "/tokens/" + TOKEN_ID, 200);
        assertVaultRequest();

        assertEquals(TOKEN_ID, token.getId());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", token.getCard().getId());
        assertEquals("sRF/oMw2UQJJp/WbU+2/ZbVzwROjpMf1lyhOHhOqziw=", token.getCard().getFingerprint());
    }

    @Test
    public void testCreate() throws IOException {
        Token token = resource().create(new Token.Create()
                .name("JOHN DOE")
                .number("4242424242424242")
                .expiration(YearMonth.now().withPeriodAdded(Period.years(1), 1))
                .securityCode("123")
                .city("Bangkok")
                .postalCode("10240"));
        assertRequested("POST", "/tokens", 200);
        assertVaultRequest();

        assertEquals(TOKEN_ID, token.getId());
        assertFalse(token.isLiveMode());
        assertEquals("card_test_4yq6tuucl9h4erukfl0", token.getCard().getId());
    }

    private void assertVaultRequest() {
        assertEquals(Endpoint.VAULT.host(), lastRequest().url().host());
    }

    private TokenResource resource() {
        return new TokenResource(testClient());
    }
}