package omise.co.model;

import omise.co.net.APIResource;

public abstract class OmiseList extends APIResource {
	protected String object = null;
	protected String from = null;
	protected String to = null;
	protected Integer offset = null;
	protected Integer limit = null;
	protected Integer total = null;
	
	public String getObject() {
		return object;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public Integer getOffset() {
		return offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public Integer getTotal() {
		return total;
	}
}
