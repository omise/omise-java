package co.omise.requests;

import co.omise.Client;
import co.omise.models.OmiseObjectBase;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * This class is wrapped data type either Class or TypeReference.
 *
 * @param <T> the generic type for any Model that would need to be returned by the {@link Client} when this request is passed to it
 */
public class ResponseType<T extends OmiseObjectBase> {

    private Class<T> classType;
    private TypeReference<T> typeReference;

    private ResponseType() {
    }

    public ResponseType(Class<T> classType) {
        this.classType = classType;
    }

    public ResponseType(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    public boolean isClassType() {
        return classType != null;
    }

    public boolean isTypeReference() {
        return typeReference != null;
    }

    public Class<T> getClassType() {
        return this.classType;
    }

    public TypeReference<T> getTypeReference() {
        return this.typeReference;
    }
}
