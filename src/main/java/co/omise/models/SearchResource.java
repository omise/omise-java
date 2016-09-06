package co.omise.models;

import co.omise.Endpoint;
import co.omise.resources.Resource;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.OkHttpClient;

import java.io.IOException;

public class SearchResource extends Resource {
    public SearchResource(OkHttpClient httpClient) {
        super(httpClient);
    }

    public <T extends Model> SearchResult<T> search(SearchResult.Options params) throws IOException {
        return httpGet(buildUrl(Endpoint.API, "search")).params(params).returns(new TypeReference<SearchResult<T>>() {
        });
    }
}

