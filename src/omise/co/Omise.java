package omise.co;

import omise.co.exeption.OmiseException;

public class Omise {
	public static final String OMISE_API_VERSION = "2014-07-27";
	public static final String OMISE_JAVA_LIB_VERSION = "1.0.0";
	
	private static volatile String publicKey = null;
	private static volatile String secretKey = null;
	
	public static void setKeys(String publicKey, String secretKey) {
		setPublicKey(publicKey);
		setSecretKey(secretKey);
	}
	
	public static void setPublicKey(String publicKey) {
		Omise.publicKey = publicKey;
	}
	public static void setSecretKey(String secretKey) {
		Omise.secretKey = secretKey;
	}
	
	public static String getPublicKey() throws OmiseException {
		if(publicKey == null) throw new OmiseException("secret key is required.");
		
		return publicKey;
	}
	public static String getSecretKey() throws OmiseException {
		if(secretKey == null) throw new OmiseException("secret key is required.");
		
		return secretKey;
	}
}
