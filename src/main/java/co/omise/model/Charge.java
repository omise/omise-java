package co.omise.model;

import co.omise.exception.*;
import co.omise.net.APIResource;

import java.io.IOException;
import java.util.HashMap;

public class Charge extends APIResource {
	protected static final String ENDPOINT = "charges";

	protected String object = null;
	protected String id = null;
	protected String livemode = null;
	protected String location = null;
	protected Integer amount = null;
	protected String currency = null;
	protected String description = null;
	protected Boolean capture = null;
	protected Boolean authorized = null;
	protected Boolean captured = null;
	protected Boolean paid = null;
	protected String transaction = null;
	protected String failure_code = null;
	protected String failure_message = null;
	@Deprecated
	protected String return_uri = null;
	@Deprecated
	protected String reference = null;
	@Deprecated
	protected String authorize_uri = null;
	protected Card card = null;
	protected String customer = null;
	protected String ip = null;
	protected String created = null;
	protected String status = null;


	public enum ChargeStatus {
		SUCCESS {
			@Override
			public String toString() {
				return "success";
			}
		},
		PENDING {
			@Override
			public String toString() {
				return "pending";
			}
		},
		FAILURE {
			@Override
			public String toString() {
				return "failure";
			}
		}
	}

	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public String getLivemode() {
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
	public String getDescription() {
		return description;
	}
	public Boolean getCapture() {
		return capture;
	}
	public Boolean getAuthorized() {
		return authorized;
	}
	public Boolean getPaid() {
		return paid;
	}
	public String getTransaction() {
		return transaction;
	}
	public String getFailureCode() {
		return failure_code;
	}
	public String getFailureMessage() {
		return failure_message;
	}
	@Deprecated
	public String getReturnUri() {
		return return_uri;
	}
	@Deprecated
	public String getReference() {
		return reference;
	}
	@Deprecated
	public String getAuthorizeUri() {
		return authorize_uri;
	}
	public Card getCard() {
		return card;
	}
	public String getCustomer() {
		return customer;
	}
	public String getIp() {
		return ip;
	}
	public String getCreated() {
		return created;
	}
	public String getStatus() {return status;}
	public Boolean getCaptured() {return captured;}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public static Charges retrieve() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Charges)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Charges.class);
	}

	/**
	 * @param id Cannot be {@code null}.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public static Charge retrieve(String id) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Charge)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.GET, null, Charge.class);
	}

	/**
	 * @param params Cannot be {@code null} or an empty HashMap.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public static Charge create(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return (Charge)request(OmiseURL.API, ENDPOINT, RequestMethod.POST, params, Charge.class);
	}

	/**
	 * @param params Cannot be {@code null} or an empty HashMap.
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public Charge update(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return updateMyself((Charge)request(OmiseURL.API, String.format("%s/%s", ENDPOINT, id), RequestMethod.PATCH, params, Charge.class));
	}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public Charge capture() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		return updateMyself((Charge)request(OmiseURL.API, String.format("%s/%s/capture", ENDPOINT, id), RequestMethod.POST, null, Charge.class));
	}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public Refunds refunds() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException, IOException {
		Refunds refunds = (Refunds)request(OmiseURL.API, String.format("%s/%s/%s", ENDPOINT, id, Refund.ENDPOINT), RequestMethod.GET, null, Refunds.class);
		refunds.charge_id = id;

		return refunds;
	}

	/**
	 * Update attributes of this instance with the given charge in parameter. Should be called when the charge is updated.
	 * @param charge
	 * @return
	 */
	private Charge updateMyself(Charge charge) {
		this.object = charge.getObject();
		this.id = charge.getId();
		this.livemode = charge.getLivemode();
		this.location = charge.getLocation();
		this.amount = charge.getAmount();
		this.currency = charge.getCurrency();
		this.description = charge.getDescription();
		this.capture = charge.getCapture();
		this.authorized = charge.getAuthorized();
		this.paid = charge.getPaid();
		this.captured = charge.getCaptured();
		this.transaction = charge.getTransaction();
		this.return_uri = charge.getReturnUri();
		this.reference = charge.getReference();
		this.authorize_uri = charge.getAuthorizeUri();
		this.card = charge.getCard();
		this.customer = charge.getCustomer();
		this.ip = charge.getIp();
		this.created = charge.getCreated();
		this.status = charge.getStatus();

		return this;
	}
}
