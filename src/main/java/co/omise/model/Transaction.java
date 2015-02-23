package main.java.co.omise.model;

import java.util.Date;

import main.java.co.omise.net.APIResource;

public class Transaction extends APIResource {
	protected String object = null;
	protected String id = null;
	protected String type = null;
	protected Integer amount = null;
	protected String currency = null;
	protected Date created = null;
	
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
	public Date getCreated() {
		return created;
	}
}
