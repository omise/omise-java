package co.omise.model;

import co.omise.net.APIResource;

public class BankAccount extends APIResource {
	protected String object = null;
	protected String brand = null;
	protected String last_digits = null;
	protected String name = null;
	protected String created = null;
	
	public String getObject() {
		return object;
	}
	public String getBrand() {
		return brand;
	}
	public String getLast_digits() {
		return last_digits;
	}
	public String getName() {
		return name;
	}
	public String getCreated() {
		return created;
	}
}
