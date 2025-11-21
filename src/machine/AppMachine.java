package machine;

public class AppMachine {
    private AppState currentState;
    private StateFactory stateFactory;

    public AppMachine() {
        stateFactory = new StateFactory();
        currentState = stateFactory.getAppState(STATES.LOGIN);
        if (currentState != null) {
            currentState.onEnter(this);
        }
    }

    public void setState(STATES state) {
        if (currentState != null) {
            currentState.onExit(this);
        }
        currentState = stateFactory.getAppState(state);
        if (currentState != null) {
            currentState.onEnter(this);
        }
    }
}
