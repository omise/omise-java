package co.omise.model;

import co.omise.OmiseSetting;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

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

			assertNotNull("Retrieve the resource", balance.getObject());
			assertEquals("The retrieved resource is a balance", balance.getObject(), "balance");

			balance.reload();
			assertEquals("The object retrieved from reload is", balance.getObject(), "balance");
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
