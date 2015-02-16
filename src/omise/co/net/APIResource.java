package omise.co.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import omise.co.Omise;
import omise.co.Omise.OmiseURL;

public class APIResource {
	private static final String CHARSET = "UTF-8";
	private static final int CONNECT_TIMEOUT = 10 * 1000;
	private static final int READ_TIMEOUT = 10 * 1000;
	protected enum RequestMethod {
		GET, POST, PATCH, DELETE 
	}
	
	/**
	 * OmiseAPIへのHttpUrlConnectionの接続情報を設定していく
	 * @param omiseUrl
	 * @param endPoint
	 * @param method
	 * @return
	 * @throws IOException
	 */
	protected static HttpURLConnection createConnection(OmiseURL omiseUrl, String endPoint, String method) throws IOException {
		HttpURLConnection con =  createOmiseConnection(omiseUrl, endPoint);
		con.setRequestProperty("User-Agent", "OmiseJava/" + Omise.OMISE_JAVA_LIB_VERSION + " OmiseAPI/" + Omise.OMISE_API_VERSION);
		
		return null;
	}
	
	/**
	 * OmiseAPIへのHttpUrlConnectionを生成する
	 * @param omiseUrl
	 * @param endPoint
	 * @return
	 * @throws IOException
	 */
	private static HttpURLConnection createOmiseConnection(OmiseURL omiseUrl, String endPoint) throws IOException {
		HttpURLConnection con = (HttpURLConnection)(new URL(omiseUrl.toString() + endPoint)).openConnection();
		String auth = "";
		switch (omiseUrl) {
		case API:
			auth = Omise.secretKey + ":";
			break;
		case VAULT:
			auth = Omise.publicKey + ":";
			break;
		default:
			break;
		
		}
		
		con.setUseCaches(false);
		con.setDoOutput(true);
		con.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64String(auth.getBytes(CHARSET)));
		con.setConnectTimeout(CONNECT_TIMEOUT);
		con.setReadTimeout(READ_TIMEOUT);
		
		return con;
	}
}
