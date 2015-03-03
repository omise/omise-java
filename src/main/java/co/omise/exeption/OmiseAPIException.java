package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

/**
 * An exception class for handling error returned from the API.
 * Please see https://docs.omise.co/api/errors/ for more information.
 */
@SuppressWarnings("serial")
public class OmiseAPIException extends OmiseException {
	public OmiseAPIException(String message, OmiseError omiseError) {
		super(message, omiseError);
	}
}
