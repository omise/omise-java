package omise.co.model;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Token extends APIResource {
	private static final String ENDPOINT = "tokens";
	
	private String object = null;
	private String id = null;
	private boolean livemode = false;
	private String location = null;
	private boolean used = false;
	private Card card = null;
	private Date created = null;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isLivemode() {
		return livemode;
	}
	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public static Token create(Map<String, Object> params) throws IOException, OmiseException {
		return (Token)request(OmiseURL.VAULT, ENDPOINT, RequestMethod.POST, params, Token.class);
	}
}
