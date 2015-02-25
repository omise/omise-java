package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Token;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TokenTest {

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
	public void testCreateAndRetrieve() {
		try {
			@SuppressWarnings("serial")
			Token tokenA = Token.create(new HashMap<String, Object>() {
				{put("card[name]", "Somchai Prasert");}
				{put("card[number]", 4242424242424242L);}
				{put("card[expiration_month]", 10);}
				{put("card[expiration_year]", 2018);}
				{put("card[city]", "Bangkok");}
				{put("card[postal_code]", "10320");}
				{put("card[security_code]", 123);}
			});
			
			assertNotNull("リソースが作成できません", tokenA.getObject());
			assertEquals("取得したリソースがTokenではありません", tokenA.getObject(), "token");
			assertEquals("トークンの生成に失敗しました", tokenA.getCard().getObject(), "card");
			
			Token tokenB = Token.retrieve(tokenA.getId());
			assertEquals("トークンの取得に失敗しました", tokenA.getId(), tokenB.getId());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
