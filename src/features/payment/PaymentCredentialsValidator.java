package features.payment;

public interface PaymentCredentialsValidator {
    boolean isValid(UserPaymentCredentials credentials);
}
