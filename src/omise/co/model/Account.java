package omise.co.model;

import java.io.IOException;

import omise.co.exeption.OmiseException;
import omise.co.net.APIResource;

public class Account extends APIResource{
	private static String ENDPOINT = "account";
	
	public static Account retrieve() {
		try {
			return (Account)request(OmiseURL.API, ENDPOINT, RequestMethod.GET, null, Account.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OmiseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
