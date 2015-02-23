package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;

import main.java.co.omise.Omise;
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
		Omise.setKeys("pkey", "skey");
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
			Balance balance = Balance.retrieve();
			
			assertNotNull("リソースが取得できません", balance.getObject());
			assertEquals("取得したリソースがBalanceではありません", balance.getObject(), "balance");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}

}
