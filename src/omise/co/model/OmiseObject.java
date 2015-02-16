package omise.co.model;

import com.google.gson.Gson;

public abstract class OmiseObject {
	@Override
	public String toString() {
		Gson gson = new Gson();
		
		return gson.toJson(this);
	}
}
