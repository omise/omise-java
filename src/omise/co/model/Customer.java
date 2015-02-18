package omise.co.model;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Customer extends APIResource {
	private static final String ENDPOINT = "customers";
	
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected String default_card = null;
	protected String email = null;
	protected String description = null;
	protected Date created = null;
	protected Cards cards = null;
	
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
	public String getDefault_card() {
		return default_card;
	}
	public String getEmail() {
		return email;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreated() {
		return created;
	}
	public Cards getCards() {
		return cards;
	}
	
	public static Customers retrieve() throws IOException, OmiseException {
		return (Customers)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Customers.class);
	}
	
	public static Customer create(HashMap<String, Object> params) throws IOException, OmiseException {
		return (Customer)request(OmiseURL.API, ENDPOINT, RequestMethod.POST, params, Customer.class);
	}
}
