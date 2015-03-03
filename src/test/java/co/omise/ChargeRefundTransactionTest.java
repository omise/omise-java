package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Charge;
import main.java.co.omise.model.Charges;
import main.java.co.omise.model.Refund;
import main.java.co.omise.model.Refunds;
import main.java.co.omise.model.Token;
import main.java.co.omise.model.Transaction;
import main.java.co.omise.model.Transactions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChargeRefundTransactionTest {

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
			// Charge.retrieve
			Charges charges = Charge.retrieve();

			assertNotNull("Charge.retrieve (list all) failed: could not retrieve the resource", charges.getObject());
			assertEquals("Charge.retrieve (list all) failed: the retrieved resource is not a list of charges", charges.getObject(), "list");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

	@SuppressWarnings("serial")
	@Test
	public void testOther() {
		try {
			// Charge.create
			final Token token = Token.create(new HashMap<String, Object>() {
					{put("card[name]", "Somchai Prasert");}
					{put("card[number]", 4242424242424242L);}
					{put("card[expiration_month]", 10);}
					{put("card[expiration_year]", 2018);}
					{put("card[city]", "Bangkok");}
					{put("card[postal_code]", "10320");}
					{put("card[security_code]", 123);}
				});
			Charge charge = Charge.create(new HashMap<String, Object>() {
					{put("return_uri", "https://example.co.th/orders/384/complete");}
					{put("amount", 100000);}
					{put("currency", "thb");}
					{put("description", "Order-384");}
					{put("ip", "127.0.0.1");}
					{put("card", token.getId());}
					{put("capture", false);}
				});
			assertEquals("Charge.create failed: could not create a charge", charge.getObject(), "charge");

			// Charge.retrieve
			assertEquals("Charge.retrieve(id) failed: could not retrieve a charge", charge.getId(), Charge.retrieve(charge.getId()).getId());

			// Charge.update
			charge.update(new HashMap<String, Object>() {
					{put("description", "Another description");}
				});
			assertEquals("Charge.update failed: could not update a charge", charge.getDescription(), "Another description");

			// Charge.capture
			charge.capture();
			assertTrue("Charge.capture failed: could not capture a charge", charge.getCaptured());

			// Refund.retrieve (list all)
			Refunds refunds = charge.refunds();
			assertNotNull("Refund.retrieve (list all) failed: could not retrieve the resource", refunds.getObject());
			assertEquals("Refund.retrieve (list all) failed: the retrieved object is not a list of refunds", refunds.getObject(), "list");

			// Refund.create
			Refund refund = refunds.create(new HashMap<String, Object>() {
					{put("amount", 10000);}
				});
			assertEquals("Refund.create failed: could not create a refund", refund.getObject(), "refund");

			// Refund.retrieve(id)
			assertEquals("Refund.retrieve(id) failed: could not retrieve a refund", refund.getId(), refunds.retrieve(refund.getId()).getId());

			// Transaction.retrieve (list all)
			Transactions transactions = Transaction.retrieve();
			assertEquals("Transaction.retrieve (list all) failed: the retrieved object is not a list of transactions", transactions.getObject(), "list");

			// Transaction.retrieve(id)
			Transaction transaction = Transaction.retrieve(charge.getTransaction());
			assertEquals("Transaction.retrieve(id) failed: could not retrieve a transaction", transaction.getObject(), "transaction");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
