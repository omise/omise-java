package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * Document object
 *
 * @see <a href="https://www.omise.co/documents-api">Document API</a>
 */
public class Document extends Model {
    @JsonProperty("download_uri")
    private String downloadUri;
    private String filename;
    private String location;

    public String getDownloadUri() {
        return this.downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class DeleteRequestBuilder extends RequestBuilder<Document> {
        private String disputeId;
        private String documentId;
        public DeleteRequestBuilder(String documentId, String disputeId) {
            this.disputeId = disputeId;
            this.documentId = documentId;
        }

        @Override
        protected String method() {
            return DELETE;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId, "documents", documentId);
        }

        @Override
        protected ResponseType<Document> type() {
            return new ResponseType<>(Document.class);
        }
    }

    public static class GetRequestBuilder extends RequestBuilder<Document> {
        private String disputeId;
        private String documentId;
        public GetRequestBuilder(String documentId, String disputeId) {
            this.disputeId = disputeId;
            this.documentId = documentId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId, "documents", documentId);
        }

        @Override
        protected ResponseType<Document> type() {
            return new ResponseType<>(Document.class);
        }
    }

    public static class ListRequestBuilder extends RequestBuilder<ScopedList<Document>> {
        private String disputeId;
        private ScopedList.Options options;
        public ListRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            if (options == null) {
                options = new ScopedList.Options();
            }
            return new HttpUrlBuilder(Endpoint.API, "disputes", serializer())
                    .segments(disputeId, "documents")
                    .params(options)
                    .build();
        }

        @Override
        protected ResponseType<ScopedList<Document>> type() {
            return new ResponseType<>(new TypeReference<ScopedList<Document>>() {});
        }

        public ListRequestBuilder options(ScopedList.Options options) {
            this.options = options;
            return this;
        }
    }

    public static class CreateRequestBuilder extends RequestBuilder<Document> {
        private String disputeId;

        @JsonProperty
        private String file;
        public CreateRequestBuilder(String disputeId) {
            this.disputeId = disputeId;
        }

        @Override
        protected String method() {
            return POST;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "disputes", disputeId, "documents");
        }

        @Override
        protected ResponseType<Document> type() {
            return new ResponseType<>(Document.class);
        }

        public CreateRequestBuilder file(String file) {
            this.file = file;
            return this;
        }

        @Override
        protected RequestBody payload() throws IOException  {
            return serialize();
        }
    }
}