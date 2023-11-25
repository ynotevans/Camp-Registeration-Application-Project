package CAMs_App.service;
import CAMs_App.entity.User;
import CAMs_App.data.AuthData;

public class UserService {

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
