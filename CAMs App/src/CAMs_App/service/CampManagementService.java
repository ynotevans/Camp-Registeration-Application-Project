package CAMs_App.service;
import java.util.ArrayList;

import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;
public class CampManagementService {
    public static void viewAttendeesList(String campName){
        Camp camp = DatabaseService.getCamp(campName);
        ArrayList<Student> attendees = camp.getAttendees();
        System.out.println(campName + "attendees list: ");
        for(int i = 0 ; i < camp.getAttendees().size() ; i++){
            System.out.print("User ID: " + attendees.get(i).getUserID() + "    ");
            System.out.println("Faculty: " + attendees.get(i).getFaculty().toString());
        }
        
    }
    public static void viewCommitteeList(String campName){
        Camp camp = DatabaseService.getCamp(campName);
        ArrayList<Student> committees = camp.getCommittee();
        System.out.println(campName + "committee list: ");
        for(int i = 0 ; i < camp.getAttendees().size() ; i++){
            System.out.print("User ID: " + committees.get(i).getUserID() + "    ");
            System.out.println("Faculty: " + committees.get(i).getFaculty().toString());
        }   
    }
}
