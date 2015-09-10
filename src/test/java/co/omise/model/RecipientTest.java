package co.omise.model;

import co.omise.OmiseSetting;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import org.junit.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RecipientTest {
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
	public void tests() {
		try {
			//Recipient.create
			Recipient recipient = Recipient.create(new HashMap<String, Object>() {
				{put("name", "Somchai Prasert");}
				{put("email", "somchai.prasert@example.com");}
				{put("type", "individual");}
				{put("bank_account", new HashMap<String, Object>() {
					{put("brand", "bbl");}
					{put("number", "1234567890");}
					{put("name", "SOMCHAI PRASERT");}
				});}
			});
			assertNotNull("Recipient.create a recipient", recipient.getObject());
			assertEquals("Recipient.create a recipient", recipient.getObject(), "recipient");

			//Recipient.retrieve
			Recipients recipients = Recipient.retrieve();
			assertNotNull("Recipient.retrieve a recipient", recipients.getObject());
			assertEquals("Recipient.retrieve list", recipients.getObject(), "list");
			assertEquals("Recipient.retrieve recipient list", recipients.getData().get(0).getObject(), "recipient");

			//Recipient.retrieve(id)
			assertEquals("Recipient.retrieve(id) recipient", Recipient.retrieve(recipient.getId()).getObject(), "recipient");


			//Recipient.update
			Recipient rec = recipient.update(new HashMap<String, Object>() {
				{put("name", "somchai@prasert.com");}
				{put("bank_account", new HashMap<String, Object>() {
					{put("brand", "tcrb");}
					{put("number", "1234567890");}
					{put("name", "SOMCHAI PRASERT");}
				});}
			});
			assertEquals("Recipient.update updated Recipient", rec.getName(), "somchai@prasert.com");
			assertEquals("Recipient.update updated Recipient", rec.getBank_account().getBrand(), "tcrb");
			assertEquals("Recipient.update updated Recipient", rec.getBank_account().getLast_digits(), "7890");
			assertEquals("Recipient.update updated Recipient", rec.getBank_account().getName(), "SOMCHAI PRASERT");

			//Recipient.destroy
		//	DeleteRecipient deleteRecipient = recipient.destroy();
		//	assertTrue("Recipient.destroy delete a recipient", deleteRecipient.isDestroyed());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
