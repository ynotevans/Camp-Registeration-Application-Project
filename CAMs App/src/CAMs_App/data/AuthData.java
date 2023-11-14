package CAMs_App.data;
import CAMs_App.entity.*;



public class AuthData {
    private static User currentUser;
    private AuthData(){};

    public static void setCurrentUser(User currentUser) {
        if(currentUser instanceof Student)  AuthData.currentUser = (Student)currentUser;
        else     AuthData.currentUser = (Staff)currentUser;
    }

    public static boolean isLoggedIn(){
        return currentUser != null;
    }

    public static User getCurrentUser() {
        return AuthData.currentUser;
    }

}
