package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Cards;
import main.java.co.omise.model.Customer;
import main.java.co.omise.model.Token;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerCardTokenTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@SuppressWarnings("serial")
	@Test
	public void test() {
		try {
			// Token.createのテスト
			final Token token = Token.create(new HashMap<String, Object>() {
					{put("card[name]", "Somchai Prasert");}
					{put("card[number]", 4242424242424242L);}
					{put("card[expiration_month]", 10);}
					{put("card[expiration_year]", 2018);}
					{put("card[city]", "Bangkok");}
					{put("card[postal_code]", "10320");}
					{put("card[security_code]", 123);}
				});
			assertNotNull("Token.createの失敗：リソースが作成できません", token.getObject());
			assertEquals("Token.createの失敗：取得したリソースがTokenではありません", token.getObject(), "token");
			assertEquals("Token.createの失敗：トークンの生成に失敗しました", token.getCard().getObject(), "card");
			
			// Token.retrieveのテスト
			assertEquals("Token.retrieveの失敗：トークンの取得に失敗しました", token.getId(), Token.retrieve(token.getId()).getId());
			
			// Customer.createのテスト
			final Customer customer = Customer.create(new HashMap<String, Object>() {
					{put("email", "john.doe@example.com");}
					{put("description", "John Doe (id: 30)");}
					{put("card", token.getId());}
				});
			assertNotNull("Customer.createの失敗：リソースが作成できません", customer.getObject());
			assertEquals("Customer.createの失敗：取得したリソースがCustomerではありません", customer.getObject(), "customer");
			
			// Customer.retrieveのテスト
			assertEquals("Customer.retrieveの失敗：カスタマーの取得に失敗しました", customer.getId(), Customer.retrieve(customer.getId()).getId());
			
			// Customer.updateのテスト
			customer.update(new HashMap<String, Object>() {
					{put("email", "john.smith@example.com");}
					{put("description", "Another description");}
				});
			assertEquals("Customer.updateの失敗：カスタマーの更新に失敗しました", customer.getEmail(), "john.smith@example.com");
			assertEquals("Customer.updateの失敗：カスタマーの更新に失敗しました", customer.getDescription(), "Another description");
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
