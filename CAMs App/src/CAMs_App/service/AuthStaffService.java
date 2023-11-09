package CAMs_App.service;

import java.util.Map;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.Staff;

public class AuthStaffService extends AuthService {
    public AuthStaffService(){};

    public boolean login(String userID, String password){
        Map<String, Staff> staffData = Database.getStaffData();

        Staff staff = staffData.get(userID);
        
        if (authenticate(staff, password) == false)
            return false;

        AuthData.setCurrentUser(staff);
        return true;
    }
}
