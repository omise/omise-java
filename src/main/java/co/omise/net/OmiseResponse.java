package co.omise.net;

import java.util.List;
import java.util.Map;

/**
 * Created by tin_hanc on 9/7/2015 AD.
 */
public class OmiseResponse {
    private int _responseCode;
    private String _responseBody;
    private Map<String, List<String>> _responseHeaders;

    public OmiseResponse(int responseCode, String responseBody) {
        this._responseCode = responseCode;
        this._responseBody = responseBody;
        this._responseHeaders = null;
    }
    public OmiseResponse(int responseCode, String responseBody, Map<String, List<String>> responseHeaders) {
        this._responseCode = responseCode;
        this._responseBody = responseBody;
        this._responseHeaders = responseHeaders;
    }

    public int getResponseCode() {
        return _responseCode;
    }

    public void setResponseCode(int responseCode) {
        this._responseCode = responseCode;
    }

    public String getResponseBody() {
        return _responseBody;
    }

    public void setResponseBody(String responseBody) {
        this._responseBody = responseBody;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return _responseHeaders;
    }
}
