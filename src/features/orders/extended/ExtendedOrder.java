package features.orders.extended;

import features.orders.IOrder;
import features.orders.Order;

public class ExtendedOrder implements IOrder {

    protected final Order order;

    protected ExtendedOrder(Order order) {
        this.order = order;
    }

    @Override
    public String getOrderSummary() {
        return order.getOrderSummary();
    }

    @Override
    public int getTotalAmount() {
        return order.getTotalAmount();
    }

    @Override
    public void process(String state) {
        order.process(state);
    }

}
