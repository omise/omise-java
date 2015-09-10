package co.omise.model;

import co.omise.OmiseSetting;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import org.junit.*;
import java.io.IOException;
import static org.junit.Assert.*;


public class AccountTest {



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
			Account accountA = Account.retrieve();
			Account accountB = Account.retrieve();

			assertTrue("Multiple instances were created", accountA == accountB);
			assertFalse(accountA != accountB);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
