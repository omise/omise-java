package main.java.co.omise.model;

import java.io.IOException;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Balance extends APIResource {
	private static final String ENDPOINT = "balance";
	
	protected String object = null;
	protected Boolean livemode = null;
	protected Integer available = null;
	protected Integer total = null;
	protected String currency = null;
	
	private static Balance _balance = null;

	public String getObject() {
		return object;
	}
	public Boolean getLivemode() {
		return livemode;
	}
	public Integer getAvailable() {
		return available;
	}
	public Integer getTotal() {
		return total;
	}
	public String getCurrency() {
		return currency;
	}
	
	public static Balance retrieve() throws IOException, OmiseException {
		Balance balance = (Balance)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Balance.class);
		if(Balance._balance != null) {
			Balance._balance.object = balance.getObject();
			Balance._balance.available = balance.getAvailable();
			Balance._balance.currency = balance.getCurrency();
			Balance._balance.livemode = balance.getLivemode();
			Balance._balance.total = balance.getTotal();
		} else {
			Balance._balance = balance;
		}
		
		return Balance._balance;
	}
}
