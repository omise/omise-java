package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.ScopedList;
import co.omise.models.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class TransactionResource extends Resource {
    public TransactionResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Transaction> list() throws IOException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Transaction> list(ScopedList.Options options) throws IOException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Transaction>>() {
        });
    }

    public Transaction get(String transactionId) throws IOException {
        return httpGet(urlFor(transactionId)).returns(Transaction.class);
    }

    private HttpUrl urlFor(String transactionId) {
        return buildUrl(Endpoint.API, "transactions", transactionId);
    }
}
