package co.omise.models;

import co.omise.OmiseTest;
import co.omise.Serializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Objects;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class SerializationTest extends OmiseTest {
    private Map<String, Class> models = Maps.newHashMapWithExpectedSize(32);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        models.put("account", Account.class);
        models.put("balance", Balance.class);
        models.put("bank_account", BankAccount.class);
        models.put("card", Card.class);
        models.put("charge", Charge.class);
        models.put("customer", Customer.class);
        models.put("dispute", Dispute.class);
        // models.put("event", Event.class);
        models.put("recipient", Recipient.class);
        models.put("token", Token.class);
        models.put("transaction", Transaction.class);
        models.put("transfer", Transfer.class);
    }

    @Test
    public void testModelSerializability() throws IOException, IllegalAccessException, InstantiationException {
        Serializer serializer = Serializer.defaultSerializer();
        for (Map.Entry<String, Class> testcase : models.entrySet()) {
            byte[] sampleBytes = getResourceBytes(objectJsonName(testcase.getValue()));
            Model instance = serializer.deserialize(new ByteArrayInputStream(sampleBytes), testcase.getValue());

            Map<String, Object> map = serializer.serializeToMap(instance);
            Map<String, Object> comparison = serializer.objectMapper().readValue(sampleBytes, new TypeReference<Map<String, Object>>() {
            });

            assertMapEquals(testcase.getKey(), comparison, map);
        }
    }

    private void assertMapEquals(String message, Map<String, Object> expectedMap, Map<String, Object> actualMap) {
        Map<String, MapDifference.ValueDifference<Object>> differences = Maps.difference(expectedMap, actualMap).entriesDiffering();
        if (differences.size() == 0) {
            return; // all good : )
        }

        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : differences.entrySet()) {
            Object expected = entry.getValue().leftValue();
            Object actual = entry.getValue().rightValue();

            // nested maps
            if (actual instanceof Map && expected instanceof Map) {
                assertMapEquals(message + "." + entry.getKey(), (Map<String, Object>) expected, (Map<String, Object>) actual);
                continue;
            }

            // ruby server serializes `+00:00` instead of `Z` for default time zone, which is non-standard.
            if (actual instanceof String && expected instanceof String && ((String) expected).endsWith("+00:00")) {
                expected = ((String) expected).replace("+00:00", "Z");
            }

            // numbers less than Long.MAX_VALUE will be deserialized as Integer
            if (actual instanceof Long && expected instanceof Integer) {
                expected = (long) (Integer) expected;
            }

            assertEquals(message + "." + entry.getKey(), expected.getClass(), actual.getClass());
            assertEquals(message + "." + entry.getKey(), expected, actual);
        }
    }

    String objectJsonName(Class klass) {
        if (Objects.equal(klass, BankAccount.class)) {
            return "/testdata/objects/bank_account_object.json";
        } else {
            return "/testdata/objects/" + klass.getSimpleName().toLowerCase() + "_object.json";
        }
    }
}
