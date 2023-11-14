package CAMs_App.service;
import CAMs_App.entity.User;
import CAMs_App.data.AuthData;
import CAMs_App.data.Database;

public class userService {
    public userService(){};

    public boolean changePassword(String oldPassword, String newPassword){
        User user = AuthData.getCurrentUser();
        if (!user.setPassword(oldPassword, newPassword))
            return false;

        Database.saveData();

        return true;
    }
}
