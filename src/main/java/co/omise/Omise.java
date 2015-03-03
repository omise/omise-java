package main.java.co.omise;

import main.java.co.omise.exeption.OmiseKeyUnsetException;

public class Omise {
	public static final String OMISE_API_VERSION = "2014-07-27";
	public static final String OMISE_JAVA_LIB_VERSION = "1.0.0";
	
	private static volatile String publicKey = null;
	private static volatile String secretKey = null;
	
	/**
	 * 秘密鍵と公開鍵を設定する。必要のない鍵には{@code null}を指定してもよい。
	 * @param publicKey
	 * @param secretKey
	 */
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
	
	public static String getPublicKey() throws OmiseKeyUnsetException {
		if(publicKey == null) throw new OmiseKeyUnsetException("secret key is required.", null);
		
		return publicKey;
	}
	public static String getSecretKey() throws OmiseKeyUnsetException {
		if(secretKey == null) throw new OmiseKeyUnsetException("secret key is required.", null);
		
		return secretKey;
	}
}
