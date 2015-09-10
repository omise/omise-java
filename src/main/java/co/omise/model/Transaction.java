package co.omise.model;

import co.omise.exception.*;
import co.omise.net.APIResource;

import java.io.IOException;

public class Transaction extends APIResource {
	protected static final String ENDPOINT = "transactions";

	protected String object = null;
	protected String id = null;
	protected String type = null;
	protected Integer amount = null;
	protected String currency = null;
	protected String created = null;

	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Integer getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
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
	public static Transactions retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Transactions)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Transactions.class);
	}

	/**
	 * @param id
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Transaction retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Transaction)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Transaction.class);
	}
}
