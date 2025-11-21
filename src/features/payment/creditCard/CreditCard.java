package features.payment.creditCard;

import features.payment.PaymentMethod;
import features.payment.UserPaymentCredentials;

public class CreditCard implements PaymentMethod {
    @Override
    public int pay(int amount, UserPaymentCredentials credentials) {
        if (!(credentials instanceof CreditCardCredentials ccCredentials)) {
            throw new IllegalArgumentException("Invalid credentials for credit card payment.");
        }

        CreditCardCredentialsValidator validator = new CreditCardCredentialsValidator();
        if (!validator.isValid(ccCredentials)) {
            throw new IllegalArgumentException("Credit card credentials validation failed.");
        }

        return amount - (int)(amount * 0.02);
    }
}
