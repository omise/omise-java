package co.omise.resources;

import co.omise.Endpoint;
import co.omise.models.Model;
import co.omise.models.OmiseException;
import co.omise.models.SearchResult;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class SearchResource extends Resource {
    public SearchResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public <T extends Model> SearchResult<T> search(SearchResult.Options params) throws IOException, OmiseException {
        return httpGet(buildUrl(Endpoint.API, "search")).params(params).returns(new TypeReference<SearchResult<T>>() {
        });
    }
}

