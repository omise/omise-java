package main.java.co.omise.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseKeyUnsetException;
import main.java.co.omise.exeption.OmiseUnknownException;

public class Refunds extends OmiseList {
	protected List<Refund> data = null;
	protected String charge_id = null;
	
	public List<Refund> getData() {
		return data;
	}
	
	/**
	 * @param params {@code null}または0要素のHashMapを渡してはならない
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
	 * @param id {@code null}を渡してはならない
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
