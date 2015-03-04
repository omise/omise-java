package co.omise.exception;

import co.omise.model.OmiseError;

/**
 * An exception that will get thrown when an unexpected error occurred.
 * Mainly when a non-error and non-response is returned from the API.
 * Please refer to stacktrace for more information.
 */
@SuppressWarnings("serial")
public class OmiseUnknownException extends OmiseException {
	public OmiseUnknownException(String message, OmiseError omiseError) {
		super(message, omiseError);
	}
}
