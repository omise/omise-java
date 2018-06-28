package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import co.omise.models.Transfer;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class TransferResource extends Resource {
    public TransferResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Transfer> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Transfer> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Transfer>>() {
        });
    }

    private HttpUrl urlFor(String transferId) {
        return buildUrl(Endpoint.API, "transfers", transferId);
    }
}
