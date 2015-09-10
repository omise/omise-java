package co.omise.model;

import co.omise.OmiseSetting;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import org.junit.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TransferTest {
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
			// Transfer.retrieve
			Transfers transfers = Transfer.retrieve();
			assertNotNull("Transfer.retrieve (list all) retrieve the resource", transfers.getObject());
			assertEquals("Transfer.retrieve (list all) a list of transfers", transfers.getObject(), "list");
			//transfers.getData().get(0).destroy();
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
			Balance balance = Balance.retrieve();
			if(balance.getAvailable() >= 100000) {
				// Transfer.create
				Transfer transfer = Transfer.create(new HashMap<String, Object>() {
						{put("amount", 100000);}
					});
				assertEquals("Transfer.create create a transfer", transfer.getObject(), "transfer");

				// Transfer.retrieve(id)
				assertEquals("Transfer.retrieve(id) retrieve a transfer", transfer.getId(), Transfer.retrieve(transfer.getId()).getId());

				// Transfer.update
				transfer.setAmount(50000);
				transfer.save();
				assertTrue("Transfer.update update a transfer", transfer.getAmount().intValue() == 50000);

				// Transfer.destroy
				//assertTrue("Transfer.destroy delete a transfer", transfer.destroy().isDestroyed());
			} else {
				System.out.println("********** the balance is not available. TransferTest could not be run. **********");
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
