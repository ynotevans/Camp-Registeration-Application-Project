package CAMs_App.service;
import java.util.Map;
import CAMs_App.entity.Student;
import CAMs_App.data.AuthData;
import CAMs_App.data.Database;


public class AuthStudentService extends AuthService {
   
    public AuthStudentService(){};

    public boolean login(String userID, String password){
        Map<String, Student> studentData = Database.getStudentsData();

        Student student = studentData.get(userID);
        
        if (authenticate(student, password) == false)
            return false;

        AuthData.setCurrentUser(student);
        return true;
    }
}
