package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Charge;
import main.java.co.omise.model.Charges;
import main.java.co.omise.model.Token;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChargeTest {

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
			Charges charges = Charge.retrieve();
			
			assertNotNull("Charge.retrieve（List ALL）の失敗：リソースが取得できません", charges.getObject());
			assertEquals("Charge.retrieve（List ALL）の失敗：取得したリソースがlist(Charge)ではありません", charges.getObject(), "list");
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
			// Charge.createのテスト
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
			assertEquals("Charge.createの失敗：チャージを生成できません", charge.getObject(), "charge");
			
			// Charge.retrieveのテスト
			assertEquals("Charge.retrieve(id)の失敗：チャージを取得できません", charge.getId(), Charge.retrieve(charge.getId()).getId());

			// Charge.updateのテスト
			charge.update(new HashMap<String, Object>() {
					{put("description", "Another description");}
				});
			assertEquals("Charge.updateの失敗：チャージを更新できません", charge.getDescription(), "Another description");
			
			// Charge.captureのテスト
			charge.capture();
			assertTrue("Charge.captureの失敗：キャプチャーできません", charge.getCaptured());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
