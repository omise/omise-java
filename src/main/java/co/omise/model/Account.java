package main.java.co.omise.model;

import java.io.IOException;
import java.util.Date;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Account extends APIResource {
	private static final String ENDPOINT = "account";
	
	protected String object = null;
	protected String id = null;
	protected String email = null;
	protected Date created = null;
	
	private static Account _account = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public Date getCreated() {
		return created;
	}

	public static Account retrieve() throws IOException, OmiseException {
		Account account = (Account)requestGet(OmiseURL.API, ENDPOINT, Account.class);
		//Account account = (Account)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Account.class);
		if(Account._account != null) {
			Account._account.created = account.getCreated();
			Account._account.email = account.getEmail();
			Account._account.id = account.getId();
			Account._account.object = account.getObject();
		} else {
			Account._account = account;
		}
		
		return Account._account;
	}
}
