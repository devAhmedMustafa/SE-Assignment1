package features.payment.digitalWallet;

import features.payment.PaymentMethod;
import features.payment.UserPaymentCredentials;

public class DigitalWallet implements PaymentMethod {
    @Override
    public int pay(int amount, UserPaymentCredentials credentials) {
        if (!(credentials instanceof DigitalWalletCredentials dwCredentials)) {
            throw new IllegalArgumentException("Invalid credentials for digital wallet payment.");
        }

        DigitalWalletCredentialsValidator validator = new DigitalWalletCredentialsValidator();
        if (!validator.isValid(dwCredentials)) {
            throw new IllegalArgumentException("Digital wallet credentials validation failed.");
        }

        return amount - (int)(amount * 0.015);
    }
}
