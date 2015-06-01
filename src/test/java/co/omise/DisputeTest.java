package test.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import co.omise.model.Dispute;
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
	public void testRetrieve() {
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
}
