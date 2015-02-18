package omise.co.model;

import java.io.IOException;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Balance extends APIResource {
	private static final String ENDPOINT = "balance";
	
	private String object = null;
	private Boolean livemode = null;
	private Integer available = null;
	private Integer total = null;
	private String currency = null;
	
	private static Balance _balance = null;

	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public Boolean isLivemode() {
		return livemode;
	}
	public void setLivemode(Boolean livemode) {
		this.livemode = livemode;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
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
			Balance._balance.setLivemode(balance.isLivemode());
			Balance._balance.setTotal(balance.getTotal());
		} else {
			Balance._balance = balance;
		}
		
		return Balance._balance;
	}
}
