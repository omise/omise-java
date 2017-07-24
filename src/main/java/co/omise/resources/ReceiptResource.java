package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.OmiseException;
import co.omise.models.Receipt;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class ReceiptResource extends Resource {
    public ReceiptResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Receipt> list() throws IOException, OmiseException {
        return list(new ScopedList.Options());
    }

    public ScopedList<Receipt> list(ScopedList.Options options) throws IOException, OmiseException {
        return httpGet(urlFor("")).params(options).returns(new TypeReference<ScopedList<Receipt>>() {
        });
    }

    public Receipt get(String receiptId) throws IOException, OmiseException {
        return httpGet(urlFor(receiptId)).returns(Receipt.class);
    }

    private HttpUrl urlFor(String receiptId) {
        return buildUrl(Endpoint.API, "receipts" , receiptId);
    }
}
