package features.payment.creditCard;

import features.payment.UserPaymentCredentials;

public class CreditCardCredentials implements UserPaymentCredentials {
    public String cardNumber;
    public String cardHolderName;
    public String expirationDate;
    public String cvv;

    public CreditCardCredentials(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}
