package co.omise.live;

import co.omise.Client;
import co.omise.models.OmiseException;
import co.omise.models.Source;
import co.omise.models.SourceType;
import co.omise.models.PlatformType;
import co.omise.requests.Request;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LiveSourceRequestTest extends BaseLiveTest {
    private Client client;

    @Before
    public void setup() throws Exception {
        client = getLiveClient();
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingBay() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InternetBankingBay)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_bay", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingKtb() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InternetBankingKtb)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_ktb", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingScb() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InternetBankingScb)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_scb", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInternetBankingBbl() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InternetBankingBbl)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("internet_banking_bbl", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceBillPayment() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.BillPaymentTescoLotus)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("bill_payment_tesco_lotus", source.getType().toString());
        assertEquals("offline", source.getFlow().toString());
        assertNotNull(source.getType());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceAlipay() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.Alipay)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("alipay", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceBarcodeAlipay() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.BarcodeAlipay)
                .amount(200000)
                .currency("thb")
                .barcode("1234567890")
                .storeId("store_1")
                .storeName("store 1")
                .terminalId("POS-01")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("barcode_alipay", source.getType().toString());
        assertEquals("offline", source.getFlow().toString());
        assertEquals(200000, source.getAmount());
        assertEquals("THB", source.getCurrency());
        assertEquals("1234567890", source.getBarcode());
        assertEquals("store_1", source.getStoreId());
        assertEquals("store 1", source.getStoreName());
        assertEquals("POS-01", source.getTerminalId());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceInstallment() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InstallmentBay)
                .amount(500000)
                .currency("thb")
                .installmentTerm(4)
                .build();

        Source source = client.sendRequest(request);

        System.out.printf("created source: %s, type = %s", source.getId(), source.getType());

        assertNotNull(source);
        assertEquals(SourceType.InstallmentBay, source.getType());
        assertEquals(4, source.getInstallmentTerm());
        assertEquals(500000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceZeroInterestInstallments() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InstallmentBay)
                .amount(500000)
                .currency("thb")
                .installmentTerm(4)
                .zeroInterestInstallments(true)
                .build();

        Source source = client.sendRequest(request);

        System.out.printf("created source: %s, type = %s", source.getId(), source.getType());

        assertNotNull(source);
        assertEquals(SourceType.InstallmentBay, source.getType());
        assertEquals(4, source.getInstallmentTerm());
        assertEquals(500000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
        assertTrue(source.isZeroInterestInstallments());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceMobileBankingBay() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.MobileBankingBay)
                .amount(10000)
                .currency("thb")
                .platformType(PlatformType.iOS)
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("mobile_banking_bay", source.getType().toString());
        assertEquals("app_redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
        assertEquals("IOS", source.getPlatformType().toString());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceMobileBankingBbl() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.MobileBankingBbl)
                .amount(25000)
                .currency("thb")
                .platformType(PlatformType.Android)
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("mobile_banking_bbl", source.getType().toString());
        assertEquals("app_redirect", source.getFlow().toString());
        assertEquals(25000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
        assertEquals("ANDROID", source.getPlatformType().toString());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceMobileBankingKbank() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.MobileBankingKbank)
                .amount(25000)
                .currency("thb")
                .platformType(PlatformType.Android)
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("mobile_banking_kbank", source.getType().toString());
        assertEquals("app_redirect", source.getFlow().toString());
        assertEquals(25000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
        assertEquals("ANDROID", source.getPlatformType().toString());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceMobileBankingOcbcPao() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.MobileBankingOcbcPao)
                .amount(25000)
                .currency("thb")
                .platformType(PlatformType.iOS)
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("mobile_banking_ocbc_pao", source.getType().toString());
        assertEquals("app_redirect", source.getFlow().toString());
        assertEquals(25000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
        assertEquals("IOS", source.getPlatformType().toString());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceMobileBankingScb() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.MobileBankingScb)
                .amount(10000)
                .currency("thb")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("mobile_banking_scb", source.getType().toString());
        assertEquals("app_redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("THB", source.getCurrency());
    }

    @Test
    @Ignore("only hit the network when we need to.")
    public void testLiveSourceFpx() throws IOException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.Fpx)
                .amount(10000)
                .currency("myr")
                .bank("cimb")
                .email("example@omise.co")
                .build();

        Source source = client.sendRequest(request);

        System.out.println("created source: " + source.getId());

        assertNotNull(source.getId());
        assertEquals("fpx", source.getType().toString());
        assertEquals("redirect", source.getFlow().toString());
        assertEquals(10000L, source.getAmount());
        assertEquals("MYR", source.getCurrency());
        assertEquals("cimb", source.getBank());
        assertEquals("example@omise.co", source.getEmail());
    }
}
