package omise.co.model;

import java.util.Date;
import java.util.List;

import omise.co.net.APIResource;

public abstract class OmiseList extends APIResource {
	protected String object = null;
	protected Date from = null;
	protected Date to = null;
	protected Integer offset = null;
	protected Integer limit = null;
	protected Integer total = null;
	protected List<OmiseObject> data = null;
	
	public String getObject() {
		return object;
	}
	public Date getFrom() {
		return from;
	}
	public Date getTo() {
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
