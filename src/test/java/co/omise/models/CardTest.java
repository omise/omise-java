package co.omise.models;

import co.omise.OmiseTest;
import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.junit.Test;

public class CardTest extends OmiseTest {
    @Test
    public void testGetExpiration() {
        Card card = new Card(
                "card",
                "omise",
                "id",
                false,
                DateTime.now(),
                false,
                "th",
                "Bangkok",
                "bank",
                "10310",
                "",
                "1234",
                "brand",
                11,
                2099,
                "mKleiBfwp+PoJWB/ipngANuECUmRKjyxROwFW5IO7TM=",
                "John Doe",
                true
        );

        YearMonth expiration = card.getExpiration();
        assertEquals(2099, expiration.getYear());
        assertEquals(11, expiration.getMonthOfYear());
    }
}
