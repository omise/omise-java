package co.omise.model;

import co.omise.net.APIResource;

public class Refund extends APIResource {
	protected static final String ENDPOINT = "refunds";

	protected String object = null;
	protected String id = null;
	protected String livemode = null;
	protected String location = null;
	protected Integer amount = null;
	protected String currency = null;
	protected String charge = null;
	protected String created = null;

	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public String getLivemode() {
		return livemode;
	}
	public String getLocation() {
		return location;
	}
	public Integer getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getCharge() {
		return charge;
	}
	public String getCreated() {
		return created;
	}
}
