package CAMs_App.service;
import CAMs_App.entity.User;
import CAMs_App.data.AuthData;
import CAMs_App.data.Database;


public abstract class AuthService {

    public abstract boolean login(String UserID, String password);


    public static boolean logout(){
        AuthData.setCurrentUser(null);
        AuthData.setCurrentCamp(null);
        Database.writeData();
        return true;
    }

    protected static boolean authenticate(User user, String password){
        if (user == null)
            return false;
        
        if (!user.getPassword().equals(password))
            return false;
        return true;
    }
}
