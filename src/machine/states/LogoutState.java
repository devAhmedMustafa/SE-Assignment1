package machine.states;

import features.auth.Authenticator;
import machine.AppMachine;
import machine.AppState;
import machine.STATES;

public class LogoutState implements AppState {

    @Override
    public void onEnter(AppMachine machine) {
        System.out.println("Good bye " + Authenticator.getInstance().getCurrentUser() + "!");
        Authenticator.getInstance().logout();
        machine.setState(STATES.LOGIN);
    }

    @Override
    public void onExit(AppMachine machine) {

    }
}
