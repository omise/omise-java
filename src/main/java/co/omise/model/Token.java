package co.omise.model;

import co.omise.exception.*;
import co.omise.net.APIResource;

import java.io.IOException;
import java.util.Map;

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

	/**
	 * @param params Cannot be {@code null} or an empty HashMap.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Token create(Map<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Token)request(OmiseURL.VAULT, ENDPOINT, RequestMethod.POST, params, Token.class);
	}

	/**
	 * @param id Cannot be {@code null}.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Token retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Token)request(OmiseURL.VAULT, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Token.class);
	}
}
