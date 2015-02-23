package main.java.co.omise.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import main.java.co.omise.exeption.OmiseException;

public class Refunds extends OmiseList {
	protected List<Refund> data = null;
	protected String charge_id = null;
	
	public List<Refund> getData() {
		return data;
	}
	
	public Refund create(HashMap<String, Object> params) throws IOException, OmiseException {
		return (Refund)request(OmiseURL.API, String.format("%s/%s/%s", Charge.ENDPOINT, charge_id, Refund.ENDPOINT), RequestMethod.POST, params, Refund.class);
	}
}
