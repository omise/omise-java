package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Balance;

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
			
			assertNotNull("リソースが取得できません", balance.getObject());
			assertEquals("取得したリソースがBalanceではありません", balance.getObject(), "balance");

			balance.reload();
			assertEquals("reloadで取得したオブジェクトが不正です", balance.getObject(), "balance");
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
			
			assertTrue("複数のインスタンスが生成されています", balanceA == balanceB);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
