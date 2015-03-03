package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

/**
 * An exception that will get thrown when either secret and public key is unset in main.java.co.omise.Omise.
 */
@SuppressWarnings("serial")
public class OmiseKeyUnsetException extends OmiseException {
	public OmiseKeyUnsetException(String message, OmiseError omiseError) {
		super(message, omiseError);
	}

	/**
	 *
	 * @return {@code null}
	 */
	@Override
	public OmiseError getOmiseError() {
		return null;
	}
}
