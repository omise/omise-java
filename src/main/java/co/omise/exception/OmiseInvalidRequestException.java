package co.omise.exception;

import co.omise.model.OmiseError;

/**
 * Created by tin_hanc on 9/8/2015 AD.
 */
public class OmiseInvalidRequestException extends OmiseException {
    public OmiseInvalidRequestException(String message, OmiseError omiseError) {
        super(message, omiseError);
    }
}

