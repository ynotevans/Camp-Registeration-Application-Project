package CAMs_App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import CAMs_App.data.*;
import CAMs_App.entity.*;

/**
 * The class {@link StaffCampService} is responsible in managing the camp-related functionalities for staff users. 
 * This class also extends from {@link CampManagementService} to obtain the various reports.
 */
public class StaffCampService extends CampManagementService{
    Scanner sc = new Scanner(System.in);

    /**
     * Adds a new camp to the Database
     * @param camp : New camp to be added
     */   
    public static void addNewCampToDB (Camp camp){
        Database.getCampData().put(camp.getCampName(), camp);
    }
    /**
     * Edits camp name of an existing camp in the Database
     * @param oldCampName : Previously stored camp name in Database
     * @param camp : Camp to be updated
     */  
    public static void editCampNameinDB (String oldCampName, Camp camp){
        Database.getCampData().put(camp.getCampName(),camp);
        Database.getCampData().remove(oldCampName);
    }

    /**
     * Deletes an existing camp from the Database
     * @param camp : Camp to be deleted
     */  
    public static void deleteCamp(Camp camp){
        Database.getCampData().remove(camp.getCampName());
    }
    /**
     * Toggles the vivisbility of a specified camp
     * @param camp : Camp to be updated
     * @param visibility : Toggles visibility of the camp
     */  
    public static void toggleVisibility(Camp camp , boolean visibility){
      camp.setVisibility(visibility);
    }
    /**
     * Displays the information about all camps
     */  
    // view camp
    public static void viewAllCamps(){
        List<Camp> sortArr= HelperService.filter();

        for (Camp camp : sortArr){
            HelperService.viewCamp(camp);
        }
    }
    /**
     * Displays information regarding the camps created by the staff user
     * @param userID : The userID of the staff user
     */  
    // see staff created camps
    public static void viewCampsCreated(String userID){
        Map<String, Camp> camp1 = Database.getCampData(); 
        List<Camp> sortArr= HelperService.sortCampsByName(camp1);

        for (Camp camp : sortArr){
            if(camp.getStaffInCharge().equals(userID)){
                HelperService.viewCamp(camp);
            }
        }
    }
     /**
     * Adds point to the specified student
     * @param student : The student whose points will be updated
     */     
    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}

    
   
    /**
     * Generates committee performance report for the current camp
     */  
    // generate committee performance report
    public static void commiteePerformanceReport(){
       Camp camp = AuthData.getCurrentCamp();
       ArrayList<Student> comm  = camp.getCommittee();
        if(comm.isEmpty()){
            System.out.println("No committee sign up for this camp");
        }
       for(int i = 0 ; i < comm.size() ; i++){
        Student student = comm.get(i);
            System.out.println("Name: " + student.getName());
            System.out.println("Student ID: " + student.getUserID());
            System.out.println("Position: " + student.getCampComMem().getPosition());
            System.out.println("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
        }

    } 



    /**
     * Generate a filtered committee performance report based on faculty
     * @parm faculty : Filtered by the faculty
     */  
    public static void commiteePerformanceReport(String faculty){
       Camp camp = AuthData.getCurrentCamp();
       ArrayList<Student> comm  = camp.getCommittee();
        int count = 0;
       for(int i = 0 ; i < comm.size() ; i++){
            Student student = comm.get(i);
            if(student.getFaculty().equals(faculty)){
                System.out.println("Name: " + student.getName());
                System.out.println("Student ID: " + student.getUserID());
                System.out.println("Position: " + student.getCampComMem().getPosition());
                System.out.println("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
                count++;
            }
        }
        if(count == 0) {
            System.out.println("No committee from " + faculty);
        }
        

    }
    
}