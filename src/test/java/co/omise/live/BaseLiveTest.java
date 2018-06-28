package co.omise.live;

class BaseLiveTest {
    private final String STAGING_PKEY = "[YOUR_PKEY]";
    private final String STAGING_SKEY = "[YOUR_SKEY]";
    private final String EXPECTED_EMAIL = "[YOUR_EMAIL]";

    String getPublicKey() {
        return STAGING_PKEY;
    }

    String getSecretKey() {
        return STAGING_SKEY;
    }

    String getUserEmail() {
        return EXPECTED_EMAIL;
    }
}
