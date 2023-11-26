package CAMs_App.service;

import java.util.Map;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.Staff;
/**
 * The class {@link AuthStaffService} extends from the abstract class {@link AuthService}. 
 * It authenticates services applicable to staff users. The class contains AuthStaffService constructor and login method.
 * 
 *  @author Tony
 *  @version 1.0
 *  @since 2023-10-25
 */
public class AuthStaffService extends AuthService {
    // /**
    //  * A {@link AuthStaffService} constructor, to create an instance of authentication service of staff users.
    //  */
    public AuthStaffService(){};
    /**
     * Logs in the staff user with userID and password
     * @param userID : The userID of the staff user
     * @param password : The associated password to the userID
     * @return {@code true}, if the login is successful, {@code false} otherwise
     */
    public boolean login(String userID, String password){
        Map<String, Staff> staffData = Database.getStaffData();

        Staff staff = staffData.get(userID);
        
        if (authenticate(staff, password) == false)
            return false;

        AuthData.setCurrentUser(staff);
        return true;
    }
}
