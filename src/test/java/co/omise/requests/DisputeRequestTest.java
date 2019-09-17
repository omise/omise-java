package co.omise.requests;

import co.omise.Serializer;
import co.omise.models.Dispute;
import co.omise.models.DisputeStatus;
import co.omise.models.DisputeReasonCode;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.IOException;

public class DisputeRequestTest extends RequestTest {
    private static final String DISPUTE_ID = "dspt_test_5089off452g5m5te7xs";

    @Test
    public void testList() throws IOException, OmiseException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().build();
        ScopedList<Dispute> list = getTestRequester().sendRequest(request);
        assertRequested("GET", "/disputes", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());
    }

    @Test
    public void testListWithStatus() throws IOException, OmiseException {
        Request<ScopedList<Dispute>> wonDisputesRequest = new Dispute.ListRequestBuilder()
                .status(DisputeStatus.Closed)
                .build();
        ScopedList<Dispute> wonDisputes = getTestRequester().sendRequest(wonDisputesRequest);
        assertRequested("GET", "/disputes/closed", 200);
        assertEquals(DisputeStatus.Won, wonDisputes.getData().get(0).getStatus());

        Request<ScopedList<Dispute>> openDisputesRequest = new Dispute.ListRequestBuilder()
                .status(DisputeStatus.Open)
                .build();
        ScopedList<Dispute> openDisputes = getTestRequester().sendRequest(openDisputesRequest);
        assertRequested("GET", "/disputes/open", 200);
        assertEquals(DisputeStatus.Open, openDisputes.getData().get(0).getStatus());

        Request<ScopedList<Dispute>> pendingDisputesRequest = new Dispute.ListRequestBuilder()
                .status(DisputeStatus.Pending)
                .build();
        ScopedList<Dispute> pendingDisputes = getTestRequester().sendRequest(pendingDisputesRequest);
        assertRequested("GET", "/disputes/pending", 200);
        assertEquals(DisputeStatus.Pending, pendingDisputes.getData().get(0).getStatus());
    }

    @Test
    public void testGet() throws IOException, OmiseException {
        Request<Dispute> request = new Dispute.GetRequestBuilder(DISPUTE_ID).build();
        Dispute dispute = getTestRequester().sendRequest(request);
        assertRequested("GET", "/disputes/" + DISPUTE_ID, 200);

        assertEquals("dspt_test_5089off452g5m5te7xs", dispute.getId());
        assertEquals(100000L, dispute.getAmount());
        assertEquals("thb", dispute.getCurrency());
        assertEquals("chrg_test_5089odjlzg9j7tw4i1q", dispute.getCharge());
        assertEquals("DESCRIPTION", dispute.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", dispute.getMetadata().get("invoice_id"));
        assertEquals("trxn_test_5d1cy88q3esdr60yr4m", dispute.getTransactions().get(0).getId());
        assertEquals(DisputeReasonCode.GoodsOrServicesNotProvided, dispute.getReasonCode());
        assertEquals("Services not provided or Merchandise not received", dispute.getReasonMessage());

        DateTimeFormatter formatter = Serializer.defaultSerializer().dateTimeFormatter();
        LocalDateTime closedAt = LocalDateTime.parse("2015-03-23T01:24:39Z", formatter);
        assertEquals(closedAt, dispute.getClosedDate().toLocalDateTime());
    }

    @Test
    public void testUpdate() throws IOException, OmiseException {
        Request<Dispute> request = new Dispute.UpdateRequestBuilder(DISPUTE_ID)
                .message("Your dispute message")
                .metadata("description", "DESCRIPTION")
                .metadata("invoice_id", "inv_N1ayTWJ2FV")
                .build();

        Dispute dispute = getTestRequester().sendRequest(request);

        assertRequested("PATCH", "/disputes/" + DISPUTE_ID, 200);

        assertEquals("dspt_test_5089off452g5m5te7xs", dispute.getId());
        assertEquals("Your dispute message", dispute.getMessage());

        assertEquals("DESCRIPTION", dispute.getMetadata().get("description"));
        assertEquals("inv_N1ayTWJ2FV", dispute.getMetadata().get("invoice_id"));
    }

}
