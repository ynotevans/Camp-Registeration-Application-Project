package CAMs_App.data;
import CAMs_App.entity.User;


public class AuthData {
    private static User currentUser;
    private AuthData(){};

    public static void setCurrentUser(User currentUser) {
        AuthData.currentUser = currentUser;
    }

    public static boolean isLoggedIn(){
        return currentUser != null;
    }

    public static User getCurrentUser() {
        return AuthData.currentUser;
    }

}
