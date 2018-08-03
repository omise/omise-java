package co.omise.requests;

import com.fasterxml.jackson.core.type.TypeReference;

public class ResponseType<T> {

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
