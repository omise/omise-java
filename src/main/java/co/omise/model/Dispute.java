package co.omise.model;

import co.omise.exception.*;
import co.omise.net.APIResource;

import java.io.IOException;
import java.util.HashMap;

public class Dispute extends APIResource {
	protected static final String ENDPOINT = "disputes";
	
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected Integer amount = null;
	protected String currency = null;
	protected String status = null;
	protected String message = null;
	protected String charge = null;
	protected String created = null;

	public enum DisputeStatus {
		OPEN {
			@Override
			public String toString() {
				return "open";
			}
		}, 
		PENDING {
			@Override
			public String toString() {
				return "pending";
			}
		}, 
		CLOSED {
			@Override
			public String toString() {
				return "closed";
			}
		}
	}
	

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
	public Integer getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public String getCharge() {
		return charge;
	}
	public String getCreated() {
		return created;
	}

	/**
	 * 
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Disputes retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Disputes)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Disputes.class);
	}

	/**
	 * 
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Disputes retrieve(DisputeStatus status) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Disputes)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, status.toString()), RequestMethod.GET, null, Disputes.class);
	}
	
	/**
	 * @param id Cannot be {@code null}.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public static Dispute retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Dispute)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Dispute.class);
	}
	
	/**
	 * @param params Cannot be {@code null} or an empty HashMap.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Dispute update(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		Dispute dispute = (Dispute)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, getId()), RequestMethod.PATCH, params, Dispute.class);
		this.object = dispute.getObject();
		this.id = dispute.getId();
		this.livemode = dispute.getLivemode();
		this.location = dispute.getLocation();
		this.amount = dispute.getAmount();
		this.currency = dispute.getCurrency();
		this.status = dispute.getStatus();
		this.message = dispute.getMessage();
		this.charge = dispute.getCharge();
		this.created = dispute.getCreated();
		return this;
	}
}
