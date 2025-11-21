package features.payment;


import features.orders.IOrder;

public class PaymentProcessor {

    private static PaymentProcessor instance;
    private PaymentProcessor() {}

    public static PaymentProcessor getInstance() {
        if (instance == null) {
            instance = new PaymentProcessor();
        }
        return instance;
    }

    public void processPayment(IOrder order, String paymentMethod, UserPaymentCredentials credentials) {
        int amount = order.getTotalAmount();
        PaymentMethod method = PaymentMethodFactory.createPaymentMethod(paymentMethod);
        method.pay(amount, credentials);
    }

}
