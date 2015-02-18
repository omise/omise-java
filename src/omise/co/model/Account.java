package omise.co.model;

import java.io.IOException;
import java.util.Date;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Account extends APIResource {
	private static final String ENDPOINT = "account";
	
	private String object = null;
	private String id = null;
	private String email = null;
	private Date created = null;
	
	private static Account _account = null;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	public static Account retrieve() throws IOException, OmiseException {
		Account account = (Account)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Account.class);
		if(Account._account != null) {
			Account._account.setCreated(account.getCreated());
			Account._account.setEmail(account.getEmail());
			Account._account.setId(account.getId());
			Account._account.setObject(account.getObject());
		} else {
			Account._account = account;
		}
		
		return Account._account;
	}
}
