package co.omise.model;

import java.util.List;

public class Transactions extends OmiseList {
	protected List<Transaction> data = null;

	public List<Transaction> getData() {
		return data;
	}
}
