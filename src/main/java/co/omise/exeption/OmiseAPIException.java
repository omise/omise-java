package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

@SuppressWarnings("serial")
public class OmiseAPIException extends OmiseException {
	private OmiseError omiseError;
	
	public OmiseAPIException(String message, OmiseError omiseError) {
		super(message);
		this.omiseError = omiseError;
	}
	
	public OmiseError getOmiseError() {
		return omiseError;
	}
}
