package machine.states;

import features.menus.MenuUI;
import machine.AppMachine;
import machine.AppState;

public class ViewAllMenusState implements AppState {
    private final MenuUI menuUI = new MenuUI();

    @Override
    public void onEnter(AppMachine machine) {
        System.out.println("=== All Menus ===");
        try {
            String menus = menuUI.displayMenu();
            System.out.println(menus);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        machine.back();
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
