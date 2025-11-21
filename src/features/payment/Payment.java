package features.payment;

import features.orders.Order;
import utils.storage.KeyAttribute;

import java.util.UUID;

public class Payment {
    public Order order;

    @KeyAttribute
    public String transactionId;

    public int amount;
    public String method;

    public Payment(Order order, int amount, String method) {
        this.order = order;
        this.amount = amount;
        this.method = method;
        this.transactionId = UUID.randomUUID().toString();
    }
}
