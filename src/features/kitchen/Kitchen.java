package features.kitchen;

import features.orders.IOrder;
import features.orders.placeOrder.PlaceOrderListener;
import features.orders.placeOrder.PlaceOrderPayload;

import java.util.Queue;

public class Kitchen implements PlaceOrderListener {
    private static Kitchen instance = null;
    private Kitchen() {}

    public static Kitchen getInstance() {
        if (instance == null) {
            instance = new Kitchen();
        }
        return instance;
    }

    private Queue<IOrder> orderQueue;

    private void processNextOrder() {
        IOrder order = orderQueue.poll();
        if (order != null) {
            order.process("preparing");
        }
    }

    @Override
    public void update(PlaceOrderPayload event) {
        orderQueue.add(event.order);
        if (orderQueue.size() == 1) {
            processNextOrder();
        }
    }
}
