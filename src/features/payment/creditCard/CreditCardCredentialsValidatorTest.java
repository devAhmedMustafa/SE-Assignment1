package features.payment.creditCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreditCardCredentialsValidatorTest {

    private final CreditCardCredentialsValidator creditCardCredentialsValidator = new CreditCardCredentialsValidator();

    @Test
    @DisplayName("Check valid credit card number")
    void testValidCreditCardNumber() {
        String validCardNumber = "4539 1488 0343 6467";
        CreditCardCredentials credentials = new CreditCardCredentials(validCardNumber, "", "12/25", "123");
        boolean isValid = creditCardCredentialsValidator.isValid(credentials);
        Assertions.assertTrue(isValid);
    }

    @Test
    @DisplayName("Check invalid credit card number")
    void testInvalidCreditCardNumber() {
        String invalidCardNumber = "1234 5678 9012 3456";
        CreditCardCredentials credentials = new CreditCardCredentials(invalidCardNumber, "", "12/25", "123");
        boolean isValid = creditCardCredentialsValidator.isValid(credentials);
        Assertions.assertFalse(isValid);
    }

    @Test
    @DisplayName("Check expired credit card")
    void testExpiredCreditCard() {
        String validCardNumber = "4539 1488 0343 6467";
        CreditCardCredentials credentials = new CreditCardCredentials(validCardNumber, "", "01/20", "123");
        boolean isValid = creditCardCredentialsValidator.isValid(credentials);
        Assertions.assertFalse(isValid);
    }

}
