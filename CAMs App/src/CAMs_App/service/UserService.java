package CAMs_App.service;
import CAMs_App.entity.User;
import CAMs_App.data.AuthData;
/**
 * The class {@link UserService} provides method to change user's password
 * 
 *  @author Tony
 *  @version 1.0
 *  @since 2023-10-25
 */
public class UserService {
    /**
     * Changes the user's password
     * @param oldPassword : The old password to be checked
     * @param newPassword : The new password to be set if the old password is correct
     * @return {@code true} if change of password is successful, {@code false} otherwise
     */
    public static boolean changePassword(String oldPassword, String newPassword){
        
        User user = AuthData.getCurrentUser();
        if (!oldPassword.equals(user.getPassword()))
            return false;
        else{
            user.setPassword(newPassword);
            return true;
        }
    }
}
