package CAMs_App.service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Enquiries;
import CAMs_App.entity.Student;
/**
 * The{@link CampManagementService} class provides methods to generate reports that can be called by both
 * {@link StaffController} and {@link CampComController}
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class CampManagementService {
    /**
     * Generates list of students for the current camp and display details such as:
     * <ul>
     * <li>Number of participants
     * <li>Pariticipants list
     * <li>Name
     * <li>Student ID
     * <li>Faculty
     * <li>Postion
     * </ul>
     */
    public static void generateStudentListReport(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Student> attendees  = camp.getAttendees();

        if(attendees.isEmpty()){
            ColouredTextPrinter.printRed("No student sign up for the camp");
            return;
        }
        
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
    /**
     * Generates a list of students for the current camp, filtered by faculty, and display details such as:
     * <ul>
     * <li>Number of participants
     * <li>Pariticipants list
     * <li>Name
     * <li>Student ID
     * <li>Faculty
     * <li>Postion
     * </ul>
     */
    public static void generateStudentListReport(String faculty){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Student> attendees  = camp.getAttendees();
        int count = 0;
        
        System.out.println(" ");
        System.out.println("Number of participants: " + camp.getAttendees().size()+ "\n");
        System.out.println("Participants list: ");
        for(int i = 0 ; i < camp.getAttendees().size() ; i++){
            Student student = attendees.get(i);
            if(student.getFaculty().equals(faculty)){
                count ++;
                System.out.println("Name: " + student.getName());
                System.out.println("Student ID: " + student.getUserID());
                System.out.println("Faculty: " + student.getFaculty());
                System.out.println("Position: Participants");

            }
        }
    if(count == 0){
        ColouredTextPrinter.printRed("No attendees from " + faculty);
    }
    }


    /**
     * Generates the enquiries report for the current camp
     * Displays the new and processed enquiries respectively
     */
    public static void enquiriesReport(){
        Camp camp = AuthData.getCurrentCamp();
        System.out.println("Enquiries report of " + camp.getCampName());

        System.out.println("New Enquiries");
        EnquiriesService.viewNewEnquiries();
        
        System.out.println("Processed Enquiries");
        EnquiriesService.viewProcessedEnquiries();
    }

     /**
     * Generates the enquiries report for the current camp
     * Displays the new and processed enquiries respectively
     * @parm faculty : Filtered by the faculty
     */
    public static void enquiriesReport(String faculty){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> qList = camp.getEnquiryList();

        System.out.println("New Enquiries\n");
        int count = 0;
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            Student student = DatabaseService.getStudent(q.getInquirer());
            String fac = student.getFaculty();
            if(fac == faculty && !q.getProcessed()){
                EnquiriesService.viewEnquiries(q);
                count++;
            }
        }
        if(count == 0) ColouredTextPrinter.printRed("No new enquiries from student of " + faculty.toUpperCase());

        count = 0;
        System.out.println("Processed enquiries\n");
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            Student student = DatabaseService.getStudent(q.getEnquiry());
            String fac = student.getFaculty();
            if(fac == faculty && q.getProcessed()){
                EnquiriesService.viewEnquiries(q);
                count++;
            }
        }
        if(count == 0) ColouredTextPrinter.printRed("No processed enquiries from student of " + faculty.toUpperCase());
    }

    
     /**
     * Generates the student list report int txt format
     */
    public static void StudentListTXT(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Student> attendees  = camp.getAttendees();
        String filePath = "CAMs App/report/StudentList_" + camp.getCampName()+ ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Camp Attendees Report for " + camp.getCampName() + " : ");
            writer.newLine(); // Add a new line
            writer.newLine(); // Add a new line

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

            ColouredTextPrinter.printYellow("Succesfully generated .txt file ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to generate student list report in TXT format
     * @parm faculty : Filtered by the faculty
     */
    public static void StudentListTXT(String faculty){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Student> attendees  = camp.getAttendees();
        String filePath = "CAMs App/report/Filtered_StudentList_" + camp.getCampName()+ ".txt";
        int count = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Camp Attendees for " + camp.getCampName() + " : ");
            writer.newLine(); // Add a new line
            writer.newLine(); // Add a new line

            writer.write("Filtered by Faculty: " + faculty +"\n");
            writer.newLine(); // Add a new line

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
            ColouredTextPrinter.printYellow("Succesfully generated .txt file ");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates the enquiries report in TXT format
     * Displays the new and processed enquiries respectively
     */
    public static void enquiriesReportTXT(){
        Camp camp = AuthData.getCurrentCamp();
        String filePath = "CAMs App/report/Enquiry_" + camp.getCampName()+ ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Enquiries report of " + camp.getCampName() + " : ");
            writer.newLine(); // Add a new line
            writer.newLine(); // Add a new line

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
            ArrayList <Enquiries> qList1 = AuthData.getCurrentCamp().getEnquiryList();
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

     /**
     * Generates the enquiries report in TXT format
     * Displays the new and processed enquiries respectively
     * @parm faculty : Both filtered by the faculty
     */
    public static void enquiriesReportTXT(String faculty){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> qList = camp.getEnquiryList();
        String filePath = "CAMs App/report/Filtered_Enquiry_" + camp.getCampName()+ ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Enquiries report of " + camp.getCampName() + " : ");
            writer.newLine(); // Add a new line
            writer.newLine(); // Add a new line

            writer.write("New Enquiries: ");
            writer.newLine(); // Add a new line
            int count = 0;
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
