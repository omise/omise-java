package co.omise.model;

import co.omise.Omise;
import co.omise.OmiseSetting;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import org.junit.*;
import java.io.IOException;
import static org.junit.Assert.*;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.PerfTest;


public class AccountTest {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		OmiseSetting.setKeys();
		Omise.setOmiseAPIVersion("2014-07-27");
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
	public void testRetrieve() {
		try {

			Account account = Account.retrieve();
			assertNotNull("Retrieve the resource", account.getObject());
			assertEquals("The retrieved resource is an account", account.getObject(), "account");
			assertNotEquals("test@omise.co", account.getEmail());
			account.reload();
			assertNotNull("Retrieve the resource", account.getObject());
			assertEquals("The object retrieved from reload is ", account.getObject(), "account");
			assertNotEquals("test@omise.co", account.getEmail());

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

}
