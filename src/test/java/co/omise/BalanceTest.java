package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import co.omise.model.Balance;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BalanceTest {

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
	public void testRetrieveAndReload() {
		try {
			Balance balance = Balance.retrieve();

			assertNotNull("Could not retrieve the resource", balance.getObject());
			assertEquals("The retrieved resource is not a balance", balance.getObject(), "balance");

			balance.reload();
			assertEquals("The object retrieved from reload is invalid", balance.getObject(), "balance");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSameInstance() {
		try {
			Balance balanceA = Balance.retrieve();
			Balance balanceB = Balance.retrieve();

			assertTrue("Multiple instances are created", balanceA == balanceB);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
