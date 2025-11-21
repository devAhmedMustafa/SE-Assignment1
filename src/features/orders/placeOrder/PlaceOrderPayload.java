package features.orders.placeOrder;

import features.orders.Order;
import utils.events.EventPayload;

public class PlaceOrderPayload implements EventPayload {
    public Order order;

    public PlaceOrderPayload(Order order) {
        this.order = order;
    }
}
