package features.orders.extended;

public class DineInOrder extends ExtendedOrder {

    public DineInOrder(features.orders.Order order) {
        super(order);
    }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary();
    }

    @Override
    public int getTotalAmount() {
        return order.subTotalAmount + order.subTotalAmount / 12;
    }

}
