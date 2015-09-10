package co.omise.exception;

import co.omise.model.OmiseError;

/**
 * Created by tin_hanc on 9/8/2015 AD.
 */

@SuppressWarnings("serial")
public class OmiseAPIConnectionException extends OmiseException {
    public OmiseAPIConnectionException(String message, OmiseError omiseError) {
        super(message, omiseError);
    }
}
