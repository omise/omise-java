package co.omise.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class OmiseObject {
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final Gson GSON = new GsonBuilder().
			setDateFormat(DATE_FORMAT).
			setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
			create();
	
	@Override
	public String toString() {
		return GSON.toJson(this);
	}
}
