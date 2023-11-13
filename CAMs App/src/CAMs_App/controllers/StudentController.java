package CAMs_App.controllers;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.*;
import java.util.HashMap;
import java.util.Map;

public class StudentController extends UserController {
    User currentUser = AuthData.getCurrentUser();
    

	public void viewAvailableCamp(){
        

    }

    public void joinAsAttendee(String campName){}

    public void joinAsCommittee(String campName){}

    public void withdrawCamp(String campName){}
    
    public void viewRegisteredCamp(){}

    public void viewEnquiry(){}

    public void createEnquiry(){}

    public void editEnquiries(){}

    public void deleteEnquiries(){}
}
