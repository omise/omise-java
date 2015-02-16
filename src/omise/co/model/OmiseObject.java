package omise.co.model;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public abstract class OmiseObject {
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final Gson GSON = new GsonBuilder().
			setDateFormat(DATE_FORMAT).
			registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
				@Override
				public Date deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
						throws JsonParseException {
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					
					try {
						return sdf.parse(arg0.getAsString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}).
			create();
}
