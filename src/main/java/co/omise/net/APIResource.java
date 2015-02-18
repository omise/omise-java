package main.java.co.omise.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import main.java.co.omise.Omise;
import main.java.co.omise.exeption.OmiseAPIException;
import main.java.co.omise.exeption.OmiseException;
import main.java.co.omise.model.OmiseError;
import main.java.co.omise.model.OmiseObject;

import org.apache.commons.codec.binary.Base64;

public class APIResource extends OmiseObject {
	private static final String CHARSET = "UTF-8";
	private static final int CONNECT_TIMEOUT = 10 * 1000;
	private static final int READ_TIMEOUT = 10 * 1000;
	protected enum RequestMethod {
		GET, POST, PATCH, DELETE
	}
	protected enum OmiseURL {
		API {
			@Override
			public String toString() {
				return "https://api.omise.co/";
			}
		},
		VAULT {
			@Override
			public String toString() {
				return "https://vault.omise.co/";
			}
		}
	}
	
	/**
	 * 
	 * @param omiseUrl
	 * @param endPoint
	 * @param method
	 * @param params
	 * @param clazz
	 * @return
	 * @throws IOException
	 * @throws OmiseException
	 */
	protected static APIResource request(OmiseURL omiseUrl, String endPoint, RequestMethod method, Map<String, Object> params, Class<?> clazz) throws IOException, OmiseException {
		HttpURLConnection con = createConnection(omiseUrl, endPoint, method);
		// POSTパラメータがある場合送信
		if(params != null) {
			StringBuilder sb = new StringBuilder();
			for(Map.Entry<String, Object> e : params.entrySet()) {
				sb.append(URLEncoder.encode(e.getKey(), CHARSET)).
					append("=").
					append(URLEncoder.encode(e.getValue().toString(), CHARSET)).
					append("&");
			}
			sb.deleteCharAt(sb.lastIndexOf("&"));
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(con.getOutputStream());
				pw.print(sb.toString());
				pw.close();
			} finally {
				if(pw != null) pw.close();
			}
		}
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			// レスポンスコードが400番以降ならerrorオブジェクトが帰ってきていると決め打ちでExceptionを発生させる
			if(con.getResponseCode() >= 400) {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				
				String buf;
				while((buf = br.readLine()) != null) {
					sb.append(buf);
				}
				
				OmiseError omiseError = null;
				omiseError = (OmiseError)GSON.fromJson(sb.toString(), OmiseError.class);
				throw new OmiseAPIException(omiseError.getMessage(), omiseError);
			} else {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String buf;
				while((buf = br.readLine()) != null) {
					sb.append(buf);
				}
			}
		} finally {
			if(br != null) br.close();
			con.disconnect();
		}
		
		return (APIResource)GSON.fromJson(sb.toString(), clazz);
	}
	
	/**
	 * OmiseAPIへのHttpUrlConnectionの接続情報を設定していく
	 * @param omiseUrl
	 * @param endPoint
	 * @param method
	 * @param params
	 * @return
	 * @throws OmiseException 
	 * @throws IOException 
	 */
	private static HttpURLConnection createConnection(OmiseURL omiseUrl, String endPoint, RequestMethod method) throws IOException, OmiseException {
		HttpURLConnection con =  createOmiseConnection(omiseUrl, endPoint);
		con.setRequestMethod(method.name());
		con.setRequestProperty("User-Agent", "OmiseJava/" + Omise.OMISE_JAVA_LIB_VERSION + " OmiseAPI/" + Omise.OMISE_API_VERSION);
		
		return con;
	}
	
	/**
	 * OmiseAPIへのHttpUrlConnectionを生成する
	 * @param omiseUrl
	 * @param endPoint
	 * @return
	 * @throws IOException 
	 * @throws OmiseException 
	 */
	private static HttpURLConnection createOmiseConnection(OmiseURL omiseUrl, String endPoint) throws IOException, OmiseException {
		HttpURLConnection con = (HttpURLConnection)(new URL(omiseUrl.toString() + endPoint)).openConnection();
		String auth = "";
		switch (omiseUrl) {
		case API:
			auth = Omise.getSecretKey() + ":";
			break;
		case VAULT:
			auth = Omise.getPublicKey() + ":";
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
