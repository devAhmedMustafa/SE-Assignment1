package features.payment;

import features.payment.creditCard.CreditCard;
import features.payment.digitalWallet.DigitalWallet;

public class PaymentMethodFactory {
    public static PaymentMethod createPaymentMethod(String type) {
        switch (type.toLowerCase()) {
            case "creditcard":
                return new CreditCard();
            case "digitalwallet":
                return new DigitalWallet();
            default:
                throw new IllegalArgumentException("Unknown payment method type: " + type);
        }
    }
}
