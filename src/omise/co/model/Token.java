package omise.co.model;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Token extends APIResource {
	private static final String ENDPOINT = "tokens";
	
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected Boolean used = null;
	protected Card card = null;
	protected Date created = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public boolean isLivemode() {
		return livemode;
	}
	public String getLocation() {
		return location;
	}
	public boolean isUsed() {
		return used;
	}
	public Card getCard() {
		return card;
	}
	public Date getCreated() {
		return created;
	}
	
	public static Token create(Map<String, Object> params) throws IOException, OmiseException {
		return (Token)request(OmiseURL.VAULT, ENDPOINT, RequestMethod.POST, params, Token.class);
	}
}
