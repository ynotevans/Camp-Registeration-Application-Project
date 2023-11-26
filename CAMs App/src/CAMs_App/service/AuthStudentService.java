package CAMs_App.service;
import java.util.Map;
import CAMs_App.entity.Student;
import CAMs_App.data.AuthData;
import CAMs_App.data.Database;

/**
 * The class {@link AuthStudentService} extends from the abstract class {@link AuthService}. 
 * It authenticates serivces applicable to student users. The class contains AuthStudentService constructor and login method.
 * 
 *  @author Tony
 *  @version 1.0
 *  @since 2023-10-25
 */
public class AuthStudentService extends AuthService {
    /**
     * A {@link AuthStudentService} constructor, to create an instance of authentication service of student users.
     */
    public AuthStudentService(){};
    /**
     * Logs in the student user with userID and password
     * @param userID : The userID of the student user
     * @param password : The associated password to the userID
     * @return {@code true}, if the login is successful, {@code false} otherwise
     */
    public boolean login(String userID, String password){
        Map<String, Student> studentData = Database.getStudentsData();

        Student student = studentData.get(userID);
        
        if (authenticate(student, password) == false)
            return false;

        AuthData.setCurrentUser(student);
        return true;
    }
}
