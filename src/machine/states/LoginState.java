package machine.states;

import features.auth.Authenticator;
import machine.AppMachine;
import machine.AppState;
import machine.STATES;

import java.util.Scanner;

public class LoginState implements AppState {

    @Override
    public void onEnter(AppMachine machine) {
        System.out.println("Hello User! Please log in to continue.");
        while (!Authenticator.getInstance().isAuthenticated()) {
            try {
                System.out.print("Please enter your username:");

                Scanner sc = new Scanner(System.in);
                String username = sc.nextLine();

                System.out.print("Please enter your password:");
                String password = sc.nextLine();

                Authenticator.getInstance().login(username, password);

                if (Authenticator.getInstance().isAdmin()) {
                    machine.setState(STATES.MANAGER_DASHBOARD);
                    return;
                }
            }
            catch (Exception e) {
                System.out.println("Login failed: " + e.getMessage());
            }
        }

        machine.setState(STATES.CUSTOMER_DASHBOARD);
    }

    @Override
    public void onExit(AppMachine machine) {
    }

}
