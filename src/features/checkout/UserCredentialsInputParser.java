package features.checkout;

import features.payment.UserPaymentCredentials;
import features.payment.creditCard.CreditCardCredentials;
import features.payment.digitalWallet.DigitalWalletCredentials;

public class UserCredentialsInputParser {

    public static UserPaymentCredentials parseUserCredentialsInput(String input, String paymentMethod) throws IllegalArgumentException {
        switch (paymentMethod.toLowerCase()) {
            case "creditcard" -> {
                String[] parts = input.split(",");
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid credit card credentials format. Expected format: cardNumber,cardHolder,expiryDate,cvv");
                }

                String cardNumber = parts[0].trim();
                String cardHolderName = parts[1].trim();
                String expiryDate = parts[2].trim();
                String cvv = parts[3].trim();

                return new CreditCardCredentials(cardNumber,cardHolderName, expiryDate, cvv);
            }

            case "digitalwallet" -> {
                String [] parts = input.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid digital wallet credentials format. Expected format: walletId,authToken");
                }

                String walletId = parts[0].trim();
                String authToken = parts[1].trim();

                return new DigitalWalletCredentials(walletId, authToken);
            }

            default -> throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }

}
