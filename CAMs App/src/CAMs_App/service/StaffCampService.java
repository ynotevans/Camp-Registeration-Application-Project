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


    public static boolean deleteCamp(String campName){
        Camp camp = DatabaseService.getCamp(campName);

        if(camp.getAttendees().size()!= 0) return false;
        else{
            Database.getCampData().remove(campName);
            return true;
        }

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

    
    //list of student attending
    public static void generateStudentListReport(){
        Camp camp = AuthData.getCurrentCamp();
       ArrayList<Student> attendees  = camp.getCommittee();
        HelperService.viewCamp(camp);
        System.out.println(" ");
        System.out.println("Number of participants: " + camp.getAttendees().size());
        System.out.println("Participants list");
        for(int i = 0 ; i < camp.getAttendees().size() ; i++){
            Student student = attendees.get(i);
            System.out.println("Name: " + student.getName());
            System.out.println("Student ID: " + student.getUserID());
            System.out.println("Position: Participants");
        }
           
      
    }

    // generate committee performance report
    public static void commiteePerformanceReport(){
       Camp camp = AuthData.getCurrentCamp();
       ArrayList<Student> comm  = camp.getCommittee();

       for(int i = 0 ; i < comm.size() ; i++){
        Student student = comm.get(i);
            System.out.println("Name: " + student.getName());
            System.out.println("Student ID: " + student.getUserID());
            System.out.println("Position: " + student.getCampComm().getPosition());
            System.out.println("# of suggestions submitted: " + student.getCampComm().getSuggestion().size());
        }

    } 
    
   
    //enquiries report
    public static void enquiriesReport(){
        Camp camp = AuthData.getCurrentCamp();
        System.out.println("Enquiries report of " + camp.getCampName());

        System.out.println("New Enquiries");
        EnquiriesService.viewNewEnquiries();
        
        System.out.println("Processed Enquiries");
        EnquiriesService.viewProcessedEnquiries();
    }
}