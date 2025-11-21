package features.checkout;

import features.payment.UserPaymentCredentials;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserCredentialsInputParserTest {


    @Test
    @DisplayName("Test parseUserCredentialsInput with valid credit card input" )
    public void testParseUserCredentialsInput_CreditCard_ValidInput() {
        String input = "1234567890123456,John Doe,12/25,123";
        String paymentMethod = "CreditCard";

        UserPaymentCredentials userCredentials = UserCredentialsInputParser.parseUserCredentialsInput(input, paymentMethod);
        assert userCredentials instanceof features.payment.creditCard.CreditCardCredentials;
    }

    @Test
    @DisplayName("Test parseUserCredentialsInput with valid digital wallet input" )
    public void testParseUserCredentialsInput_DigitalWallet_ValidInput() {
        String input = "12345,67890";
        String paymentMethod = "DigitalWallet";
        UserPaymentCredentials userCredentials = UserCredentialsInputParser.parseUserCredentialsInput(input, paymentMethod);
        assert userCredentials instanceof features.payment.digitalWallet.DigitalWalletCredentials;
    }

}
