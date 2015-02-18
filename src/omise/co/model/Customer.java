package omise.co.model;

import java.util.Date;
import java.util.List;

import omise.co.net.APIResource;

public class Customer extends APIResource {
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected String default_card = null;
	protected String email = null;
	protected String description = null;
	protected Date created = null;
	protected List<Card> cards = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public Boolean getLivemode() {
		return livemode;
	}
	public String getLocation() {
		return location;
	}
	public String getDefault_card() {
		return default_card;
	}
	public String getEmail() {
		return email;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreated() {
		return created;
	}
	public List<Card> getCards() {
		return cards;
	}
}
