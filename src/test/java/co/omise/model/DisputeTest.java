package co.omise.model;

import co.omise.OmiseSetting;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import co.omise.model.Dispute.DisputeStatus;
import org.junit.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DisputeTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		OmiseSetting.setKeys();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListAll() {
		try {
			Disputes disputes = Dispute.retrieve();
			assertNotNull("Dispute.retrieve (list all) the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (list all) list of dispute", disputes.getObject(), "list");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRetrievePending() {
		try {
			Disputes disputes = Dispute.retrieve(DisputeStatus.PENDING);
			assertNotNull("Dispute.retrieve (DisputeStatus.PENDING) the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (DisputeStatus.PENDING) list of dispute", disputes.getObject(), "list");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRetrieveClosed() {
		try {
			Disputes disputes = Dispute.retrieve(DisputeStatus.CLOSED);
			assertNotNull("Dispute.retrieve (DisputeStatus.CLOSED) the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (DisputeStatus.CLOSED) list of dispute", disputes.getObject(), "list");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRetrieveOpen() {
		try {
			Disputes disputes = Dispute.retrieve(DisputeStatus.OPEN);
			assertNotNull("Dispute.retrieve (DisputeStatus.OPEN) the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (DisputeStatus.OPEN) list of dispute", disputes.getObject(), "list");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRetrieveAndUpdate() {
		try {
			Disputes disputes = Dispute.retrieve();
			if(disputes.getData().size() > 0) {
				Dispute dispute = Dispute.retrieve(disputes.getData().get(0).getId());
				assertNotNull("Dispute.retrieve (id) the resource", dispute.getObject());
				assertEquals("Dispute.retrieve (id) list of dispute", dispute.getObject(), "dispute");

				dispute.update(new HashMap<String, Object>() {
					{put("message", "Proofs and other information regarding the disputed charge ...");}
				});
				assertEquals("Dispute.update failed", dispute.getMessage(), "Proofs and other information regarding the disputed charge ...");
			} else {
				System.out.println("**WARNING** RetrieveAndUpdate is not Tested.");
			}
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
