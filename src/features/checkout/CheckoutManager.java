package features.checkout;

import features.cart.Cart;
import features.cart.OrderedItem;
import features.orders.ORDER_TYPE;
import features.orders.Order;
import features.orders.OrderBuilder;
import features.orders.placeOrder.PlaceOrderEvent;
import features.orders.placeOrder.PlaceOrderPayload;
import features.payment.PaymentProcessor;
import features.payment.UserPaymentCredentials;
import utils.events.EventFactory;

import java.util.Map;

public class CheckoutManager {

    private static CheckoutManager instance;
    private CheckoutManager() {
        placeOrderEvent = (PlaceOrderEvent) EventFactory.getEvent(PlaceOrderEvent.class);
        paymentProcessor = PaymentProcessor.getInstance();
    }
    public static CheckoutManager getInstance() {
        if (instance == null) {
            instance = new CheckoutManager();
        }
        return instance;
    }

    private final PlaceOrderEvent placeOrderEvent;
    private final PaymentProcessor paymentProcessor;

    public void checkout(String customerName, ORDER_TYPE orderType, String paymentMethod, UserPaymentCredentials userPaymentCredentials) {
        OrderBuilder orderBuilder = new OrderBuilder();

        Map<OrderedItem, Integer> items = Cart.getInstance().getItems();

        Order order = orderBuilder
                .setCustomerName(customerName)
                .setTotalAmount(Cart.getInstance().calculateTotal())
                .setType(orderType)
                .setOrderedItems(items)
                .build();

        paymentProcessor.processPayment(order, paymentMethod, userPaymentCredentials);
        Cart.getInstance().clearCart();

        placeOrderEvent.notify(new PlaceOrderPayload(order));
    }

}
