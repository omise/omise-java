package co.omise.requests;

import co.omise.Client;
import co.omise.models.Model;
import co.omise.models.OmiseException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

/**
 * Requester is an interface to be used for implementation of sending HTTP requests for {@link Client}. It is used to abstract away the actual HTTP part of the client and also allow it to be easily testable.
 */
public interface Requester {
    /**
     * Sends the user generated {@link Request<T>} to {@link Requester} for it to be carried out
     *
     * @param <T>     the {@link Model} object type that is expected to be returned
     * @param <R>     the {@link Request} object type that is passed in from the user
     * @param request the {@link Request<T>} user generated request
     * @param klass   the type of the object that the response is expected to be deserialized as
     * @return the {@link Model} object of type <T>
     * @throws IOException    the general I/O error that could happen during deserialization
     * @throws OmiseException the custom exception thrown for response errors
     */
    <T extends Model, R extends Request<T>> T sendRequest(R request, Class<T> klass) throws IOException, OmiseException;

    /**
     * Sends the user generated {@link Request<T>} to {@link Requester} for it to be carried out
     *
     * @param <T>           the {@link Model} object type that is expected to be returned
     * @param <R>           the {@link Request} object type that is passed in from the user
     * @param request       the {@link Request<T>} user generated request
     * @param typeReference the type of the object for the list that the response is expected to be deserialized as
     * @return the {@link Model} object of type <T>
     * @throws IOException    the general I/O error that could happen during deserialization
     * @throws OmiseException the custom exception thrown for response errors
     */
    <T extends Model, R extends Request<T>> T sendRequest(R request, TypeReference<T> typeReference) throws IOException, OmiseException;
}