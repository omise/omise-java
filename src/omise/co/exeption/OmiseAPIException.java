package omise.co.exeption;

import omise.co.model.OmiseError;

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
