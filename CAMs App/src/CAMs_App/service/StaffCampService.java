package CAMs_App.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import CAMs_App.data.*;
import CAMs_App.entity.*;

/**
 * The class {@link StaffCampService} is responsible in managing the camp-related functionalities for staff users. 
 * This class also extends from {@link CampManagementService} to obtain the various reports.
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
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
    public static void viewAllCamps(){
        List<Camp> sortArr= HelperService.filter();

        if(sortArr.size() == 0)
            ColouredTextPrinter.printRed("Currently no camp is creted!!!");
        else{
            for (Camp camp : sortArr){
                HelperService.viewCamp(camp);
            }
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
       System.out.println("Filtered by Faculty: " + faculty +"\n");
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

    /**
     * Method to generate committee performance report in TXT format
    */
    public static void committeePerformanceinTXT(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Student> comm  = camp.getCommittee();
        String filePath = "CAMs App/report/Committee_Performance_" + camp.getCampName()+ ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Committee Performance Report for " + camp.getCampName() + " : ");
            writer.newLine(); // Add a new line
            writer.newLine(); // Add a new line

            if(comm.isEmpty()){
                writer.write("No committee sign up for this camp.");
                writer.newLine(); // Add a new line
            }
            for(int i = 0 ; i < comm.size() ; i++){
                Student student = comm.get(i);
                writer.write("----------------------------------------------");
                writer.newLine();
                writer.write("Name: " + student.getName());
                writer.newLine();
                writer.write("Student ID: " + student.getUserID());
                writer.newLine();
                writer.write("Position: " + student.getCampComMem().getPosition());
                writer.newLine();
                writer.write("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
                writer.newLine();
                writer.write("Faculty: " + student.getFaculty());
                writer.newLine();
                writer.newLine(); // Add a new line
            }

            ColouredTextPrinter.printYellow("Succesfully generated .txt file ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Generate a filtered committee performance report based on faculty in TXT format
     * @parm faculty : Filtered by the faculty
     */ 
    public static void committeePerformanceinTXT(String faculty){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Student> comm  = camp.getCommittee();
        String filePath = "CAMs App/report/Filtered_Committee_Performance_" + camp.getCampName()+ ".txt";
        int count = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Committee Performance Report for " + camp.getCampName() + " : ");
            writer.newLine(); // Add a new line
            writer.newLine(); // Add a new line

            writer.write("Filtered by Faculty: " + faculty +"\n");
            writer.newLine(); // Add a new line

            if(comm.isEmpty()){
                writer.write("No committee sign up for this camp.");
                writer.newLine(); // Add a new line
            }
            for(int i = 0 ; i < comm.size() ; i++){
                Student student = comm.get(i);
                if(student.getFaculty().equals(faculty)){
                    count++;
                    writer.write("----------------------------------------------");
                    writer.newLine();
                    writer.write("Name: " + student.getName());
                    writer.newLine();
                    writer.write("Student ID: " + student.getUserID());
                    writer.newLine();
                    writer.write("Position: " + student.getCampComMem().getPosition());
                    writer.newLine();
                    writer.write("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
                    writer.newLine();
                    writer.write("Faculty: " + student.getFaculty());
                    writer.newLine();
                    writer.newLine(); // Add a new line
                }
            }

            if(count == 0) {
                    writer.write("No committee from " + faculty);
                    writer.newLine();
            }
            ColouredTextPrinter.printYellow("Succesfully generated .txt file ");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Generate camp report in TXT format
     * the report consists of committee performance list, attendees list, enquiries list
     * enquiries list is split into two groups, processed and unprocessed   
     */
    public static void campReportTXT(){
        Camp camp = AuthData.getCurrentCamp();
        String filePath = "CAMs App/report/CampReport_" + camp.getCampName()+ ".txt";
    
        //for committee
        ArrayList<Student> comm  = camp.getCommittee();

        //for attendees
        ArrayList<Student> attendees  = camp.getAttendees();

        //for enquiries
        ArrayList <Enquiries> qList1 = AuthData.getCurrentCamp().getEnquiryList();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Camp Report for " + camp.getCampName() + " : ");
            writer.newLine(); 
            writer.newLine(); 

            writer.write("_____________________________________________________________________________________");
            writer.newLine(); 
            writer.newLine(); 
            writer.write("Committee List: ");
            writer.newLine(); 
            writer.newLine(); 
            

            if(comm.isEmpty()){
                writer.write("No committee sign up for this camp.");
                writer.newLine(); 
            }
            for(int i = 0 ; i < comm.size() ; i++){
                Student student = comm.get(i);
                writer.write("----------------------------------------------");
                writer.newLine();
                writer.write("Name: " + student.getName());
                writer.newLine();
                writer.write("Student ID: " + student.getUserID());
                writer.newLine();
                writer.write("Position: " + student.getCampComMem().getPosition());
                writer.newLine();
                writer.write("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
                writer.newLine();
                writer.write("Faculty: " + student.getFaculty());
                writer.newLine();
                writer.newLine(); 
            }

            writer.write("_____________________________________________________________________________________");
            writer.newLine(); 
            writer.newLine(); 
            writer.write("Attendees Lists: ");
            writer.newLine(); 
            writer.newLine(); 
            

            if(attendees.isEmpty()){
                writer.write("No student sign up for the camp");
                writer.newLine(); // Add a new line
            }
            for(int i = 0 ; i < camp.getAttendees().size() ; i++){
                Student student = attendees.get(i);
                writer.write("----------------------------------------------");
                writer.newLine();
                writer.write("Name: " + student.getName());
                writer.newLine();
                writer.write("Student ID: " + student.getUserID());
                writer.newLine();
                writer.write("Faculty: " + student.getFaculty().toString());
                writer.newLine();
                writer.write("Position: Participants");
                writer.newLine();
                writer.newLine(); // Add a new line
            }

            writer.write("_____________________________________________________________________________________");
            writer.newLine(); 
            writer.newLine(); 
            writer.write("Enquiries List: ");
            writer.newLine(); 
            writer.newLine(); 
            

            writer.write("New Enquiries: ");
            writer.newLine(); // Add a new line
            if(!EnquiriesService.hasNewEnquiries()){
                writer.write("--- No new enquiry for this camp ---");
                writer.newLine(); // Add a new line
            }
            else{
                ArrayList <Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
                for(int i = 0 ; i < qList.size() ; i++){
                    Enquiries q = qList.get(i);
                    if(!q.getProcessed()){
                        writer.write("----------------------------------------------");
                        writer.newLine();
                        writer.write("EnquiriesID: " + (i+1));
                        writer.newLine();
                        writer.write("Inquirer:" + q.getInquirer());
                        writer.newLine();
                        writer.write("Enquiry: " + q.getEnquiry());
                        writer.newLine();
                        writer.newLine();
                        writer.write("Respondent: " + q.getAnswerer());
                        writer.newLine();
                        writer.write("Answer: " + q.getAnswer());
                        writer.newLine();
                        writer.newLine();
                    }
                }
            }

            writer.write("Processed Enquiries:");
            writer.newLine();
            int count = 0;
            for(int i = 0 ; i < qList1.size() ; i++){
                Enquiries q = qList1.get(i);
                if(q.getProcessed()){
                    count++;
                    writer.write("----------------------------------------------");
                    writer.newLine();
                    writer.write("EnquiriesID: " + (i+1));
                    writer.newLine();
                    writer.write("Inquirer:" + q.getInquirer());
                    writer.newLine();
                    writer.write("Enquiry: " + q.getEnquiry());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Respondent: " + q.getAnswerer());
                    writer.newLine();
                    writer.write("Answer: " + q.getAnswer());
                    writer.newLine();
                    writer.newLine();
                }
            }
            if(count == 0){
                writer.write("--- No processed enquiry for this camp ---");
                writer.newLine();
            }
            ColouredTextPrinter.printYellow("Succesfully generated .txt file ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
  
     /*Generate camp report in TXT format
     * The report consists of three components: committee performance list, attendees list, enquiries list
     * enquiries list is split into two groups, processed and unprocessed  
     * @parm faculty : All three components are filtered by the faculty 
     */
    public static void campReportTXT(String faculty){
        Camp camp = AuthData.getCurrentCamp();
        String filePath = "CAMs App/report/Filtered_CampReport_" + camp.getCampName()+ ".txt";
    
        //for committee
        ArrayList<Student> comm  = camp.getCommittee();

        //for attendees
        ArrayList<Student> attendees  = camp.getAttendees();
        int count=0;

        //for enquiries
        ArrayList<Enquiries> qList = camp.getEnquiryList();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Camp Report for " + camp.getCampName() + " : ");
            writer.newLine(); 
            writer.newLine(); 

            writer.write("Filtered by Faculty: " + faculty +"\n");
            writer.newLine();
            writer.newLine();

            writer.write("_____________________________________________________________________________________");
            writer.newLine(); 
            writer.newLine(); 
            writer.write("Committee List: ");
            writer.newLine(); 
            writer.newLine(); 

            if(comm.isEmpty()){
                writer.write("No committee sign up for this camp.");
                writer.newLine(); // Add a new line
            }
            for(int i = 0 ; i < comm.size() ; i++){
                Student student = comm.get(i);
                if(student.getFaculty().equals(faculty)){
                    count++;
                    writer.write("----------------------------------------------");
                    writer.newLine();
                    writer.write("Name: " + student.getName());
                    writer.newLine();
                    writer.write("Student ID: " + student.getUserID());
                    writer.newLine();
                    writer.write("Position: " + student.getCampComMem().getPosition());
                    writer.newLine();
                    writer.write("# of suggestions submitted: " + student.getCampComMem().getSuggestion().size());
                    writer.newLine();
                    writer.write("Faculty: " + student.getFaculty());
                    writer.newLine();
                    writer.newLine(); // Add a new line
                }
            }

            if(count == 0) {
                    writer.write("No committee from " + faculty);
                    writer.newLine();
            }

            writer.write("_____________________________________________________________________________________");
            writer.newLine(); 
            writer.newLine(); 
            writer.write("Attendees Lists: ");
            writer.newLine(); 
            writer.newLine(); 
            

            if(attendees.isEmpty()){
                writer.write("No student sign up for the camp");
                writer.newLine(); // Add a new line
            }
             for(int i = 0 ; i < camp.getAttendees().size() ; i++){
                Student student = attendees.get(i);
                if(student.getFaculty().equals(faculty)){
                    count++;
                    writer.write("----------------------------------------------");
                    writer.newLine();
                    writer.write("Name: " + student.getName());
                    writer.newLine();
                    writer.write("Student ID: " + student.getUserID());
                    writer.newLine();
                    writer.write("Faculty: " + student.getFaculty().toString());
                    writer.newLine();
                    writer.write("Position: Participants");
                    writer.newLine();
                    writer.newLine(); // Add a new line
                }
            }
            if(count == 0) {
                    writer.write("No attendees from " + faculty);
                    writer.newLine();
            }

            writer.write("_____________________________________________________________________________________");
            writer.newLine(); 
            writer.newLine(); 
            writer.write("Enquiries List: ");
            writer.newLine(); 
            writer.newLine(); 
            

            writer.write("New Enquiries: ");
            writer.newLine(); // Add a new line
            count = 0;
            for(int i = 0 ; i < qList.size() ; i++){
                Enquiries q = qList.get(i);
                Student student = DatabaseService.getStudent(q.getInquirer());
                String fac = student.getFaculty();
                if(fac == faculty && !q.getProcessed()){
                    writer.write("----------------------------------------------");
                    writer.newLine();
                    writer.write("EnquiriesID: " + (i+1));
                    writer.newLine();
                    writer.write("Inquirer:" + q.getInquirer());
                    writer.newLine();
                    writer.write("Enquiry: " + q.getEnquiry());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Respondent: " + q.getAnswerer());
                    writer.newLine();
                    writer.write("Answer: " + q.getAnswer());
                    writer.newLine();
                    writer.newLine();
                    count++;
                }
            }
            if(count == 0){
                writer.write("--- No new enquiry from student of " + faculty.toUpperCase()+ " ---");
                writer.newLine();
            } 

            count = 0;
            writer.write("Processed Enquiries:");
            writer.newLine();
            for(int i = 0 ; i < qList.size() ; i++){
                Enquiries q = qList.get(i);
                Student student = DatabaseService.getStudent(q.getInquirer());
                String fac = student.getFaculty();
                if(fac == faculty && q.getProcessed()){
                    writer.write("----------------------------------------------");
                    writer.newLine();
                    writer.write("EnquiriesID: " + (i+1));
                    writer.newLine();
                    writer.write("Inquirer:" + q.getInquirer());
                    writer.newLine();
                    writer.write("Enquiry: " + q.getEnquiry());
                    writer.newLine();
                    writer.newLine();
                    writer.write("Respondent: " + q.getAnswerer());
                    writer.newLine();
                    writer.write("Answer: " + q.getAnswer());
                    writer.newLine();
                    writer.newLine();
                    count++;
                }
            }
            if(count == 0){
                writer.write("--- No processed enquiries from student of " + faculty.toUpperCase()+" ---");
                writer.newLine();
            } 
            ColouredTextPrinter.printYellow("Succesfully generated .txt file ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}