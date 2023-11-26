package CAMs_App.data;
import CAMs_App.entity.*;


/**
 * The {@link AuthData} class provides utility methods for managing the current
 * authenticated user within the application. It offers methods to set and get
 * the current user and camp, as well as check if the user is logged in.
 * 
 *  @author Tony
 *  @version 1.0
 *  @since 2023-10-25
 */
public class AuthData {
	/**
	 * The currently authenticated user.
	 */
    private static User currentUser;
    /**
     * The current selected camp.
     */
    private static Camp currentCamp;
    
    /**
     * Sets the current user in the AuthData.
     * 
     * @param currentUser The {@link User} instance to set as current user.
     */
    public static void setCurrentUser(User currentUser) {
        if(currentUser instanceof Student)  AuthData.currentUser = (Student)currentUser;
        else     AuthData.currentUser = (Staff)currentUser;
    }

    /**
     * Checks if the user is logged in.
     * 
     * @return {@code true} if the user is logged in, {@code false} otherwise.
     */
    public static boolean isLoggedIn(){
        return currentUser != null;
    }
    
    /**
     * Gets the currently authenticated user.
     * 
     * @return The current {@link User} instance, or {@code null} if not logged in.
     */
    public static User getCurrentUser() {
        return AuthData.currentUser;
    }
    
    /**
     * Gets the current selected camp.
     * 
     * @return The current {@link Camp} instance, or {@code null} if none selected.
     */
    public static Camp getCurrentCamp() {
        return AuthData.currentCamp;
    }

    /**
     * Sets the current selected camp.
     * 
     * @param currentCamp The {@link Camp} instance to set as current camp.
     */
    public static void setCurrentCamp(Camp currentCamp){
        AuthData.currentCamp = currentCamp;
    }

}
