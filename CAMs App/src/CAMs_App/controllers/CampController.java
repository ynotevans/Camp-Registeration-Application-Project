package CAMs_App.controllers;
import CAMs_App.entity.Camp;
import CAMs_App.entity.CampInfo;
import CAMs_App.entity.Staff;
import CAMs_App.data.Database;

import java.util.*;
import java.time.LocalDateTime;

public class CampController extends StaffController {
    Scanner sc = new Scanner(System.in);
    Camp camp = new Camp();
    CampInfo campInfo = new CampInfo();
    Database db = new Database();


    public CampController(){
        super();
    };
    
    public boolean isCampFull(Camp camp){
        if (camp.getRemainingSlot()==0){
            return true;
        }
        return false;
    }

    public boolean checkRegisterDeadline(Camp camp){
        // get the current date time
        LocalDateTime today = LocalDateTime.now();

        // check if deadline is over 
        if (camp.getRegCloseDate().isBefore(today)){
            return true;
        }
        return false;
    }

    public void printCamp(Camp camp){
        // prints out the camp info
         System.out.println("Camp Name: " + camp.getCampName() +
                            "\nCamp Date: " + camp.getCampDate() +
                            "\nCamp Registeration Closing Date: " + camp.getRegCloseDate() +
                            "\nCamp Location: " + camp.getLocation() +
                            "\nCamp Total Slots" + camp.getTotalSlots() +
                            "\nCamp Committee Slots left: " + camp.getCampCommitteeSlots() +
                            "\nCamp Description: " + camp.getDescription() +
                            "\nCamp's Staff In Charge: " + camp.getStaffInCharge() +
                            "\nCamp Duration: " + camp.getNumberOfCampDays() + 
                            "\n Remaining Slots for the camp: " + camp.getRemainingSlot()); 
    }

    public boolean createCamp(Camp camp){
        if (Database.findCamp(camp, db.getCampDataBase())==false) {
            Database.addCampToDatabase(camp, db.getCampDataBase());
            return true;
        }
        return false;
    }

    public boolean deleteCamp(Camp camp){
        if (Database.findCamp(camp,db.getCampDataBase())==true){
            Database.removeCampFromDatabase(camp,db.getCampDataBase());
            return true;    
        }
        return false;
    }

    public boolean editCamp(Camp camp){
        // super.editCamp(camp);
        return true;
    }

    // view functions

    public void viewCamps(){
        System.out.println(); // prints out all the camp names
    }

    public void viewStaffCreatedCamp(String staffID){
        // System.out.println(Camp.getCampName());
    }

    public void viewAttendeesList(){
        
    }

    public void viewCommitteeList(){
        
    }

    public void updateRemainingSlot(){

    }

    public void checkWithdrawBefore(){

    }
}


