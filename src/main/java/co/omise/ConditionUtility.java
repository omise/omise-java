package co.omise;


/**
 * A utility class that encases methods for checking certain conditions, for example a object not being null
 */
public class ConditionUtility {

    /**
     * Ensures that an object passed in is not null.
     *
     * @param obj an object reference
     * @return the non-null value that was validated
     * @throws NullPointerException if value is null
     */
    public static <T> T notNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}