package co.omise.resources;

import co.omise.Endpoint;
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

    public ScopedList<Transfer> list() throws IOException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Transfer> list(ScopedList.Options options) throws IOException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Transfer>>() {
        });
    }

    public Transfer get(String transferId) throws IOException {
        return httpGet(urlFor(transferId)).returns(Transfer.class);
    }

    public Transfer create(Transfer.Create params) throws IOException {
        return httpPost(urlFor("")).params(params).returns(Transfer.class);
    }

    public Transfer update(String transferId, Transfer.Update params) throws IOException {
        return httpPost(urlFor(transferId)).params(params).returns(Transfer.class);
    }

    public Transfer destroy(String transferId) throws IOException {
        return httpDelete(urlFor(transferId)).returns(Transfer.class);
    }

    private HttpUrl urlFor(String transferId) {
        return buildUrl(Endpoint.API, "transfer", transferId);
    }
}
