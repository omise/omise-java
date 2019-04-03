package co.omise.models;

import java.util.Collections;
import java.util.List;

public class OmiseList<T extends Model> extends OmiseObjectBase {
    private int total;
    private Ordering order;
    private List<T> data;

    public OmiseList() {
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = Collections.unmodifiableList(data);
    }
}