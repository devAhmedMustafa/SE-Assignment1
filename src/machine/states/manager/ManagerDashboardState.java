package machine.states.manager;

import machine.AppMachine;
import machine.AppState;
import machine.STATES;

import java.util.Scanner;

public class ManagerDashboardState implements AppState {
    @Override
    public void onEnter(AppMachine machine) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Manager Dashboard ===");

        boolean running = true;

        while (running) {
            System.out.println("1. Add a new item category");
            System.out.println("2. Add a new item");
            System.out.println("3. View all menus");
            System.out.println("4. Display a menu");
            System.out.println("5. Add new menu");
            System.out.println("6. Logout");

            System.out.print("Select an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    machine.setState(STATES.ADD_ITEM_CATEGORY);
                    break;
                case "2":
                    machine.setState(STATES.ADD_NEW_ITEM);
                    break;
                case "3":
                    machine.setState(STATES.VIEW_ALL_MENUS);
                    break;
                case "4":
                    machine.setState(STATES.DISPLAY_MENU);
                    break;
                case "5":
                    machine.setState(STATES.ADD_NEW_MENU);
                    break;
                case "6":
                    machine.setState(STATES.LOGOUT);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            running = false;
        }
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
