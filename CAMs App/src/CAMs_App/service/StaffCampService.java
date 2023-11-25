package CAMs_App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import CAMs_App.data.*;
import CAMs_App.entity.*;


public class StaffCampService extends CampManagementService{
    Scanner sc = new Scanner(System.in);

    
    public static void addNewCampToDB (Camp camp){
        Database.getCampData().put(camp.getCampName(), camp);
    }

    public static void editCampNameinDB (String oldCampName, Camp camp){
        Database.getCampData().put(camp.getCampName(),camp);
        Database.getCampData().remove(oldCampName);
    }


    public static void deleteCamp(Camp camp){
        Database.getCampData().remove(camp.getCampName());
    }

    public static void toggleVisibility(Camp camp , boolean visibility){
      camp.setVisibility(visibility);
    }

    // view camp
    public static void viewAllCamps(){
        List<Camp> sortArr= HelperService.filter();

        for (Camp camp : sortArr){
            HelperService.viewCamp(camp);
        }
    }

    // see staff created camps
    public static void viewCampsCreated(String userID){
        Map<String, Camp> camp1 = Database.getCampData(); 

        for (Camp camp : camp1.values()){
            if(camp.getStaffInCharge().equals(userID)){
                HelperService.viewCamp(camp);
            }
                
        }
    }
    
    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}

    
   

    // generate committee performance report
    public static void commiteePerformanceReport(){
       Camp camp = AuthData.getCurrentCamp();
       ArrayList<Student> comm  = camp.getCommittee();

       for(int i = 0 ; i < comm.size() ; i++){
        Student student = comm.get(i);
            System.out.println("Name: " + student.getName());
            System.out.println("Student ID: " + student.getUserID());
            System.out.println("Position: " + student.getCampComMem().getPosition());
            System.out.println("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
        }

    } 
    
}