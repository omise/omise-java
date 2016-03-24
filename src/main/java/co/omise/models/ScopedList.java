package co.omise.models;

import com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;

public class ScopedList<T extends Model> {
    private String object;
    private String location;
    private DateTime from;
    private DateTime to;
    private int offset;
    private int limit;
    private int total;
    private Ordering order;
    private ImmutableList<T> data;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Ordering getOrder() {
        return order;
    }

    public void setOrder(Ordering order) {
        this.order = order;
    }

    public ImmutableList<T> getData() {
        return data;
    }

    public void setData(ImmutableList<T> data) {
        this.data = data;
    }
}
