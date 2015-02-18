package omise.co.model;

import java.io.IOException;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Balance extends APIResource {
	private static final String ENDPOINT = "balance";
	
	private String object = null;
	private boolean livemode = false;
	private int available = 0;
	private int total = 0;
	private String currency = null;
	
	private static Balance _balance = null;

	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public boolean isLivemode() {
		return livemode;
	}
	public boolean getLivemode() {
		return livemode;
	}
	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public static Balance retrieve() throws IOException, OmiseException {
		Balance balance = (Balance)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Balance.class);
		if(Balance._balance != null) {
			Balance._balance.setObject(balance.getObject());
			Balance._balance.setAvailable(balance.getAvailable());
			Balance._balance.setCurrency(balance.getCurrency());
			Balance._balance.setLivemode(balance.getLivemode());
			Balance._balance.setTotal(balance.getTotal());
		} else {
			Balance._balance = balance;
		}
		
		return Balance._balance;
	}
}
