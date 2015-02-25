package main.java.co.omise.model;

import java.io.IOException;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Account extends APIResource {
	protected static final String ENDPOINT = "account";
	
	protected String object = null;
	protected String id = null;
	protected String email = null;
	protected String created = null;
	
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
	public String getCreated() {
		return created;
	}

	public static Account retrieve() throws IOException, OmiseException {
		return updateMyself((Account)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Account.class));
	}
	
	public Account reload() throws IOException, OmiseException{
		return retrieve();
	}
	
	public static Account updateMyself(Account account) {
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
