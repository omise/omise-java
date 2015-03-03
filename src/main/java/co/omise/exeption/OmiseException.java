package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

@SuppressWarnings("serial")
public class OmiseException extends Exception {
	private OmiseError omiseError = null;

	public OmiseException(String message, OmiseError omiseError) {
		super(message);
		this.omiseError = omiseError;
	}
	
	public OmiseError getOmiseError() {
		return omiseError;
	}
}
