package machine.states;

import features.menus.MenuUI;
import machine.AppMachine;
import machine.AppState;

import java.util.Scanner;

public class DisplayMenuState implements AppState {

    private final MenuUI menuUI = new MenuUI();

    @Override
    public void onEnter(AppMachine machine) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Display Menu ===");
        while (true) {
            try {
                System.out.print("Enter Menu Title to display: ");
                String title = sc.nextLine();
                String menuDisplay = menuUI.displayMenu(title);
                System.out.println(menuDisplay);
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
