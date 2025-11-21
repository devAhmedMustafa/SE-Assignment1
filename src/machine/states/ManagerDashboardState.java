package machine.states;

import machine.AppMachine;
import machine.AppState;

import java.util.Scanner;

public class ManagerDashboardState implements AppState {
    @Override
    public void onEnter(AppMachine machine) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Manager Dashboard ===");
        System.out.println("1. Add an item category");
        System.out.println("2. Add a new item");
        System.out.println("3. View all menus");
        System.out.println("4. Add new menu");
        System.out.println("5. Logout");
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
