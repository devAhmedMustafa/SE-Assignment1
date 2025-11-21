package features.orders.extended;

import features.orders.Order;

public class DeliveryOrder extends ExtendedOrder{

    public DeliveryOrder(Order order) {
        super(order);
    }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary();
    }

    @Override
    public int getTotalAmount() {
        return super.getTotalAmount() + 500;
    }
}
