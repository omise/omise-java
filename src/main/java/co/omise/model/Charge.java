package main.java.co.omise.model;

import java.util.Date;

import main.java.co.omise.net.APIResource;

public class Charge extends APIResource {
	protected String ENDPOINT = "charges";

	protected String charge = null;
	protected String id = null;
	protected String livemode = null;
	protected String location = null;
	protected Integer amount = null;
	protected String currency = null;
	protected String description = null;
	protected Boolean capture = null;
	protected Boolean authorized = null;
	protected Boolean captured = null;
	protected String transaction = null;
	protected String failure_code = null;
	protected String failure_message = null;
	protected Card card = null;
	protected String customer = null;
	protected String ip = null;
	protected Date created = null;
	
	public String getENDPOINT() {
		return ENDPOINT;
	}
	public String getCharge() {
		return charge;
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
	public String getDescription() {
		return description;
	}
	public Boolean getCapture() {
		return capture;
	}
	public Boolean getAuthorized() {
		return authorized;
	}
	public Boolean getCaptured() {
		return captured;
	}
	public String getTransaction() {
		return transaction;
	}
	public String getFailure_code() {
		return failure_code;
	}
	public String getFailure_message() {
		return failure_message;
	}
	public Card getCard() {
		return card;
	}
	public String getCustomer() {
		return customer;
	}
	public String getIp() {
		return ip;
	}
	public Date getCreated() {
		return created;
	}
}
