package features.payment.creditCard;

import features.payment.PaymentCredentialsValidator;
import features.payment.UserPaymentCredentials;

public class CreditCardCredentialsValidator implements PaymentCredentialsValidator {

    private boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    @Override
    public boolean isValid(UserPaymentCredentials credentials) {
        if (credentials instanceof CreditCardCredentials ccCredentials) {
            String cardNumber = ccCredentials.cardNumber;
            String expiryDate = ccCredentials.expirationDate;
            String cvv = ccCredentials.cvv;

            cardNumber = cardNumber.replaceAll("\\s+", "");

            if (cardNumber == null || cardNumber.length() != 16 || !cardNumber.matches("\\d+") || !luhnCheck(cardNumber)) {
                return false;
            }
            if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                return false;
            }

            if (expiryDate != null) {
                String[] parts = expiryDate.split("/");
                int month = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]) + 2000;

                java.util.Calendar now = java.util.Calendar.getInstance();
                int currentYear = now.get(java.util.Calendar.YEAR);
                int currentMonth = now.get(java.util.Calendar.MONTH) + 1;

                if (year < currentYear || (year == currentYear && month < currentMonth)) {
                    return false;
                }
            }

            if (cvv == null || cvv.length() != 3 || !cvv.matches("\\d+")) {
                return false;
            }

            return true;
        }

        return false;
    }
}
