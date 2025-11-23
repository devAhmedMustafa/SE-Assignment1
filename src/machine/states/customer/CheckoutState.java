package machine.states.customer;

import features.checkout.CheckoutUI;
import machine.AppMachine;
import machine.AppState;
import utils.io.InputQueueHandler;

import java.util.Scanner;

public class CheckoutState implements AppState {

    private final String[] creditCardFields = {"Card Number", "Expiry Date (MM/YY)", "CVV"};
    private final String[] digitalWalletFields = {"Wallet ID", "Auth Token"};

    private final CheckoutUI checkoutUI = new CheckoutUI();

    @Override
    public void onEnter(AppMachine machine) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Checkout ===");

        while (true) {
            try {
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();

                System.out.println("Select Order Type ");
                System.out.println("1. Dine-In");
                System.out.println("2. Takeaway");
                System.out.println("3. Delivery");
                String orderTypeChoice = sc.nextLine();

                String orderType = switch (orderTypeChoice) {
                    case "1" -> "DineIn";
                    case "2" -> "Takeaway";
                    case "3" -> "Delivery";
                    default -> throw new Exception("Invalid order type selected.");
                };

                System.out.println("Select your payment method ");
                System.out.println("1. Credit Card");
                System.out.println("2. Digital Wallet");

                String paymentChoice = sc.nextLine();
                String paymentMethod;
                if (paymentChoice.equals("1")) {
                    paymentMethod = "CreditCard";
                } else if (paymentChoice.equals("2")) {
                    paymentMethod = "DigitalWallet";
                } else {
                    throw new Exception("Invalid payment method selected.");
                }

                StringBuilder userPaymentCredentials = new StringBuilder();

                switch (paymentChoice) {
                    case "1":
                        InputQueueHandler ioQ1 = new InputQueueHandler(creditCardFields);
                        ioQ1.takeInputs(sc);

                        String cardNumber = ioQ1.poll();
                        String expiryDate = ioQ1.poll();
                        String cvv = ioQ1.poll();

                        userPaymentCredentials.append(cardNumber).append(",").append(customerName).append(",").append(expiryDate).append(",").append(cvv);
                        break;
                    case "2":
                        InputQueueHandler ioQ2 = new InputQueueHandler(digitalWalletFields);
                        ioQ2.takeInputs(sc);

                        String walletId = ioQ2.poll();
                        String authToken = ioQ2.poll();

                        userPaymentCredentials.append(walletId).append(",").append(customerName).append(",").append(authToken);
                        break;
                    default:
                        throw new Exception("Invalid payment method selected.");
                }

                checkoutUI.checkout(customerName, orderType, paymentMethod, userPaymentCredentials.toString());

                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
