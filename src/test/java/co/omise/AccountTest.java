package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Account;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
			
			assertNotNull("リソースが取得できません", account.getObject());
			assertEquals("取得したリソースがAccountではありません", account.getObject(), "account");
			
			account.reload();
			assertEquals("reloadで取得したオブジェクトが不正です", account.getObject(), "balance");
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
			
			assertTrue("複数のインスタンスが生成されています", accountA == accountB);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
