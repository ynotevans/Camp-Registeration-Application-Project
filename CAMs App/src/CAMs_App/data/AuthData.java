package CAMs_App.data;
import CAMs_App.entity.*;



public class AuthData {
    private static User currentUser;
    private static Camp currentCamp;

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

    public static Camp getCurrentCamp() {
        return AuthData.currentCamp;
    }

    public static void setCurrentCamp(Camp currentCamp){
        AuthData.currentCamp = currentCamp;
    }

}
