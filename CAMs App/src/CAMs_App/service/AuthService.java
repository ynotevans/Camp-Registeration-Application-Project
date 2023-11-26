package CAMs_App.service;
import CAMs_App.entity.User;
import CAMs_App.data.AuthData;
import CAMs_App.data.Database;

/**
 * The abstract class {@link AuthService} used as a base for authentication services in this application.
 * The class contains static method for logging out a user and a protected method for authentication.
 * 
 *  @author Tony
 *  @version 1.0
 *  @since 2023-10-25
 */
public abstract class AuthService {
    /**
     * Logs in the user with provided userID and password
     * @param UserID : User ID to log in
     * @param password : Associated password with the User ID
     * @return {@code true} upon successful login, else {@code false}
     */
    public abstract boolean login(String UserID, String password);
    
    /**
     * Logs out the current user by setting the currentuser and current camp to null, writing the data to database.
     * @return {@code true} after successful logout
     */
    public static boolean logout(){
        AuthData.setCurrentUser(null);
        AuthData.setCurrentCamp(null);
        Database.writeData();
        return true;
    }
    /**
     * Authenticates the user by comparing the entered password with the user's stored password
     * @param user : The authenticated user
     * @param password : Compare and checks with the user's stored password
     * @return {@code true} if the stored and entered password are the same, {@code false} otherwise
     */
    protected static boolean authenticate(User user, String password){
        if (user == null)
            return false;
        
        if (!user.getPassword().equals(password))
            return false;
        return true;
    }
}
