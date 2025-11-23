package machine;

import machine.states.DisplayMenuState;
import machine.states.ViewAllMenusState;
import machine.states.customer.AddToCartState;
import machine.states.customer.CheckoutState;
import machine.states.manager.AddNewCategoryState;
import machine.states.LoginState;
import machine.states.manager.ManagerDashboardState;
import machine.states.manager.AddNewItemState;

import java.util.Map;

public class StateFactory {
    private final Map<STATES, AppState> map = Map.of(
            STATES.LOGIN, new LoginState(),

            STATES.MANAGER_DASHBOARD, new ManagerDashboardState(),
            STATES.ADD_NEW_ITEM, new AddNewItemState(),
            STATES.ADD_ITEM_CATEGORY, new AddNewCategoryState(),
            STATES.ADD_NEW_MENU, new machine.states.manager.AddNewMenu(),

            STATES.VIEW_ALL_MENUS, new ViewAllMenusState(),
            STATES.DISPLAY_MENU, new DisplayMenuState(),

            STATES.ADD_TO_CART, new AddToCartState(),
            STATES.CHECKOUT, new CheckoutState(),

            STATES.LOGOUT, new machine.states.LogoutState()
    );



    public AppState getAppState(STATES state) {
        return map.get(state);
    }
}
