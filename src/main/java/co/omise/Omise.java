package co.omise;

import co.omise.exception.OmiseKeyUnsetException;
import co.omise.net.APIResource;

public class Omise {
	public static final String OMISE_API_VERSION = "2015-08-28";
	public static final String OMISE_JAVA_LIB_VERSION = "1.0.4";

	private static volatile String publicKey = null;
	private static volatile String secretKey = null;


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
		if(publicKey == null) throw new OmiseKeyUnsetException("public key is required.", null);

		return publicKey;
	}
	public static String getSecretKey() throws OmiseKeyUnsetException {
		if(secretKey == null) throw new OmiseKeyUnsetException("secret key is required.", null);

		return secretKey;
	}
}
