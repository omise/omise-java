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
    @Test
    public void testModelSerializability() throws IOException, IllegalAccessException, InstantiationException {
        Serializer serializer = Serializer.defaultSerializer();
        for (Map.Entry<String, Class> testcase : ModelTypeResolver.KNOWN_TYPES.entrySet()) {
            byte[] sampleBytes = getResourceBytes(objectJsonName(testcase.getValue()));
            OmiseObject instance = serializer.deserialize(new ByteArrayInputStream(sampleBytes), testcase.getValue());

            Map<String, Object> map = serializer.serializeToMap(instance);
            Map<String, Object> comparison = serializer.objectMapper().readValue(sampleBytes, new TypeReference<Map<String, Object>>() {
            });

            assertMapEquals(testcase.getKey(), comparison, map);
        }
    }

    private void assertMapEquals(String prefix, Map<String, Object> expectedMap, Map<String, Object> actualMap) {
        MapDifference<String, Object> differences = Maps.difference(expectedMap, actualMap);
        if (differences.entriesDiffering().size() == 0 && differences.entriesOnlyOnLeft().size() == 0) {
            return; // all good : )
        }

        for (Map.Entry<String, Object> entry : differences.entriesOnlyOnLeft().entrySet()) {
            if (entry.getKey().equals("deleted")) {
                continue; // "deleted" key are never serialized.
            }

            fail(prefix + "." + entry.getKey() + " is missing");
            return;
        }

        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : differences.entriesDiffering().entrySet()) {
            Object expected = entry.getValue().leftValue();
            Object actual = entry.getValue().rightValue();

            // nested maps
            if (expected instanceof Map) {
                assertTrue(actual instanceof Map);
                assertMapEquals(prefix + "." + entry.getKey(), (Map<String, Object>) expected, (Map<String, Object>) actual);
                continue;
            }

            // ruby server serializes `+00:00` instead of `Z` for default time zone, which is non-standard.
            if (expected instanceof String && ((String) expected).endsWith("+00:00")) {
                expected = ((String) expected).replace("+00:00", "Z");
            }

            // numbers less than Long.MAX_VALUE will be deserialized as Integer
            if (actual instanceof Long && expected instanceof Integer) {
                expected = (long) (Integer) expected;
            }

            if (expected == null) {
                assertNull(prefix + "." + entry.getKey(), actual);
            } else {
                assertNotNull(prefix + "." + entry.getKey(), actual);
            }

            assertEquals(prefix + "." + entry.getKey(), expected.getClass(), actual.getClass());
            assertEquals(prefix + "." + entry.getKey(), expected, actual);
        }
    }

    private String objectJsonName(Class klass) {
        if (Objects.equal(klass, BankAccount.class)) {
            return "/testdata/objects/bank_account_object.json";
        } else {
            return "/testdata/objects/" + klass.getSimpleName().toLowerCase() + "_object.json";
        }
    }
}
