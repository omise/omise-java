package co.omise.models;

import co.omise.OmiseTest;
import org.joda.time.YearMonth;
import org.junit.Test;

public class CardTest extends OmiseTest {
    @Test
    public void testGetExpiration() {
        Card card = new Card();
        card.setExpirationMonth(11);
        card.setExpirationYear(2099);

        YearMonth expiration = card.getExpiration();
        assertEquals(2099, expiration.getYear());
        assertEquals(11, expiration.getMonthOfYear());
    }
}
