package test.java.co.omise;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import co.omise.model.Card;
import co.omise.model.Cards;
import co.omise.model.Customer;
import co.omise.model.Token;

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
			// Token.create
			final Token token = Token.create(new HashMap<String, Object>() {
					{put("card[name]", "Somchai Prasert");}
					{put("card[number]", 4242424242424242L);}
					{put("card[expiration_month]", 10);}
					{put("card[expiration_year]", 2018);}
					{put("card[city]", "Bangkok");}
					{put("card[postal_code]", "10320");}
					{put("card[security_code]", 123);}
				});
			assertNotNull("Token.create failed: could not create a token", token.getObject());
			assertEquals("Token.create failed: the retrieved object is not a token", token.getObject(), "token");
			assertEquals("Token.create failed: the card creation failed", token.getCard().getObject(), "card");

			// Token.retrieve
			assertEquals("Token.retrieve failed: could not retrieve a token", token.getId(), Token.retrieve(token.getId()).getId());

			// Customer.retrieve
			assertEquals("Customer.retrieve failed: could not retrieve a customer", Customer.retrieve().getObject(), "list");

			// Customer.create
			final Customer customer = Customer.create(new HashMap<String, Object>() {
					{put("email", "john.doe@example.com");}
					{put("description", "John Doe (id: 30)");}
					{put("card", token.getId());}
				});
			assertNotNull("Customer.create failed: could not create a customer", customer.getObject());
			assertEquals("Customer.create failed: the retrieved object is not a customer", customer.getObject(), "customer");

			// Customer.retrieve(id)
			assertEquals("Customer.retrieve(id) failed: could not retrieve a customer", customer.getId(), Customer.retrieve(customer.getId()).getId());

			// Customer.update
			customer.update(new HashMap<String, Object>() {
					{put("email", "john.smith@example.com");}
					{put("description", "Another description");}
				});
			assertEquals("Customer.update failed: could not update a customer", customer.getEmail(), "john.smith@example.com");
			assertEquals("Customer.update failed: could not update a customer", customer.getDescription(), "Another description");

			// Card.retrieve
			Cards cards = customer.getCards();
			assertEquals("Card.retrieve failed: the retrieved object is not a list of cards", cards.getObject(), "list");
			assertTrue("Card.retrieve failed: the retrieved object has extra members", cards.getTotal() == 1);

			// Cards.retrieve(id)
			Card card = cards.retrieve(cards.getData().get(0).getId());
			assertEquals("Cards.retrieve(id) failed: could not retrieve a card", card.getId(), cards.getData().get(0).getId());
			Card cardBack = cards.retrieve(cards.getData().get(0).getId());

			// Card.update
			card.update(new HashMap<String, Object>() {
					{put("expiration_month", 11);}
					{put("expiration_year", 2017);}
					{put("name", "Somchai Praset");}
					{put("postal_code", "10310");}
				});
			assertTrue("Card.update failed: could not update a card", card.getExpirationMonth().intValue() == 11);
			assertTrue("Card.update failed: could not update a card", card.getExpirationYear().intValue() == 2017);
			assertEquals("Card.update failed: could not update a card", card.getName(), "Somchai Praset");
			assertEquals("Card.update failed: could not update a card", card.getPostalCode(), "10310");

			// Card.reload
			cardBack.reload();
			assertTrue("Card.reload failed: could not reload a card", card.getExpirationMonth().intValue() == 11);

			// Card.destroy
			assertTrue("Card.destroy failed: could not delete a card", card.destroy().isDestroyed());

			// Customer.destroy
			assertTrue("Customer.destroy failed: could not delete a customer", customer.destroy().isDestroyed());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
