package features.checkout;

import features.orders.ORDER_TYPE;
import features.orders.OrderTypeMapper;
import features.payment.UserPaymentCredentials;
import utils.debug.Logger;

import java.awt.*;

public class CheckoutUI {

    public void checkout(String customerName, String orderTypeInput, String paymentMethod, String userPaymentCredentialsInput) {
        try {
            ORDER_TYPE orderType = OrderTypeMapper.mapStringToOrderType(orderTypeInput);
            UserPaymentCredentials userPaymentCredentials = UserCredentialsInputParser.parseUserCredentialsInput(userPaymentCredentialsInput, paymentMethod);
            CheckoutManager.getInstance().checkout(customerName, orderType, paymentMethod, userPaymentCredentials);
            Logger.log("Checkout process completed successfully.", Color.GREEN);
        }
        catch (Exception e) {
            Logger.log("Error during checkout process: " + e.getMessage(), Color.RED);
        }
    }

}
