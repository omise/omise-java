package main.java.co.omise.model;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Customer extends APIResource {
	protected static final String ENDPOINT = "customers";
	
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected String default_card = null;
	protected String email = null;
	protected String description = null;
	protected Date created = null;
	protected Cards cards = null;
	protected Boolean deleted = null;
	
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
	public Boolean getDeleted() {
		return deleted;
	}
	
	public static Customers retrieve() throws IOException, OmiseException {
		return (Customers)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Customers.class);
	}
	
	public static Customer retrieve(String id) throws IOException, OmiseException {
		return (Customer)request(OmiseURL.API, ENDPOINT + "/" + id, RequestMethod.GET, null, Customer.class);
	}
	
	public static Customer create(HashMap<String, Object> params) throws IOException, OmiseException {
		return (Customer)request(OmiseURL.API, ENDPOINT, RequestMethod.POST, params, Customer.class);
	}
	
	public Customer update(HashMap<String, Object> params) throws IOException, OmiseException {
		Customer customer = (Customer)request(OmiseURL.API, ENDPOINT + "/" + this.getId(), RequestMethod.PATCH, params, Customer.class);
		this.object = customer.getObject();
		this.id = customer.getId();
		this.livemode = customer.getLivemode();
		this.location = customer.getLocation();
		this.default_card = customer.getDefault_card();
		this.email = customer.getEmail();
		this.description = customer.getDescription();
		this.created = customer.getCreated();
		this.cards = customer.getCards();
		
		return this;
	}
	
	public DeleteCustomer destroy() throws IOException, OmiseException {
		DeleteCustomer deleteCustomer = (DeleteCustomer)request(OmiseURL.API, ENDPOINT + "/" + this.getId(), RequestMethod.DELETE, null, DeleteCustomer.class);
		this.deleted = deleteCustomer.getDeleted();
		this.livemode = deleteCustomer.getLivemode();
		
		return deleteCustomer;
	}
}
