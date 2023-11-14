package CAMs_App.controllers;

import CAMs_App.boundary.CampComMenu;
import CAMs_App.boundary.StudentMenu;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.HelperService;


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


    public void switchMode(int currentMode){
        HelperService.clearScreen();
        //from student switch to camp committee
        if(currentMode == 1){
            if(currentUser.getFaculty() == "CEE"){    //need to change the condition
                CampComMenu campComMenu = new CampComMenu();
                campComMenu.viewApp();
            }
            else{
                System.out.println("You are not a committee for any camp.");
                System.out.println("Unable to switch mode !!!");
            }
        }
        else{
            StudentMenu studentMenu = new StudentMenu();
            studentMenu.viewApp();
        }

    }
}
