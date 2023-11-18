package CAMs_App.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import CAMs_App.service.EnquiriesService;
import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.*;

import CAMs_App.service.DatabaseService;
public class StaffCampService extends CampManagementService{
    Scanner sc = new Scanner(System.in);  
    ArrayList<Camp>createdCamps = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

 
    public void createCamp (String campName,LocalDate dates,LocalDate registerDate,String availability,
    String location,int totalSlots,int campCommitteeSlots,String description,String staffInCharge){
        Camp camp = new Camp();
        camp.setCampName(campName);
        camp.setCampDate(dates);
        camp.setRegCloseDate(registerDate);
        camp.setLocation(location);
        camp.setCampCommitteeSlots(campCommitteeSlots);
        camp.setDescription(description);
        camp.setStaffInCharge(staffInCharge);
        camp.setVisibility(true);
    }

    public void editCamp(Camp camp){
        int choice=0;
        do
        {
            System.out.println("Editting "+camp.getCampName());
            System.out.println("1.Change camp name");
            System.out.println("2.Change camp dates");
            System.out.println("3.Change registration date");
            System.out.println("4.Open camp to own school or whole NTU");
            System.out.println("5.Edit camp location");
            System.out.println("6.Edit number of camp committee slots");
            System.out.println("7.Edit camp description");
            System.out.println("8.Quit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Edit camp name");
                    System.out.println("Change camp name to");
                    camp.setCampName(sc.next());
                    System.out.println("Camp name changed in to "+camp.getCampName());
                    break;
                case 2:
                    System.out.println("Edit camp dates");
                    System.out.println("Enter starting date in dd-mm-yyyy format:");
                    String start = sc.next();
                    System.out.println("Enter ending date in dd-mm-yyyy format:");
                    String end = sc.next();
                    LocalDate startDateTime = LocalDate.parse(start,formatter);
                    System.out.println(startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    LocalDate endDateTime = LocalDate.parse(end,formatter);
                    System.out.println(endDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    int numberofDays = (int)ChronoUnit.DAYS.between(startDateTime, endDateTime);
                    camp.setNumberOfCampDays(numberofDays);
                    camp.setCampDate(startDateTime);
                    break;
                case 3:
                    System.out.println("Edit camp registeration closing date");
                    System.out.println("Enter new closing date:");
                    LocalDate regDateTime = LocalDate.parse(sc.next(),formatter);
                    camp.setRegCloseDate(regDateTime);
                    System.out.println("Camp new closing date: "+camp.getRegCloseDate());
                    break;
                case 4:
                    System.out.println("Edit camp to open to own school or whole NTU");
                    System.out.println("1.Open to School\n2.Open to NTU\nEnter(1/2):");
                    sc.next();
                    break;
                case 5:
                    System.out.println("Edit camp location");
                    System.out.println("Enter camp's new location:");
                    camp.setLocation(sc.next());
                    System.out.println("Camp location changed to: "+camp.getLocation());                    
                    break;
                case 6:
                    System.out.println("Edit camp committee slots(up to max 10");
                    System.out.println("Set number of camp commitee slots");
                    camp.setCampCommitteeSlots(sc.nextInt());
                    System.out.println("The camp committee slots changed to: "+camp.getCampCommitteeSlots());
                    break;
                case 7:
                    System.out.println("Edit camp description");
                    System.out.println("Enter new camp description");
                    camp.setDescription(sc.next());
                    System.out.println("Camp description has been changed to\n"+camp.getDescription());
                    break;       
                case 8:
                    System.out.println("Exitting camp edits.");
                    break;    
                default:
                    System.out.println("Re-enter choice");
                    break;
            }
        }while(choice!=8);
    }

    public boolean deleteCamp(String campName){
        Camp camp = DatabaseService.getCamp(campName);

        if(camp.getAttendees().size()!= 0) return false;
        else{
            Database.getCampData().remove(campName);
            return true;
        }

    }

    // toggle visibility
    public void toggleVisibility(Camp camp){
        System.out.println("Set visibility for "+camp+" (True/False):");
        boolean tV = sc.nextBoolean();
        camp.setVisibility(tV);
    }

    // view camp
    public void viewAllCamps(){
        for(int i = 0 ; i < Database.getCampData().size() ; i++){
           
        }
    }

    // see staff created camps
    public void viewCampsCreated(){
        for(int i=0;i<createdCamps.size();i++){
            
        }
            
    }


    // generate attendance report
    public void generateAttendanceReport(Camp camp){
        System.out.println("Participants attending camp");
        for(int i=0;i<camp.getAttendees().size();i++)
            System.out.println(camp.getAttendees().get(i).getUserID());
        System.out.println("Committee members attending camp");
        for(int i=0;i>camp.getCommittee().size();i++)
            System.out.println(camp.getCommittee().get(i).getUserID());
    }
    // generate performance report
    public void generatePerformanceReport(Camp camp){
        for(int i=0;i<camp.getAttendees().size();i++){
            System.out.println(camp.getAttendees().get(i).getUserID()+":"
            +camp.getAttendees().get(i).getPoints());
        }
    }

    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}

    // //can choose to view processed or unprocessed 
    // public void viewAllEnquiries(String CampName , boolean printProcessed){
    //     Camp camp = DatabaseService.getCamp(CampName);
    //     if(camp.getStaffInCharge() != AuthData.getCurrentUser().getUserID()){
    //        System.out.println("Unable to view enquiry list of camp created by other staff");
    //        return;
    //     }
        
    //     ArrayList<Enquiries> enquiries = camp.getEnquiryList();
    //     Enquiries q;
    //     if(printProcessed){
    //         for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
    //             q = enquiries.get(i);
    //             if(q.getProcessed()){
    //                 System.out.println("Enquiry: " + i + 1);
    //                 EnquiriesService.viewEnquiries(q);
    //             }
    //         }
    //     }
    //     else{
    //         for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
    //             q = enquiries.get(i);
    //             if(!q.getProcessed()){
    //                 System.out.println("Enquiry: " + i + 1);
    //                 EnquiriesService.viewEnquiries(q);
    //             }
    //         }
    //     }
           
    // }

    // public void replyEnquiries(Camp camp , int index , String reply){
    //    if(AuthData.getCurrentUser().getUserID() != camp.getStaffInCharge()){
    //     System.out.println("Unable to reply, camp is incharged by other staff!!");
    //    }
    //    else{
    //     EnquiriesService.replyEnquiries(camp.getCampName(), index, reply);
    //     System.out.println("Reply posted!!");
    //    }
    // }

    // // can view and approve suggestions made
    // public void viewSuggestions(Camp camp){
    //     camp.getSuggestionList(); 
    // }
}