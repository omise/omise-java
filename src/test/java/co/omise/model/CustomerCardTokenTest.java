package co.omise.model;

import co.omise.*;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseException;
import org.junit.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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


			assertNotNull("Token.create ", token.getObject());
			assertEquals("Token.create ", token.getObject(), "token");
			assertEquals("Token.create ", token.getCard().getObject(), "card");

			// Token.retrieve
			assertEquals("Token.retrieve ", token.getId(), Token.retrieve(token.getId()).getId());

			// Customer.retrieve
			assertEquals("Customer.retrieve ", Customer.retrieve().getObject(), "list");

			// Customer.create
			final Customer customer = Customer.create(new HashMap<String, Object>() {
					{put("email", "john.doe@example.com");}
					{put("description", "John Doe (id: 35)");}
					{put("card", token.getId());}
				});
			assertNotNull("Customer.create ", customer.getObject());
			assertEquals("Customer.create ", customer.getObject(), "customer");

			// Customer.retrieve(id)
			assertEquals("Customer.retrieve(id) ", customer.getId(), Customer.retrieve(customer.getId()).getId());
			assertEquals("Customer.retrieve(id) ", customer.getEmail(), "john.doe@example.com");

			// Customer.update
			customer.update(new HashMap<String, Object>() {
					{put("email", "john.smith@example.com");}
					{put("description", "Another description");}
				});
			assertEquals("Customer.update ", customer.getEmail(), "john.smith@example.com");
			assertEquals("Customer.update ", customer.getDescription(), "Another description");

			// Card.retrieve
			Cards cards = customer.getCards();
			assertEquals("Card.retrieve ", cards.getObject(), "list");
			assertTrue("Card.retrieve ", cards.getTotal() == 1);

			// Cards.retrieve(id)
			Card card = cards.retrieve(cards.getData().get(0).getId());
			assertEquals("Cards.retrieve(id) ", card.getId(), cards.getData().get(0).getId());
			Card cardBack = cards.retrieve(cards.getData().get(0).getId());

			// Card.update
			card.update(new HashMap<String, Object>() {
					{put("expiration_month", 11);}
					{put("expiration_year", 2017);}
					{put("name", "Somchai Praset");}
					{put("postal_code", "10310");}
				});
			assertTrue("Card.update ", card.getExpirationMonth().intValue() == 11);
			assertTrue("Card.update ", card.getExpirationYear().intValue() == 2017);
			assertEquals("Card.update ", card.getName(), "Somchai Praset");
			assertEquals("Card.update ", card.getPostalCode(), "10310");

			// Card.reload
			cardBack.reload();
			assertTrue("Card.reload ", card.getExpirationMonth().intValue() == 11);

			// Card.destroy
			// assertTrue("Card.destroy ", card.destroy().isDestroyed());

			// Customer.destroy
			// assertTrue("Customer.destroy ", customer.destroy().isDestroyed());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (OmiseAPIException e) {
			fail(e.getOmiseError().toString());
		} catch (OmiseException e) {
			fail(e.getMessage());
		}
	}
}
