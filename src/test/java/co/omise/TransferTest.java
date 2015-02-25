package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Transfer;
import main.java.co.omise.model.Transfers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
			// Transfer.retrieveのテスト
			Transfers transfers = Transfer.retrieve();
			assertNotNull("Transfer.retrieve（List ALL）の失敗：リソースが取得できません", transfers.getObject());
			assertEquals("Transfer.retrieve（List ALL）の失敗：取得したリソースがlistではありません", transfers.getObject(), "list");
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
			// Transfer.createのテスト
			Transfer transfer = Transfer.create(new HashMap<String, Object>() {
					{put("amount", 100000);}
				});
			assertEquals("Transfer.createの失敗：Transferが生成できません", transfer.getObject(), "transfer");
			
			// Transfer.retrieve(id)のテスト
			assertEquals("Transfer.retrieve(id)の失敗：Transferが取得できません", transfer.getId(), Transfer.retrieve(transfer.getId()).getId());
			
			// Transfer.updateのテスト
			transfer.setAmount(50000);
			transfer.save();
			assertTrue("Transfer.updateの失敗：Transferが更新できません", transfer.getAmount().intValue() == 50000);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
