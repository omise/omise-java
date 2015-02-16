package omise.co;

public class Omise {
	public static final String OMISE_API_VERSION = "2014-07-27";
	public static final String OMISE_JAVA_LIB_VERSION = "1.0.0";
	
	public enum OmiseURL {
		API {
			@Override
			public String toString() {
				return "https://api.omise.co/";
			}
		},
		VAULT {
			@Override
			public String toString() {
				return "https://vault.omise.co/";
			}
		}
	}
	
	public static volatile String publicKey;
	public static volatile String secretKey;
}
