package main.java.co.omise.model;

import java.io.IOException;
import java.util.List;

import main.java.co.omise.exeption.OmiseException;

public class Cards extends OmiseList {
	protected String customer_id = null;
	protected List<Card> data = null;
	
	public List<Card> getData() {
		return data;
	}
	
	protected Cards setCustomerID(String id) {
		customer_id = id;
		for(Card card : data) {
			card.customer_id = id;
		}
		
		return this;
	}
	
	public Card retrieve(String id) throws IOException, OmiseException {
		Card card = (Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, Card.ENDPOINT, id), RequestMethod.GET, null, Card.class);
		card.customer_id = customer_id;
		
		return card;
	}
}
