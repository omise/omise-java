package co.omise.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseKeyUnsetException;
import co.omise.exception.OmiseUnknownException;

public class Refunds extends OmiseList {
	protected List<Refund> data = null;
	protected String charge_id = null;

	public List<Refund> getData() {
		return data;
	}

	/**
	 * @param params Cannot be {@code null} or an empty HashMap.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Refund create(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (Refund)request(OmiseURL.API, String.format("%s/%s/%s", Charge.ENDPOINT, charge_id, Refund.ENDPOINT), RequestMethod.POST, params, Refund.class);
	}

	/**
	 * @param id Cannot be {@code null} or an empty HashMap.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Refund retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (Refund)request(OmiseURL.API, String.format("%s/%s/%s/%s", Charge.ENDPOINT, charge_id, Refund.ENDPOINT, id), RequestMethod.GET, null, Refund.class);
	}
}
