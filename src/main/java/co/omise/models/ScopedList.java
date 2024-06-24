package co.omise.models;

import co.omise.Serializer;
import okhttp3.RequestBody;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScopedList<T extends Model> extends OmiseList<T> {
    private ZonedDateTime from;
    private ZonedDateTime to;
    private int offset;
    private int limit;

    public ScopedList() {
    }

    public ZonedDateTime getFrom() {
        return from;
    }

    public void setFrom(ZonedDateTime from) {
        this.from = from;
    }

    public ZonedDateTime getTo() {
        return to;
    }

    public void setTo(ZonedDateTime to) {
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
        private ZonedDateTime from;
        private ZonedDateTime to;
        private Ordering order;

        public Options offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Options limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Options from(ZonedDateTime from) {
            this.from = from;
            return this;
        }

        public Options to(ZonedDateTime to) {
            this.to = to;
            return this;
        }

        public Options order(Ordering order) {
            this.order = order;
            return this;
        }

        @Override
        public Map<String, String> query(Serializer serializer) {
            if (serializer == null) {
                serializer = Serializer.defaultSerializer();
            }

            Map<String, String> map = new HashMap<>();
            DateTimeFormatter formatter = serializer.dateTimeFormatter();

            if (offset != null) {
                map.put("offset", offset.toString());
            }
            if (limit != null) {
                map.put("limit", limit.toString());
            }
            if (from != null) {
                map.put("from", formatter.format(from));
            }
            if (to != null) {
                map.put("to", formatter.format(to));
            }

            if (order != null) {
                map.put("order", serializer.serializeToQueryParams(order));
            }
            return Collections.unmodifiableMap(map);
        }

        @Override
        public RequestBody body(Serializer serializer) {
            return null;
        }
    }
}
