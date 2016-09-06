package co.omise.models;

import com.google.common.collect.ImmutableList;

public class OmiseList<T extends Model> extends OmiseObjectBase {
    private int total;
    private Ordering order;
    private ImmutableList<T> data;

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

