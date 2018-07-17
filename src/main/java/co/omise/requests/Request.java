package co.omise.requests;

import co.omise.Client;
import co.omise.models.OmiseObjectBase;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

/**
 * Request is a base class, all extending classes would act as a holder class that encapsulate the information
 * regarding which of the Omise APIs  the user wants to access and pass any additional data needed for that api request
 *
 * @param <T> the generic type for any Model/ScopedList that would need to be returned by the {@link Client} when this request is passed to it
 */
public class Request<T extends OmiseObjectBase> {
    private final String method;
    private final HttpUrl path;
    private final String contentType;
    private final RequestBody payload;
    private final Class<T> classType;
    private final TypeReference<T> typeReference;

    /**
     * Constructor for a new Request
     *
     * @param method      The HTTP method to use
     * @param path        The {@link HttpUrl} for the url path of the HTTP request
     * @param contentType The content type of HTTP request
     * @param payload     Additional params passed as OkHttp {@link RequestBody} to the HTTP request
     */
    Request(String method, HttpUrl path, String contentType, RequestBody payload, Class<T> classType) {
        this.method = method;
        this.path = path;
        this.contentType = contentType;
        this.payload = payload;
        this.classType = classType;
        this.typeReference = null;
    }

    Request(String method, HttpUrl path, String contentType, RequestBody payload, TypeReference<T> typeReference) {
        this.method = method;
        this.path = path;
        this.contentType = contentType;
        this.payload = payload;
        this.typeReference = typeReference;
        this.classType = null;
    }

    /**
     * Gets the HTTP method.
     *
     * @return the HTTP method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gets url path for the specific API in the request.
     *
     * @return the url path as {@link HttpUrl}
     */
    public HttpUrl getPath() {
        return path;
    }

    /**
     * Gets HTTP request content type.
     *
     * @return the content type as a String
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Gets payload (request params).
     *
     * @return the payload as a {@link RequestBody}
     */
    public RequestBody getPayload() {
        return payload;
    }

    public Class<T> getClassType() {
        return classType;
    }

    public TypeReference<T> getTypeReference() {
        return typeReference;
    }
}