package main.java.co.omise.model;

import java.io.IOException;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Transaction extends APIResource {
	protected static final String ENDPOINT = "transactions";
	
	protected String object = null;
	protected String id = null;
	protected String type = null;
	protected Integer amount = null;
	protected String currency = null;
	protected String created = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Integer getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getCreated() {
		return created;
	}
	
	public static Transactions retrieve() throws IOException, OmiseException {
		return (Transactions)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Transactions.class);
	}
	
	public static Transaction retrieve(String id) throws IOException, OmiseException {
		return (Transaction)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Transaction.class);
	}
}
