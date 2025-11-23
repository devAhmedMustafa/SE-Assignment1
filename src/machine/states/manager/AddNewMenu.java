package machine.states.manager;

import features.menus.MenuUI;
import machine.AppMachine;
import machine.AppState;

import java.util.Scanner;

public class AddNewMenu implements AppState {

    private final MenuUI menuUI = new MenuUI();

    @Override
    public void onEnter(AppMachine machine) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("=== Add New Menu ===");

                System.out.println("Enter Menu Title: ");
                String title = sc.nextLine();

                System.out.println("Enter Menu Description: ");
                String description = sc.nextLine();

                System.out.println("Enter Item Names to be included in the menu (comma separated): ");
                String items = sc.nextLine();

                menuUI.addMenu(title, description, items);

                System.out.println("Menu added successfully!");
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        machine.back();
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
