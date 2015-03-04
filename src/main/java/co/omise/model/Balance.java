package co.omise.model;

import java.io.IOException;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseKeyUnsetException;
import co.omise.exception.OmiseUnknownException;
import co.omise.net.APIResource;

public class Balance extends APIResource {
	protected static final String ENDPOINT = "balance";

	protected String object = null;
	protected Boolean livemode = null;
	protected Integer available = null;
	protected Integer total = null;
	protected String currency = null;

	private static Balance _balance = null;

	public String getObject() {
		return object;
	}
	public Boolean getLivemode() {
		return livemode;
	}
	public Integer getAvailable() {
		return available;
	}
	public Integer getTotal() {
		return total;
	}
	public String getCurrency() {
		return currency;
	}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Balance retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return updateMyself((Balance)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Balance.class));
	}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Balance reload() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return retrieve();
	}

	/**
	 * Return a balance passed to balance parameter. In order to make the class a singleton, a balance should be passed to this method when it is re-created.
	 * @param balance Cannot be {@code null}.
	 * @return Balance
	 */
	public static Balance updateMyself(Balance balance) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		if(Balance._balance != null) {
			Balance._balance.object = balance.getObject();
			Balance._balance.available = balance.getAvailable();
			Balance._balance.currency = balance.getCurrency();
			Balance._balance.livemode = balance.getLivemode();
			Balance._balance.total = balance.getTotal();
		} else {
			Balance._balance = balance;
		}

		return Balance._balance;
	}
}
