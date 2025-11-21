package features.orders;

import features.cart.OrderedItem;

import java.util.Date;
import java.util.Map;

public class Order implements IOrder {
    public String customerName;
    public Date date;
    public int subTotalAmount;
    public int tip;
    public String status;
    public ORDER_TYPE type;
    public Map<OrderedItem, Integer> items;

    public Order(String customerName, Date date, int totalAmount, int tip, String status, ORDER_TYPE type, Map<OrderedItem, Integer> items) {
        this.items = items;
        this.customerName = customerName;
        this.date = date;
        this.subTotalAmount = totalAmount;
        this.tip = tip;
        this.status = status;
        this.type = type;
    }

    @Override
    public int getTotalAmount() {
        return subTotalAmount;
    }

    @Override
    public String getOrderSummary() {
        return "Order for " + customerName + " on " + date.toString() + "\n Subtotal Amount = " + subTotalAmount + ", Tip = " + tip + "\n Status: " + status + "\n Type: " + type.toString() + "\n";
    }

    @Override
    public void process(String state){
        this.status = state;
    }
}
