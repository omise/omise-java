package test.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import co.omise.model.Dispute;
import co.omise.model.Dispute.DisputeStatus;
import co.omise.model.Disputes;
import co.omise.Omise;

public class DisputeTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Omise.setMode(Omise.MODE_STAGING);
		OmiseSetting.setKeys();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Omise.setMode(Omise.MODE_RELEASE);
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
			assertNotNull("Dispute.retrieve (list all) failed: could not retrieve the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (list all) failed: the retrieved object is not a list of dispute", disputes.getObject(), "list");
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
			assertNotNull("Dispute.retrieve (DisputeStatus.PENDING) failed: could not retrieve the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (DisputeStatus.PENDING) failed: the retrieved object is not a list of dispute", disputes.getObject(), "list");
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
			assertNotNull("Dispute.retrieve (DisputeStatus.CLOSED) failed: could not retrieve the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (DisputeStatus.CLOSED) failed: the retrieved object is not a list of dispute", disputes.getObject(), "list");
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
			assertNotNull("Dispute.retrieve (DisputeStatus.OPEN) failed: could not retrieve the resource", disputes.getObject());
			assertEquals("Dispute.retrieve (DisputeStatus.OPEN) failed: the retrieved object is not a list of dispute", disputes.getObject(), "list");
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
				assertNotNull("Dispute.retrieve (id) failed: could not retrieve the resource", dispute.getObject());
				assertEquals("Dispute.retrieve (id) failed: the retrieved object is not a list of dispute", dispute.getObject(), "dispute");
				
				dispute.update(new HashMap<String, Object>() {
					{put("message", "Proofs and other information regarding the disputed charge ...");}
				});
				assertEquals("Dispute.update failed", dispute.getMessage(), "Proofs and other information regarding the disputed charge ...");
			} else {
				System.out.println("**WORNING!! RetrieveAndUpdate is not Tested **");
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
