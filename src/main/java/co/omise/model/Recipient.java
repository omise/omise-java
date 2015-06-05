package co.omise.model;

import java.io.IOException;
import java.util.HashMap;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseKeyUnsetException;
import co.omise.exception.OmiseUnknownException;
import co.omise.net.APIResource;

public class Recipient extends APIResource {
	protected static final String ENDPOINT = "recipients";
	
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected Boolean verified = null;
	protected Boolean active = null;
	protected String name = null;
	protected String email = null;
	protected String description = null;
	protected String type = null;
	protected String tax_id = null;
	protected BankAccount bank_account = null;
	protected String failure_code = null;
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
	public Boolean getVerified() {
		return verified;
	}
	public Boolean getActive() {
		return active;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
	public String getTax_id() {
		return tax_id;
	}
	public BankAccount getBank_account() {
		return bank_account;
	}
	public String getFailure_code() {
		return failure_code;
	}
	public String getCreated() {
		return created;
	}
	
	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Recipients retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (Recipients)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Recipients.class);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Recipient retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (Recipient)request(OmiseURL.API, String.format("%s/%s",  ENDPOINT, id), RequestMethod.GET, null, Recipient.class);
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Recipient create(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (Recipient)request(OmiseURL.API, ENDPOINT, RequestMethod.POST, params, Recipient.class);
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Recipient update(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return updateMyself((Recipient)request(OmiseURL.API, String.format("%s/%s",  ENDPOINT, getId()), RequestMethod.PATCH, params, Recipient.class));
	}
	
	/**
	 * 
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public DeleteRecipient destroy() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (DeleteRecipient)request(OmiseURL.API, String.format("%s/%s",  ENDPOINT, getId()), RequestMethod.DELETE, null, DeleteRecipient.class);
	}
	
	private Recipient updateMyself(Recipient recipient){
		this.object = recipient.getObject();
		this.id = recipient.getId();
		this.livemode = recipient.getLivemode();
		this.location = recipient.getLocation();
		this.verified = recipient.getVerified();
		this.active = recipient.getActive();
		this.name = recipient.getName();
		this.email = recipient.getEmail();
		this.description = recipient.getDescription();
		this.type = recipient.getType();
		this.tax_id = recipient.getTax_id();
		this.bank_account.object = recipient.getBank_account().getObject();
		this.bank_account.brand = recipient.getBank_account().getBrand();
		this.bank_account.last_digits = recipient.getBank_account().getLast_digits();
		this.bank_account.name = recipient.getBank_account().getName();
		this.bank_account.created = recipient.getBank_account().getCreated();
		this.failure_code = recipient.getFailure_code();
		this.created = recipient.getCreated();
		
		return this;
	}
}
