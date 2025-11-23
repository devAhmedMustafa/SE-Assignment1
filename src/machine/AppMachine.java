package machine;

public class AppMachine {
    private AppState currentState, prevState;
    private StateFactory stateFactory;

    public AppMachine() {
        stateFactory = new StateFactory();
        currentState = stateFactory.getAppState(STATES.LOGIN);
        prevState = currentState;

        if (currentState != null) {
            currentState.onEnter(this);
        }
    }

    public void setState(STATES state) {
        prevState = currentState;
        if (currentState != null) {
            currentState.onExit(this);
        }
        currentState = stateFactory.getAppState(state);
        if (currentState != null) {
            currentState.onEnter(this);
        }
    }

    public void back() {
        if (prevState != null) {
            currentState.onExit(this);
            AppState temp = currentState;
            currentState = prevState;
            prevState = temp;
            currentState.onEnter(this);
        }
    }
}
