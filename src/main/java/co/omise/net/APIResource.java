package co.omise.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import co.omise.Omise;
import co.omise.exception.OmiseAPIException;
import co.omise.exception.OmiseKeyUnsetException;
import co.omise.exception.OmiseUnknownException;
import co.omise.model.OmiseError;
import co.omise.model.OmiseObject;

import org.apache.commons.codec.binary.Base64;

public class APIResource extends OmiseObject {
	private static final String CHARSET = "UTF-8";
	private static int CONNECT_TIMEOUT = 10 * 1000;
	private static int READ_TIMEOUT = 60 * 1000;
	
	public static int getConnectTimeout() {
		return CONNECT_TIMEOUT;
	}
	public static void setConnectTimeout(int connectTimeout) {
		CONNECT_TIMEOUT = connectTimeout;
	}
	public static int getReadTimeout() {
		return READ_TIMEOUT;
	}
	public static void setReadTimeout(int readTimeout) {
		READ_TIMEOUT = readTimeout;
	}

	protected enum RequestMethod {
		GET,
		POST,
		DELETE,
		PATCH {
			@Override
			public String toString() {
				return RequestMethod.POST.name();
			}
		}
	}
	protected enum OmiseURL {
		API {
			@Override
			public String toString() {
				switch (Omise.getMode()) {
				case Omise.MODE_RELEASE:
					return "https://api.omise.co/";
					
				case Omise.MODE_STAGING:
					System.out.println("*** staging(debug) mode ***");
					return "https://api-staging.omise.co/";

				default:
					return null;
				}
			}
		},
		VAULT {
			@Override
			public String toString() {
				switch (Omise.getMode()) {
				case Omise.MODE_RELEASE:
					return "https://vault.omise.co/";
					
				case Omise.MODE_STAGING:
					System.out.println("*** staging(debug) mode ***");
					return "https://vault-staging.omise.co/";

				default:
					return null;
				}
			}
		}
	}

	/**
	 *
	 * @param omiseUrl An FQDN of API endpoint, including the protocol, the domain and the trailing slash. The values are declared in OmiseURL.
	 * @param endPoint A path of API endpoint.
	 * @param method A HTTP method to call the API, such as GET or POST. The values are declared in RequestMethod.
	 * @param params A request body to POST or PATCH. Can be {@code null} when the request has no body.
	 * @param clazz A class to return.
	 * @return
	 * @throws IOException
	 * @throws OmiseAPIException
	 * @throws OmiseKeyUnsetException
	 * @throws OmiseUnknownException
	 */
	protected static APIResource request(OmiseURL omiseUrl, String endPoint, RequestMethod method, Map<String, Object> params, Class<?> clazz) throws IOException, OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException {
		HttpURLConnection con = createConnection(omiseUrl, endPoint, method);
		writeParams(con, params);

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			con.connect();
			// Throw the appropriate exception when the response code is above 400.
			if(con.getResponseCode() >= 400) {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));

				String buf;
				while((buf = br.readLine()) != null) {
					sb.append(buf);
				}

				OmiseError omiseError = null;
				omiseError = (OmiseError)GSON.fromJson(sb.toString(), OmiseError.class);
				if("error".equals(omiseError.getObject())) {
					throw new OmiseAPIException(omiseError.getMessage(), omiseError);
				} else {
					throw new OmiseUnknownException(sb.toString(), omiseError);
				}
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
	 * Encode and send params as request body.
	 * @param con
	 * @param params
	 * @throws IOException
	 */
	private static void writeParams(HttpURLConnection con, Map<String, Object> params) throws IOException {
		// Only when params is present.
		if(params != null) {
			StringBuilder sb = new StringBuilder();
			for(Map.Entry<String, Object> e : params.entrySet()) {
				if(e.getValue() instanceof Map) {
					for(Map.Entry<String, Object> e2 : ((Map<String, Object>)e.getValue()).entrySet()) {
						sb.append(URLEncoder.encode(e.getKey(), CHARSET)).
							append("[").
							append(URLEncoder.encode(e2.getKey(), CHARSET)).
							append("]").
							append("=").
							append(URLEncoder.encode(e2.getValue().toString(), CHARSET)).
							append("&");
					}
				} else {
					sb.append(URLEncoder.encode(e.getKey(), CHARSET)).
						append("=").
						append(URLEncoder.encode(e.getValue().toString(), CHARSET)).
						append("&");
				}
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
	}

	/**
	 * Make a HttpUrlConnection to Omise API.
	 * @param omiseUrl
	 * @param endPoint
	 * @param method
	 * @param params
	 * @return
	 * @throws OmiseKeyUnsetException
	 * @throws IOException
	 */
	private static HttpURLConnection createConnection(OmiseURL omiseUrl, String endPoint, RequestMethod method) throws IOException, OmiseKeyUnsetException {
		HttpURLConnection con = (HttpURLConnection)(new URL(omiseUrl.toString() + endPoint)).openConnection();

		con.setUseCaches(false);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setConnectTimeout(CONNECT_TIMEOUT);
		con.setReadTimeout(READ_TIMEOUT);
		con.setRequestMethod(method.toString());
		con.setRequestProperty("User-Agent", "OmiseJava/" + Omise.OMISE_JAVA_LIB_VERSION + " OmiseAPI/" + Omise.OMISE_API_VERSION);
		con.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64String(getBasicAuthString(omiseUrl).getBytes(CHARSET)));
		con.setRequestProperty("Keep-Alive", "close");
		if(method == RequestMethod.PATCH) {
			con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
		}

		return con;
	}

	private static String getBasicAuthString(OmiseURL omiseUrl) throws OmiseKeyUnsetException {
		switch(omiseUrl){
			case API:
				return Omise.getSecretKey() + ":";
			case VAULT:
				return Omise.getPublicKey() + ":";
			default:
				return ":";
		}
	}
}
