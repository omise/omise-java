package co.omise;

import co.omise.exception.OmiseKeyUnsetException;
import co.omise.net.APIResource;

public class Omise {
	public static final String OMISE_API_VERSION = "2014-07-27";
	public static final String OMISE_JAVA_LIB_VERSION = "1.0.2";

	private static volatile String publicKey = null;
	private static volatile String secretKey = null;
	
	public static final int MODE_STAGING = 0;
	public static final int MODE_RELEASE = 1;
	private static int mode = Omise.MODE_RELEASE;
	

	/**
	 * Set the private and secret keys. You may pass {@code null} for an unused key.
	 * @param publicKey
	 * @param secretKey
	 */
	public static void setKeys(String publicKey, String secretKey) {
		setPublicKey(publicKey);
		setSecretKey(secretKey);
	}
	
	/**
	 * To use if you want to change the read timeout of API(APIResource)
	 * default value is 60 * 1000 (ms)
	 * @param readTimeout
	 */
	public static void setReadTimeout(int readTimeout) {
		APIResource.setReadTimeout(readTimeout);
	}
	public static int getReadTimeout() {
		return APIResource.getReadTimeout();
	}
	/**
	 * To use if you want to change the connect timeout of API(APIResource)
	 * default value is 10 * 1000 (ms)
	 * @param readTimeout
	 */
	public static void setConnectTimeout(int connectTimeout) {
		APIResource.setConnectTimeout(connectTimeout);
	}
	public static int getConnectTimeout() {
		return APIResource.getConnectTimeout();
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
	
	public static void setMode(int mode) {
		switch (mode) {
		case Omise.MODE_RELEASE:
			Omise.mode = mode;
			
			break;
		case Omise.MODE_STAGING:
			Omise.mode = mode;
			
			break;
		default:
			throw new IllegalArgumentException("Unsupported value. mode="+mode);
		}
	}
	public static int getMode() {
		return Omise.mode;
	}
}
