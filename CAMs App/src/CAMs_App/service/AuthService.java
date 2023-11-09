package CAMs_App.service;
import CAMs_App.entity.User;
import CAMs_App.data.AuthData;


public abstract class AuthService {

    public abstract boolean login(String UserID, String password);

    public boolean logout(){
        AuthData.setCurrentUser(null);
        return true;
    }

    protected boolean authenticate(User user, String password){
        if (user == null)
            return false;
        
        if (!user.getPassword().equals(password))
            return false;
            
        return true;
    }
}
