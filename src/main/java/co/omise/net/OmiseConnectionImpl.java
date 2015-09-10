package co.omise.net;

import co.omise.Omise;
import co.omise.exception.*;
import co.omise.model.OmiseError;
import co.omise.net.APIResource.OmiseURL;
import co.omise.net.APIResource.RequestMethod;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by tin_hanc on 9/7/2015 AD.
 */
public class OmiseConnectionImpl implements OmiseConnection {

    @Override
    public  <O> O request(OmiseURL omiseUrl, String endPoint,
                                 RequestMethod method, Map<String, Object> params,
                                 Class<?> clazz) throws IOException, OmiseAPIException,
            OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException , OmiseInvalidRequestException{
        return _request(omiseUrl,endPoint,method,params,clazz);
    }

    private static <O> O _request(OmiseURL omiseUrl, String endPoint,
                                  RequestMethod method, Map<String, Object> params,
                                  Class<?> clazz) throws IOException, OmiseAPIException,
            OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException {

           OmiseResponse response;
           response = getOmiseResponse(method, omiseUrl, endPoint, params);

           int responseCode = response.getResponseCode();
           String responseBody = response.getResponseBody();

           if (responseCode < 200 || responseCode >= 300) {
               throw new OmiseAPIException("Error", APIResource.GSON.fromJson(responseBody, OmiseError.class));
           }
         return (O) APIResource.GSON.fromJson(responseBody, clazz);
    }

    private static String getResponseBody(InputStream responseStream)
            throws IOException {
        String rBody = new Scanner(responseStream, APIResource.CHARSET)
                .useDelimiter("\\A")
                .next();

        responseStream.close();
        return rBody;
    }

    private static OmiseResponse getOmiseResponse(
            APIResource.RequestMethod method, OmiseURL url, String endPoint,
            Map<String, Object> params) throws IOException, OmiseInvalidRequestException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseAPIException {
        String query;
        try {
            query = createQuery(params);
        } catch (UnsupportedEncodingException e) {
            throw new OmiseInvalidRequestException("Unable to encode parameters to "
                    + APIResource.CHARSET
                    + ". Please contact support@omise.co for assistance.",
                    null);
        }
            return createURLConnectionRequest(method, url, endPoint, query);
    }

    private static OmiseResponse createURLConnectionRequest(
            APIResource.RequestMethod method, OmiseURL url, String endPoint, String query) throws OmiseAPIException, OmiseAPIConnectionException, OmiseUnknownException, OmiseInvalidRequestException{
        java.net.HttpURLConnection conn = null;
        try {
            switch (method) {
                case GET:
                    conn = omiseGetConnection(url, endPoint, query);
                    break;
                case POST:
                    conn = omisePostOrPathConnection(method, url, endPoint, query);
                    break;
                case DELETE:
                    conn = omiseDeleteConnection(url, endPoint, query);
                    break;
                case PATCH:
                    conn = omisePostOrPathConnection(method, url, endPoint, query);
                    break;
                default:
                    throw new OmiseInvalidRequestException(
                            String.format(
                                    "Unrecognized HTTP method %s "
                                            + "Please check your HTTP method. Contact us via "
                                            + "support@omise.co for assistance.", method.name()),
                                    new OmiseError());
            }

            int responseCode = conn.getResponseCode();
            String responseBody;
            Map<String, List<String>> headers;

            if (responseCode >= 200 && responseCode < 300) {
                responseBody = getResponseBody(conn.getInputStream());
            } else {
                responseBody = getResponseBody(conn.getErrorStream());
            }
            headers = conn.getHeaderFields();
            return new OmiseResponse(responseCode, responseBody, headers);

        } catch (IOException e) {
            throw new OmiseAPIConnectionException(
                    String.format(
                            "IOException during API request to Omise (%s): %s "
                                    + "Please check your internet connection and try again. If this problem persists,"
                                    + " or let us know at support@omise.co.",
                            url.toString()+endPoint, e.getMessage()), null);
        } catch (Exception e) {
            throw new OmiseUnknownException(
                    String.format(
                            "Exception during API request to Omise (%s): %s "
                                    + "Please check your internet connection and try again. If this problem persists,"
                                    + " or let us know at support@omise.co.",
                            url.toString()+endPoint, e.getMessage()), null);
        }finally {  if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static java.net.HttpURLConnection createOmiseConnection(
             String omiseUrl)  throws OmiseKeyUnsetException, IOException {
        URL omiseURL = new URL(omiseUrl);
        HttpURLConnection conn;
            conn = (HttpURLConnection) omiseURL.openConnection();
        conn.setConnectTimeout(APIResource.CONNECT_TIMEOUT);
        conn.setReadTimeout(APIResource.READ_TIMEOUT);
        conn.setUseCaches(false);
        for (Map.Entry<String, String> header : getHeaders().entrySet()) {
            conn.setRequestProperty(header.getKey(), header.getValue());
        }

        return conn;
    }


    private static Map<String, String> getHeaders() throws OmiseKeyUnsetException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Charset", APIResource.CHARSET);
        headers.put("Accept", "application/json");
        headers.put("User-Agent", "OmiseJava/" + Omise.getOmiseLibVersion() + " OmiseAPI/" + Omise.getOmiseAPIVersion());
        headers.put("Keep-Alive", "close");

        return headers;
    }

    private static java.net.HttpURLConnection omiseGetConnection(
             OmiseURL omiseUrl, String endPoint,  String query) throws OmiseKeyUnsetException, IOException {
        String getURL = formatURL((omiseUrl.toString()) + endPoint , query);
        java.net.HttpURLConnection conn = createOmiseConnection(getURL);
        conn.setRequestMethod("GET");
        omiseSetAuthori(conn,omiseUrl);

        return conn;
    }

    private static java.net.HttpURLConnection omisePostOrPathConnection(
            RequestMethod method, OmiseURL omiseUrl,String endPoint, String query) throws OmiseKeyUnsetException, IOException {
        String postURL = omiseUrl.toString() + endPoint;
        java.net.HttpURLConnection conn = createOmiseConnection(postURL);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", String.format("application/x-www-form-urlencoded;charset=%s", APIResource.CHARSET));
        omiseSetAuthori(conn,omiseUrl);
        if(method == RequestMethod.PATCH)
        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");

        OutputStream output = null;
        try {
            output = conn.getOutputStream();
            output.write(query.getBytes(APIResource.CHARSET));
        } finally {
            if (output != null) {
                output.close();
            }
        }

        return conn;
    }

    private static java.net.HttpURLConnection omiseSetAuthori(java.net.HttpURLConnection conn, OmiseURL omiseUrl) throws OmiseKeyUnsetException{
        conn.setRequestProperty("Authorization", "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(getBasicAuthString(omiseUrl).getBytes()));
        return conn;
    }

    private static java.net.HttpURLConnection omiseDeleteConnection(
            OmiseURL omiseUrl, String endPoint,  String query) throws OmiseKeyUnsetException, IOException {
        String deleteUrl = formatURL((omiseUrl.toString()) + endPoint , query);
        java.net.HttpURLConnection conn = createOmiseConnection(deleteUrl);
        conn.setRequestMethod("DELETE");
        omiseSetAuthori(conn,omiseUrl);

        return conn;
    }

    private static String urlEncode(String k, String v)
            throws UnsupportedEncodingException {
        return String.format("%s=%s", APIResource.urlEncode(k), APIResource.urlEncode(v));
    }

    private static String createQuery(Map<String, Object> params)
            throws UnsupportedEncodingException {
        Map<String, String> flatParams = flattenParams(params);
        StringBuilder queryStringBuffer = new StringBuilder();
        for (Map.Entry<String, String> entry : flatParams.entrySet()) {
            if (queryStringBuffer.length() > 0) {
                queryStringBuffer.append("&");
            }
            queryStringBuffer.append(urlEncode(entry.getKey(),
                    entry.getValue()));
        }
        return queryStringBuffer.toString();
    }

    private static Map<String, String> flattenParams(Map<String, Object> params)
            throws UnsupportedEncodingException {
        if (params == null) {
            return new HashMap<String, String>();
        }
        Map<String, String> flatParams = new LinkedHashMap<String, String>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map<?, ?>) {
                Map<String, Object> flatNestedMap = new LinkedHashMap<String, Object>();
                Map<?, ?> nestedMap = (Map<?, ?>) value;
                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    flatNestedMap.put(
                            String.format("%s[%s]", key, nestedEntry.getKey()),
                            nestedEntry.getValue());
                }
                flatParams.putAll(flattenParams(flatNestedMap));
            } else if (value == null) {
                flatParams.put(key, "");
            } else {
                flatParams.put(key, value.toString());
            }
        }
        return flatParams;
    }


    private static String formatURL(String url, String query) {
        if (query == null || query.isEmpty()) {
            return url;
        } else {
            String separator = url.contains("?") ? "&" : "?";
            return String.format("%s%s%s", url, separator, query);
        }
    }

    private static String getBasicAuthString(OmiseURL omiseUrl) throws OmiseKeyUnsetException {
        switch(omiseUrl){
            case API:
                return APIResource.getSecretKey()+ ":";
            case VAULT:
                return APIResource.getPublicKey()+ ":";
            default:
                return ":";
        }
    }

}
