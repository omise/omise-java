package main.java.co.omise.model;

import java.util.Date;

import main.java.co.omise.net.APIResource;

public class Transfer extends APIResource {
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected Boolean sent = false;
	protected Boolean paid = false;
	protected Integer amount = null;
	protected String currency = null;
	protected String failure_code = null;
	protected String failure_message = null;
	protected String transaction = null;
	protected Date created = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public Boolean getLivemode() {
		return livemode;
	}
	public String getLocation() {
		return location;
	}
	public Boolean getSent() {
		return sent;
	}
	public Boolean getPaid() {
		return paid;
	}
	public Integer getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getFailure_code() {
		return failure_code;
	}
	public String getFailure_message() {
		return failure_message;
	}
	public String getTransaction() {
		return transaction;
	}
	public Date getCreated() {
		return created;
	}
}
