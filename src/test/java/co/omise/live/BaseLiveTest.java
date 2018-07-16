package co.omise.live;

import co.omise.Client;

class BaseLiveTest {
    private final String LIVETEST_PKEY = "[YOUR_PKEY]";
    private final String LIVETEST_SKEY = "[YOUR_SKEY]";
    private final String EXPECTED_EMAIL = "[YOUR_EMAIL]";
    private final String LIVETEST_REFUND = "[YOUR_CHARGE_ID]";

    String getUserEmail() {
        return EXPECTED_EMAIL;
    }

    String getChargeRefundId() {
        return LIVETEST_REFUND;
    }

    Client getLiveClient() throws Exception {
        return new Client(LIVETEST_PKEY, LIVETEST_SKEY);
    }
}