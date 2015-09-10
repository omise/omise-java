package co.omise.net;

import co.omise.exception.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tin_hanc on 9/7/2015 AD.
 */
public interface OmiseConnection {
    <O> O request(APIResource.OmiseURL omiseUrl, String endPoint,
                  APIResource.RequestMethod method, Map<String, Object> params,
                  Class<?> clazz) throws IOException, OmiseAPIException,
            OmiseKeyUnsetException, OmiseUnknownException, OmiseAPIConnectionException, OmiseInvalidRequestException;
}
