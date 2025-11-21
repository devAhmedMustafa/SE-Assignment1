package features.payment.digitalWallet;

import features.payment.UserPaymentCredentials;

public class DigitalWalletCredentials implements UserPaymentCredentials {
    private final String walletId;
    private final String authToken;

    public DigitalWalletCredentials(String walletId, String authToken) {
        this.walletId = walletId;
        this.authToken = authToken;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getAuthToken() {
        return authToken;
    }
}
