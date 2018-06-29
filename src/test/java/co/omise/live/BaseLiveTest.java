package co.omise.live;

import co.omise.Client;

class BaseLiveTest {
    private final String LIVETEST_PKEY = "[YOUR_PKEY]";
    private final String LIVETEST_SKEY = "[YOUR_SKEY]";
    private final String EXPECTED_EMAIL = "[YOUR_EMAIL]";

    String getUserEmail() {
        return EXPECTED_EMAIL;
    }

    Client getLiveClient() throws Exception {
        return new Client(LIVETEST_PKEY, LIVETEST_SKEY);
    }
}