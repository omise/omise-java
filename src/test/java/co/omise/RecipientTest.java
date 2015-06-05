package test.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import junit.extensions.TestSetup;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import co.omise.model.Account;
import co.omise.model.DeleteRecipient;
import co.omise.model.Recipient;
import co.omise.model.Recipients;
import co.omise.Omise;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RecipientTest {
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
			assertNotNull("Recipient.create failed: could not create a recipient", recipient.getObject());
			assertEquals("Recipient.create failed: the retrieved object is not a recipient", recipient.getObject(), "recipient");
			
			//Recipient.retrieve
			Recipients recipients = Recipient.retrieve();
			assertNotNull("Recipient.retrieve failed: could not retrieve a recipient", recipients.getObject());
			assertEquals("Recipient.retrieve failed: the retrieved object is not a list", recipients.getObject(), "list");
			assertEquals("Recipient.retrieve failed: the retrieved object is not a recipient list", recipients.getData().get(0).getObject(), "recipient");
			
			//Recipient.retrieve(id)
			assertEquals("Recipient.retrieve(id) failed: the retrieved object is not a recipient", Recipient.retrieve(recipient.getId()).getObject(), "recipient");
			
			
			//Recipient.update
			recipient.update(new HashMap<String, Object>() {
				{put("name", "somchai@prasert.com");}
				{put("bank_account", new HashMap<String, Object>() {
					{put("brand", "kbank");}
					{put("number", "1234567890");}
					{put("name", "SOMCHAI PRASERT");}
				});}
			});
			assertEquals("Recipient.update failed: could not update Recipient", recipient.getName(), "somchai@prasert.com");
			assertEquals("Recipient.update failed: could not update Recipient", recipient.getBank_account().getBrand(), "kbank");
			assertEquals("Recipient.update failed: could not update Recipient", recipient.getBank_account().getLast_digits(), "7890");
			assertEquals("Recipient.update failed: could not update Recipient", recipient.getBank_account().getName(), "SOMCHAI PRASERT");
			
			//Recipient.destroy
			DeleteRecipient deleteRecipient = recipient.destroy();
			assertTrue("Recipient.destroy failed: could not delete a recipient", deleteRecipient.isDestroyed());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
