package features.auth;

public class Authenticator {

    private static Authenticator instance = null;

    private boolean isAuthenticated = false;
    private String currentUser = null;
    private boolean isAdmin = false;

    private Authenticator() {}

    public static Authenticator getInstance() {
        if (instance == null) {
            instance = new Authenticator();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

            if (username.equals("admin")) {
                if (password.equals("adminpass")) {
                    isAdmin = true;
                }
                else {
                    throw new IllegalArgumentException("Invalid admin credentials");
                }
            }

            isAuthenticated = true;
            currentUser = username;
            return true;
        }
        return false;
    }

    public boolean logout() {
        isAuthenticated = false;
        currentUser = null;
        isAdmin = false;
        return true;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
