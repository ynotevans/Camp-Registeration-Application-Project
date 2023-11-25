package CAMs_App.service;
import java.util.ArrayList;

import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;
public class CampManagementService {
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
            System.out.println("Faculty: " + student.getFaculty().toString());
            System.out.println("Position: Participants");
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
   
    
    // public static void viewCommitteeList(){
    //     Camp camp = AuthData.getCurrentCamp();
    //     ArrayList<Student> committees = camp.getCommittee();
    //     System.out.println(camp.getCampName() + "committee list: ");
    //     for(int i = 0 ; i < camp.getAttendees().size() ; i++){
    //         System.out.print("User ID: " + committees.get(i).getUserID() + "    ");
    //         System.out.println("Faculty: " + committees.get(i).getFaculty().toString());
    //     }   
    // }

}
