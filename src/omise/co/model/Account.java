package omise.co.model;

import java.io.IOException;
import java.util.Date;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Account extends APIResource{
	private static String ENDPOINT = "account";
	
	private String object;
	private String id;
	private String email;
	private Date created;
	
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
		return (Account)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Account.class);
	}
}
