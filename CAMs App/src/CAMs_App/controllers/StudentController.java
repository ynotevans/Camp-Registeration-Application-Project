package CAMs_App.controllers;

import CAMs_App.boundary.CampComMenu;
import CAMs_App.boundary.StudentMenu;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.HelperService;
import CAMs_App.service.StudentCampService;


public class StudentController extends UserController {
    static Student currentUser = (Student)AuthData.getCurrentUser();

	public boolean viewAvailableCamp(){
        if (StudentCampService.viewAvailableCamps()==0){
            return false;
        }
        return true;
    }

    public void joinAsAttendee(){
        String campName = AuthData.getCurrentCamp().getCampName();
        Camp camp = DatabaseService.getCamp(campName);

        if(camp.getRemainingSlot() != 0)
            StudentCampService.registerAsAttendee(campName);
    }

    public void joinAsCommittee(){
        String campName = AuthData.getCurrentCamp().getCampName();

        Camp camp = DatabaseService.getCamp(campName);
        
        if(camp.getCommittee().size() != camp.getCampCommitteeSlots())
            StudentCampService.registerAsCommittee(campName);
    }

    public void withdrawCamp(){
        String campName = AuthData.getCurrentCamp().getCampName();

        System.out.println("Are you sure you want to withdraw from this camp? (Y/N)");

        char ans = sc.next().charAt(0);

        if(ans == 'Y'){
            if(currentUser.getIsComittee()){
                StudentCampService.withdrawCamp(campName, currentUser, true);
            }
            else{
                StudentCampService.withdrawCamp(campName, currentUser, false);
            }
        }
        else{
            System.out.println("cancel withdraw...");
        }

        
    }
    
    public boolean viewRegisteredCamp(){
        if (StudentCampService.viewRegisteredCamp()==0){
            return false;
        }
        return true;
    }

    public void viewEnquiry(){}

    public void createEnquiry(){}

    public void editEnquiries(){}

    public void deleteEnquiries(){}


    public void switchMode(int currentMode){
        HelperService.clearScreen();
        //from student switch to camp committee
        if(currentMode == 1){
            if(currentUser.getIsComittee()== true){    //need to change the condition
                CampComMenu campComMenu = new CampComMenu();
                campComMenu.viewApp();
            }
            else{
                System.out.println("You are not a committe member of any camp");
                System.out.println("Unable to switch mode !!!\n");
                StudentMenu studentMenu = new StudentMenu();
                studentMenu.printMenu();
            }
        }
        else{
            StudentMenu studentMenu = new StudentMenu();
            studentMenu.viewApp();
        }

    }
}
