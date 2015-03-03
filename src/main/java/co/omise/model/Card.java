package main.java.co.omise.model;

import java.io.IOException;
import java.util.HashMap;

import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseKeyUnsetException;
import main.java.co.omise.exeption.OmiseUnknownException;
import main.java.co.omise.net.APIResource;

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
	
	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Card reload() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return updateMyself((Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.GET, null, Card.class));
	}
	
	/**
	 * @param params nullまたは0要素のHashMapを渡してはならない
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public Card update(HashMap<String, Object> params) throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return updateMyself((Card)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.PATCH, params, Card.class));
	}
	
	/**
	 * @return
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 * @throws IOException
	 */
	public DeleteCard destroy() throws OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, IOException {
		return (DeleteCard)request(OmiseURL.API, String.format("%s/%s/%s/%s", Customer.ENDPOINT, customer_id, ENDPOINT, id), RequestMethod.DELETE, null, DeleteCard.class);
	}

	/**
	 * 更新系の処理の場合に必ず呼び、自分自身のインスタンスの内容を最新に更新する。
	 * @param card {@code null}を渡してはいけない
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
		
		return this;
	}
}
