package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.Card;
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

	@SuppressWarnings("serial")
	@Test
	public void tests() {
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
			
			// Customer.retrieveのテスト
			assertEquals("Customer.retrieveの失敗：カスタマーの取得に失敗しました", Customer.retrieve().getObject(), "list");
			
			// Customer.createのテスト
			final Customer customer = Customer.create(new HashMap<String, Object>() {
					{put("email", "john.doe@example.com");}
					{put("description", "John Doe (id: 30)");}
					{put("card", token.getId());}
				});
			assertNotNull("Customer.createの失敗：リソースが作成できません", customer.getObject());
			assertEquals("Customer.createの失敗：取得したリソースがCustomerではありません", customer.getObject(), "customer");
			
			// Customer.retrieve(id)のテスト
			assertEquals("Customer.retrieve(id)の失敗：カスタマーの取得に失敗しました", customer.getId(), Customer.retrieve(customer.getId()).getId());
			
			// Customer.updateのテスト
			customer.update(new HashMap<String, Object>() {
					{put("email", "john.smith@example.com");}
					{put("description", "Another description");}
				});
			assertEquals("Customer.updateの失敗：カスタマーの更新に失敗しました", customer.getEmail(), "john.smith@example.com");
			assertEquals("Customer.updateの失敗：カスタマーの更新に失敗しました", customer.getDescription(), "Another description");
			
			// Card.retrieveのテスト
			Cards cards = customer.getCards();
			assertEquals("Card.retrieveの失敗：", cards.getObject(), "list");
			assertTrue("Card.retrieveの失敗：", cards.getTotal() == 1);
			
			// Cards.retrieve(id)のテスト
			Card card = cards.retrieve(cards.getData().get(0).getId());
			assertEquals("Cards.retrieve(id)の失敗：カードの取得に失敗しました", card.getId(), cards.getData().get(0).getId());
			Card cardBack = cards.retrieve(cards.getData().get(0).getId());
			
			// Card.updateのテスト
			card.update(new HashMap<String, Object>() {
					{put("expiration_month", 11);}
					{put("expiration_year", 2017);}
					{put("name", "Somchai Praset");}
					{put("postal_code", "10310");}
				});
			assertTrue("Card.updateの失敗：カードの更新に失敗しました", card.getExpirationMonth().intValue() == 11);
			assertTrue("Card.updateの失敗：カードの更新に失敗しました", card.getExpirationYear().intValue() == 2017);
			assertEquals("Card.updateの失敗：カードの更新に失敗しました", card.getName(), "Somchai Praset");
			assertEquals("Card.updateの失敗：カードの更新に失敗しました", card.getPostalCode(), "10310");
			
			// Card.reloadのテスト
			cardBack.reload();
			assertTrue("Card.reloadの失敗：カードのリロードに失敗しました", card.getExpirationMonth().intValue() == 11);
			
			// Card.destroyのテスト
			assertTrue("Card.destroyの失敗：カードの削除に失敗しました", card.destroy().isDestroyed());

			// Customer.destroyのテスト
			assertTrue("Customer.destroyの失敗：カスタマーの削除に失敗しました", customer.destroy().isDestroyed());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
