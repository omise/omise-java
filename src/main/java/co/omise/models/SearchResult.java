package co.omise.models;

import co.omise.Endpoint;
import co.omise.Serializer;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents Omise SearchResult object and contains all of its {@link RequestBuilder}.
 *
 * @see <a href="https://www.omise.co/search-api">Search API</a>
 */
public class SearchResult<T extends Model> extends OmiseList<T> {
    private SearchScope scope;
    private String query;
    private Map<String, String> filters;
    private int page;
    @JsonProperty("total_pages")
    private int totalPages;

    public SearchScope getScope() {
        return scope;
    }

    public void setScope(SearchScope scope) {
        this.scope = scope;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public static class Options extends Params {
        private SearchScope scope;
        private String query;
        private Map<String, String> filters;
        private Ordering order;

        public Options scope(SearchScope scope) {
            this.scope = scope;
            return this;
        }

        public Options query(String query) {
            this.query = query;
            return this;
        }

        public Options filter(String key, String value) {
            if (this.filters == null) {
                this.filters = new HashMap<>();
                return this.filter(key, value);
            }

            this.filters.put(key, value);
            return this;
        }

        public Options filters(Map<String, String> filters) {
            this.filters = filters;
            return this;
        }

        public Options order(Ordering order) {
            this.order = order;
            return this;
        }

        @Override
        public ImmutableMap<String, String> query(Serializer serializer) {
            if (serializer == null) {
                serializer = Serializer.defaultSerializer();
            }

            ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();

            if (scope != null) {
                builder = builder.put("scope", serializer.serializeToQueryParams(scope));
            }
            if (query != null && !query.isEmpty()) {
                builder = builder.put("query", query);
            }

            if (filters != null && !filters.isEmpty()) {
                for (Map.Entry<String, String> entry : filters.entrySet()) {
                    builder = builder.put("filters[" + entry.getKey() + "]", entry.getValue());
                }
            }
            if (order != null) {
                builder = builder.put("order", serializer.serializeToQueryParams(order));
            }

            return builder.build();
        }

        @Override
        public RequestBody body(Serializer serializer) {
            return null;
        }
    }

    /**
     * The {@link RequestBuilder} class for retrieving search result with an specific data within scope that belong to an account.
     */
    public static class SearchRequestBuilder<T extends Model> extends RequestBuilder<SearchResult<T>> {

        private SearchResult.Options options;

        public SearchRequestBuilder(SearchResult.Options options) {
            this.options = options;
        }

        @Override
        protected HttpUrl path() throws IOException {
            return buildUrl(Endpoint.API, "search", options);
        }

        @Override
        protected ResponseType<SearchResult<T>> type() {
            return new ResponseType<>(new TypeReference<SearchResult<T>>() {});
        }
    }
}