package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Dispute;
import co.omise.models.DisputeStatus;
import co.omise.models.OmiseException;
import co.omise.models.ScopedList;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class DisputeResource extends Resource {
    public DisputeResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public ScopedList<Dispute> list() throws IOException, OmiseException {
        return list(null, new ScopedList.Options());
    }

    public ScopedList<Dispute> list(ScopedList.Options options) throws IOException, OmiseException {
        return list(null, options);
    }

    public ScopedList<Dispute> list(DisputeStatus status) throws IOException, OmiseException {
        return list(status, new ScopedList.Options());
    }

    public ScopedList<Dispute> list(DisputeStatus status, ScopedList.Options options) throws IOException, OmiseException {
        String path = status == null ? "" : status.name().toLowerCase();
        return httpGet(urlFor(path)).params(options).returns(new TypeReference<ScopedList<Dispute>>() {
        });
    }

    public Dispute get(String disputeId) throws IOException, OmiseException {
        return httpGet(urlFor(disputeId)).returns(Dispute.class);
    }

    private HttpUrl urlFor(String disputeId) {
        return buildUrl(Endpoint.API, "disputes", disputeId);
    }
}
