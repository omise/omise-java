package main.java.co.omise.model;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Card extends APIResource {
	protected static final String ENDPOINT = "card";
	
	protected String customer_id = null;
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected String country = null;
	protected String city = null;
	protected String postal_code = null;
	protected String financing = null;
	protected String last_digits = null;
	protected String brand = null;
	protected Integer expiration_month = null;
	protected Integer expiration_year = null;
	protected String fingerprint = null;
	protected String name = null;
	protected String created = null;
	
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
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public String getPostalCode() {
		return postal_code;
	}
	public String getFinancing() {
		return financing;
	}
	public String getLastDigits() {
		return last_digits;
	}
	public String getBrand() {
		return brand;
	}
	public Integer getExpiration_month() {
		return expiration_month;
	}
	public Integer getExpiration_year() {
		return expiration_year;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public String getName() {
		return name;
	}
	public String getCreated() {
		return created;
	}
	
	public Card reload() throws IOException, OmiseException {
		return updateMyself((Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.GET, null, Card.class));
	}
	
	public Card update(HashMap<String, Object> params) throws IOException, OmiseException {
		return updateMyself((Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.PATCH, params, Card.class));
	}
	
	public DeleteCard destroy() throws IOException, OmiseException {
		return (DeleteCard)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.DELETE, null, DeleteCard.class);
	}
	
	private Card updateMyself(Card card) {
		this.object = card.getObject();
		this.id = card.getId();
		this.livemode = card.getLivemode();
		this.location = card.getLocation();
		this.country = card.getCountry();
		this.city = card.city;
		this.postal_code = card.getPostalCode();
		this.financing = card.getFinancing();
		this.last_digits = card.getLastDigits();
		this.brand = card.getBrand();
		this.expiration_month = card.getExpiration_month();
		this.expiration_year = card.getExpiration_year();
		this.fingerprint = card.getFingerprint();
		this.name = card.getName();
		this.created = card.getCreated();
		
		return this;
	}
}
