package main.java.co.omise.model;

import java.io.IOException;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseKeyUnsetException;
import main.java.co.omise.exeption.OmiseUnknownException;
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

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Account retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return updateMyself((Account)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Account.class));
	}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Account reload() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return retrieve();
	}

	/**
	 * Return an account passed to account parameter. In order to make the class a singleton, an account should be passed to this method when it is re-created.
	 * @param account Cannot be {@code null}.
	 * @return Account
	 */
	private static Account updateMyself(Account account) {
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
