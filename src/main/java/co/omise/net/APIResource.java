package co.omise.net;


import co.omise.exception.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by tin_hanc on 9/7/2015 AD.
 */
public class APIResource extends OmiseObject {
    private static OmiseConnection _omiseConnection = new OmiseConnectionImpl();

    public static void setOmiseConnection(OmiseConnection _omiseConnection) {
        APIResource._omiseConnection = _omiseConnection;
    }

    private static final String _LIVE_API_BASE = "https://api.omise.co/";
    private static final String _LIVE_VAULT_BASE = "https://vault.omise.co/";
    private static volatile String _publicKey = null;
    private static volatile String _secretKey = null;
    private static  String _apiBase = _LIVE_API_BASE;
    private static  String _vaultBase = _LIVE_VAULT_BASE;

    public static void setPublicKey(String publicKey) {
        APIResource._publicKey = publicKey;
    }
    public static void setSecretKey(String secretKey) {
        APIResource._secretKey = secretKey;
    }

    public static String getPublicKey() throws OmiseKeyUnsetException {
        if(_publicKey == null) throw
                new OmiseKeyUnsetException("NullObject Exception, Pleas check your public key. Public key need for Authorization is not empty.", null);

        return _publicKey;
    }
    public static String getSecretKey() throws OmiseKeyUnsetException {
        if(_secretKey == null) throw
                new OmiseKeyUnsetException("NullObject Exception, Pleas check your secret key. Secret key need for Authorization is not empty.", null);

        return _secretKey;
    }

    public static final String CHARSET = "UTF-8";
    public static int CONNECT_TIMEOUT = 30 * 1000;
    public static int READ_TIMEOUT = 80 * 1000;

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
                return _apiBase;
            }
        },
        VAULT {
            @Override
            public String toString() {
                return _vaultBase;
            }
        }
    }

    protected static  <O> O request(OmiseURL omiseUrl, String endPoint, RequestMethod method, Map<String, Object> params, Class<?> clazz)
            throws IOException, OmiseAPIException, OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException {
        return APIResource._omiseConnection.request(omiseUrl, endPoint, method, params, clazz);
    }



    public static String urlEncode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        else {
            return URLEncoder.encode(str, CHARSET);
        }
    }
}
