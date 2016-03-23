package co.omise.models;

import co.omise.OmiseTest;
import org.joda.time.YearMonth;
import org.junit.Test;

public class CardTest extends OmiseTest {
    @Test
    public void testSetExpirationYear() {
        YearMonth now = YearMonth.now();

        Card card = new Card();
        card.setExpiration(new YearMonth(2088, 8));
        card.setExpirationYear(2099);
        assertEquals(2099, card.getExpirationYear());
        assertEquals(2099, card.getExpiration().getYear());

        card.setExpiration(null);
        card.setExpirationYear(2099);
        assertEquals(2099, card.getExpirationYear());
        assertEquals(2099, card.getExpiration().getYear());
        assertEquals(now.getMonthOfYear(), card.getExpirationMonth());
    }

    @Test
    public void testSetExpirationMonth() {
        YearMonth now = YearMonth.now();

        Card card = new Card();
        card.setExpiration(new YearMonth(2088, 8));
        card.setExpirationMonth(9);
        assertEquals(9, card.getExpirationMonth());
        assertEquals(9, card.getExpiration().getMonthOfYear());

        card.setExpiration(null);
        card.setExpirationMonth(9);
        assertEquals(9, card.getExpirationMonth());
        assertEquals(9, card.getExpiration().getMonthOfYear());
        assertEquals(now.getYear(), card.getExpirationYear());
    }
}
