package features.payment.digitalWallet;

import features.payment.PaymentCredentialsValidator;
import features.payment.UserPaymentCredentials;

public class DigitalWalletCredentialsValidator implements PaymentCredentialsValidator {
    @Override
    public boolean isValid(UserPaymentCredentials credentials) {
        if (credentials instanceof DigitalWalletCredentials digitalWalletCredentials) {

            String walletId = digitalWalletCredentials.getWalletId();
            String authToken = digitalWalletCredentials.getAuthToken();

            if (walletId == null || walletId.length() != 11) return false;
            if (authToken == null || authToken.length() != 6) return false;

            return true;
        }
        return false;
    }
}
