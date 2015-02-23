package main.java.co.omise.model;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Charge extends APIResource {
	protected static final String ENDPOINT = "charges";

	protected String object = null;
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
	@Deprecated
	protected String return_uri = null;
	@Deprecated
	protected String reference = null;
	@Deprecated
	protected String authorize_uri = null;
	protected Card card = null;
	protected String customer = null;
	protected String ip = null;
	protected Date created = null;
	
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
	public String getFailureCode() {
		return failure_code;
	}
	public String getFailureMessage() {
		return failure_message;
	}
	@Deprecated
	public String getReturnUri() {
		return return_uri;
	}
	@Deprecated
	public String getReference() {
		return reference;
	}
	@Deprecated
	public String getAuthorizeUri() {
		return authorize_uri;
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
	
	public static Charges retrieve() throws IOException, OmiseException {
		return (Charges)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Charges.class);
	}
	
	public static Charge retrieve(String id) throws IOException, OmiseException {
		return (Charge)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Charge.class);
	}
	
	public static Charge create(HashMap<String, Object> params) throws IOException, OmiseException {
		return (Charge)request(OmiseURL.API, ENDPOINT, RequestMethod.POST, params, Charge.class);
	}
	
	public Charge update(HashMap<String, Object> params) throws IOException, OmiseException {
		return updateMyself((Charge)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.PATCH, params, Charge.class));
	}
	
	public Charge capture() throws IOException, OmiseException {
		return updateMyself((Charge)request(OmiseURL.API, String.format("%s/%s/capture", ENDPOINT, id), RequestMethod.POST, null, Charge.class));
	}
	
	private Charge updateMyself(Charge charge) {
		this.object = charge.getObject();
		this.id = charge.getId();
		this.livemode = charge.getLivemode();
		this.location = charge.getLocation();
		this.amount = charge.getAmount();
		this.currency = charge.getCurrency();
		this.description = charge.getDescription();
		this.capture = charge.getCapture();
		this.authorized = charge.getAuthorized();
		this.captured = charge.getCaptured();
		this.transaction = charge.getTransaction();
		this.return_uri = charge.getReturnUri();
		this.reference = charge.getReference();
		this.authorize_uri = charge.getAuthorizeUri();
		this.card = charge.getCard();
		this.customer = charge.getCustomer();
		this.ip = charge.getIp();
		this.created = charge.getCreated();
		
		return this;
	}
}
