package main.java.co.omise.model;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseKeyUnsetException;
import main.java.co.omise.exeption.OmiseUnknownException;
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
	protected String created = null;
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
	public String getDefaultCard() {
		return default_card;
	}
	public String getEmail() {
		return email;
	}
	public String getDescription() {
		return description;
	}
	public String getCreated() {
		return created;
	}
	public Cards getCards() {
		return cards;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	
	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Customers retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return setCustomerID((Customers)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Customers.class));
	}
	
	/**
	 * @param id {@code null}を渡してはならない
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Customer retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return setCustomerID((Customer)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Customer.class));
	}
	
	/**
	 * @param params {@code null}または0要素のHashMapを渡してはならない
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Customer create(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return setCustomerID((Customer)request(OmiseURL.API, ENDPOINT, RequestMethod.POST, params, Customer.class));
	}
	
	/**
	 * @param params {@code null}または0要素のHashMapを渡してはならない
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Customer update(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		Customer customer = (Customer)request(OmiseURL.API, ENDPOINT + "/" + this.getId(), RequestMethod.PATCH, params, Customer.class);
		this.object = customer.getObject();
		this.id = customer.getId();
		this.livemode = customer.getLivemode();
		this.location = customer.getLocation();
		this.default_card = customer.getDefaultCard();
		this.email = customer.getEmail();
		this.description = customer.getDescription();
		this.created = customer.getCreated();
		this.cards = customer.getCards();
		return setCustomerID(this);
	}
	
	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public DeleteCustomer destroy() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		DeleteCustomer deleteCustomer = (DeleteCustomer)request(OmiseURL.API, ENDPOINT + "/" + this.getId(), RequestMethod.DELETE, null, DeleteCustomer.class);
		this.deleted = deleteCustomer.getDeleted();
		this.livemode = deleteCustomer.getLivemode();
		
		return deleteCustomer;
	}
	
	/**
	 * Customersを生成するタイミングで、data(個々のCustomer.cards)の内容にcustomer idを追加する
	 * @param customers
	 * @return
	 */
	private static Customers setCustomerID(Customers customers) {
		for(Customer customer : customers.data) {
			setCustomerID(customer);
		}
		
		return customers;
	}
	
	/**
	 * Customerを生成するタイミングで、cardsの内容にcustomer idを追加する
	 * @param customer
	 * @return
	 */
	private static Customer setCustomerID(Customer customer) {
		customer.cards.setCustomerID(customer.id);
		
		return customer;
	}
}
