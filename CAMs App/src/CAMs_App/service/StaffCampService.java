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

    
    // generate attendance report
    public static void generateAttendanceReport(Camp camp){
        System.out.println("Number of participants: " + camp.getAttendees().size());
        System.out.println("Participants list");
        for(int i = 0 ; i < camp.getAttendees().size() ; i++)
            System.out.println(camp.getAttendees().get(i).getUserID());
           
        System.out.println("Number of committee: " + camp.getCommittee().size());
        System.out.println("Committee list: ");
        for(int i=0;i>camp.getCommittee().size();i++)
            System.out.println(camp.getCommittee().get(i).getUserID());
    }

    // generate performance report
    public static void generatePerformanceReport(Camp camp){
        for(int i=0;i<camp.getAttendees().size();i++){
            System.out.println(camp.getAttendees().get(i).getUserID()+":"
            +camp.getAttendees().get(i).getPoints());
        }
    } 
    
    //generate attendees list
    public static void generateAttendeeList(Camp camp){
        ArrayList<Student> attendees = camp.getAttendees();

        for(int i = 0 ; i < attendees.size() ; i++){
            System.out.println("User ID: " + attendees.get(i).getUserID());
            System.out.println("Faculty: " + attendees.get(i).getFaculty());
        }
    }

     //generate committee list
    public static void generateCommitteeList(Camp camp){
        ArrayList<Student> committee = camp.getCommittee();

        for(int i = 0 ; i < committee.size() ; i++){
            System.out.print("User ID: " + committee.get(i).getUserID());
            System.out.print("  Faculty: " + committee.get(i).getFaculty());

        }
    }
}