package machine;

import machine.states.LoginState;
import machine.states.ManagerDashboardState;

import java.util.Map;

public class StateFactory {
    private final Map<STATES, AppState> map = Map.of(
            STATES.LOGIN, new LoginState(),
            STATES.MANAGER_DASHBOARD, new ManagerDashboardState()
    );



    public AppState getAppState(STATES state) {
        return map.get(state);
    }
}
