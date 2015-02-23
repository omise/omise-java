package main.java.co.omise.model;

import java.util.Date;

public class Card {
	private static final String ENDPOINT = "card";
	
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
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public String getFinancing() {
		return financing;
	}
	public String getLast_digits() {
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
	public Date getCreated() {
		return created;
	}
	
	public Card reload() {
		
		
		return this;
	}
}
