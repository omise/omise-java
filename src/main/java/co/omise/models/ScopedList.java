package co.omise.models;

import co.omise.Serializer;
import com.google.common.collect.ImmutableMap;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class ScopedList<T extends Model> extends OmiseList<T> {
    private DateTime from;
    private DateTime to;
    private int offset;
    private int limit;

    public DateTime getFrom() {
        return from;
    }

    public void setFrom(DateTime from) {
        this.from = from;
    }

    public DateTime getTo() {
        return to;
    }

    public void setTo(DateTime to) {
        this.to = to;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public static class Options extends Params {
        private Integer offset;
        private Integer limit;
        private DateTime from;
        private DateTime to;
        private Ordering order;

        public Options offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Options limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Options from(DateTime from) {
            this.from = from;
            return this;
        }

        public Options to(DateTime to) {
            this.to = to;
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
            DateTimeFormatter formatter = serializer.dateTimeFormatter();

            if (offset != null) {
                builder = builder.put("offset", offset.toString());
            }
            if (limit != null) {
                builder = builder.put("limit", limit.toString());
            }
            if (from != null) {
                builder = builder.put("from", formatter.print(from));
            }
            if (to != null) {
                builder = builder.put("to", formatter.print(to));
            }

            if (order != null) {
                builder = builder.put("order", serializer.serializeToQueryParams(order));
            }

            return builder.build();
        }

        @Override
        public RequestBody body(Serializer serializer) {
            return new FormBody.Builder().build();
        }
    }
}
