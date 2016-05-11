package co.omise.models;

import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Params class encapsulate request parameterization and provides a way to builds a parameter builder
 * for resource operations. This class is meant to be subclassed and used in one the following ways:
 * <ul>
 * <li>Wrap the {@link #add(String, String)} in a builder method to appends key/value pair to the {@link FormBody} request body.</li>
 * <li>Overrides the {@link #query()} method to add query string parameters.</li>
 * <li>Overrides the {@link #body()} method to returns a custom OkHttp {@link RequestBody}.</li>
 * </ul>
 * <p>
 * For an example, see the {@link ScopedList.Options} or the {@link Charge.Create} class
 * </p>
 *
 * @see ScopedList.Options
 * @see Charge.Create
 */
public abstract class Params {
    private FormBody.Builder formBuilder = null;

    /**
     * Returns the query string to add to an HTTP operation's URL.
     *
     * @return An {@link ImmutableMap} containing keys and values to adds to the URL.
     */
    public ImmutableMap<String, String> query() {
        return ImmutableMap.of();
    }

    /**
     * Returns the {@link RequestBody} to send with the HTTP request. The default implementation automatically
     * builds and returns a {@link FormBody} from values added with the {@link #add(String, String)} method.
     *
     * @return An {@link RequestBody} to send with the HTTP request.
     */
    public RequestBody body() {
        return formBuilder == null ? null : formBuilder.build();
    }

    /**
     * Adds a key-value pair for building a {@link FormBody}.
     *
     * @param name  The name of the form field.
     * @param value The value of the form field.
     */
    protected void add(String name, String value) {
        if (formBuilder == null) {
            formBuilder = new FormBody.Builder();
        }

        // TODO: test
        if (value != null) {
            formBuilder = formBuilder.add(name, value);
        }
    }
}
