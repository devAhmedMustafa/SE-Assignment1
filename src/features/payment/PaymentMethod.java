package features.payment;

public interface PaymentMethod {
    int pay(int amount, UserPaymentCredentials credentials);
}
