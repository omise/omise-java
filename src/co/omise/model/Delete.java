package co.omise.model;

import co.omise.net.APIResource;

public class Delete extends APIResource {
	protected String object = null;
	protected String id = null;
	protected Boolean livemode = null;
	protected Boolean deleted = null;
	
	public String getObject() {
		return object;
	}
	public String getId() {
		return id;
	}
	public Boolean getLivemode() {
		return livemode;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	
	public Boolean isDestroyed() {
		return getDeleted();
	}
}
