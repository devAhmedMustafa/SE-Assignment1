package features.orders.placeOrder;

import utils.events.Listener;

public interface PlaceOrderListener extends Listener<PlaceOrderPayload> {
    void update(PlaceOrderPayload event);
}
