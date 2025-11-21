package features.orders;

import features.cart.OrderedItem;

import java.util.Date;
import java.util.Map;

public class OrderBuilder {
    private String customerName;
    private int totalAmount;
    private int tip = 0;
    private ORDER_TYPE type;
    private Map<OrderedItem, Integer> orderedItems;

    public OrderBuilder setOrderedItems(Map<OrderedItem, Integer> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }

    public OrderBuilder setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public OrderBuilder setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderBuilder setTip(int tip) {
        this.tip = tip;
        return this;
    }

    public OrderBuilder setType(ORDER_TYPE type) {
        this.type = type;
        return this;
    }

    public Order build() {
        Date date = new Date();
        String status = "Pending";
        return new Order(customerName, date, totalAmount, tip, status, type, orderedItems);
    }
}
