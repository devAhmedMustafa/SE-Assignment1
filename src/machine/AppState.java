package machine;

public interface AppState {
    public void onEnter(AppMachine machine);
    public void onExit(AppMachine machine);
}
