package main.java.co.omise.model;

import java.io.IOException;
import java.util.Map;

import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.net.APIResource;

public class Token extends APIResource {
	protected static final String ENDPOINT = "tokens";
	
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected Boolean used = null;
	protected Card card = null;
	protected String created = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public boolean getLivemode() {
		return livemode;
	}
	public String getLocation() {
		return location;
	}
	public boolean getUsed() {
		return used;
	}
	public Card getCard() {
		return card;
	}
	public String getCreated() {
		return created;
	}
	
	public static Token create(Map<String, Object> params) throws IOException, OmiseException {
		return (Token)request(OmiseURL.VAULT, ENDPOINT, RequestMethod.POST, params, Token.class);
	}
	
	public static Token retrieve(String id) throws IOException, OmiseException {
		return (Token)request(OmiseURL.VAULT, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Token.class);
	}
}
