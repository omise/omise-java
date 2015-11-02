package co.omise.model;

import co.omise.exception.*;
import co.omise.net.APIResource;

import java.io.IOException;
import java.util.HashMap;

public class Card extends APIResource {
	protected static final String ENDPOINT = "cards";

	protected String customer_id = null;
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected String location = null;
	protected String country = null;
	protected String city = null;
	protected String postal_code = null;
	protected String financing = null;
	protected String last_digits = null;
	protected String brand = null;
	protected Integer expiration_month = null;
	protected Integer expiration_year = null;
	protected String fingerprint = null;
	protected String name = null;
	protected String created = null;
	protected String bank = null;

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
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public String getPostalCode() {
		return postal_code;
	}
	public String getFinancing() {
		return financing;
	}
	public String getLastDigits() {
		return last_digits;
	}
	public String getBrand() {
		return brand;
	}
	public Integer getExpirationMonth() {
		return expiration_month;
	}
	public Integer getExpirationYear() {
		return expiration_year;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public String getName() {
		return name;
	}
	public String getCreated() {
		return created;
	}
	public String getBank() {return bank;}

	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 * @throws OmiseAPIConnectionException
	 * @throws OmiseInvalidRequestException
	 */
	public Card reload() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException, OmiseAPIConnectionException, OmiseInvalidRequestException  {
		return updateMyself((Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.GET, null, Card.class));
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
	public Card update(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException, OmiseAPIConnectionException, OmiseInvalidRequestException  {
		return updateMyself((Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.PATCH, params, Card.class));
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
	public DeleteCard destroy() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException , IOException {
		return (DeleteCard)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.DELETE, null, DeleteCard.class);
	}

	/**
	 * Update attributes of this instance with the given card in parameter. Should be called when the card is updated.
	 * @param card Cannot be {@code null}.
	 * @return Card
	 */
	private Card updateMyself(Card card) {
		this.object = card.getObject();
		this.id = card.getId();
		this.livemode = card.getLivemode();
		this.location = card.getLocation();
		this.country = card.getCountry();
		this.city = card.city;
		this.postal_code = card.getPostalCode();
		this.financing = card.getFinancing();
		this.last_digits = card.getLastDigits();
		this.brand = card.getBrand();
		this.expiration_month = card.getExpirationMonth();
		this.expiration_year = card.getExpirationYear();
		this.fingerprint = card.getFingerprint();
		this.name = card.getName();
		this.created = card.getCreated();
		this.bank = card.getBank();

		return this;
	}
}
