package CAMs_App.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import CAMs_App.data.AuthData;

import CAMs_App.entity.Student;
import CAMs_App.entity.Suggestions;
import CAMs_App.enums.Faculty;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Enquiries;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.EnquiriesService;
import CAMs_App.service.HelperService;
import CAMs_App.service.StaffCampService;
import CAMs_App.service.SuggestionsService;

public class StaffController extends UserController{
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public void createCamp(){
        Camp camp = new Camp();
       
        //read camp name
        System.out.println("Enter camp name: ");
        String campName = sc.next();
        
        while(DatabaseService.checkIfCampNameExists(campName)){
            System.out.println("Camp Name already exists!! Enter another name");
            campName = sc.next();
        }
        camp.setCampName(campName);


        //camp starting date
        while(true){
            try {
                // Parse the user input into a LocalDateTime object
                System.out.println("Enter starting date in dd-mm-yyyy format:");
                String start = sc.next();
                LocalDate startDateTime = LocalDate.parse(start,formatter);
                camp.setCampDate(startDateTime);
                break;
            
            } catch (Exception e) {
                System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
            } 
        }
        
        
        
        //camp ending date
        while (true) {
             try {
                // Parse the user input into a LocalDateTime object
                System.out.println("Enter end date for camp in dd-mm-yyyy format:");                    
                String end = sc.next();
                LocalDate endDateTime = LocalDate.parse(end,formatter);
                camp.setCampEndDate(endDateTime);
                break;
            
            } catch (Exception e) {
                System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
            }
        }
       
       
        //set camp days
        camp.setNumberOfCampDays((int)ChronoUnit.DAYS.between(camp.getCampDate(), camp.getCampEndDate())+1);
        //System.out.println(camp.getNumberOfCampDays());

                   
        //registratoin closing date
        while (true) {
            try {
                // Parse the user input into a LocalDateTime object
                System.out.println("Enter camp registration closing date: ");
                String date = sc.next(); 
                LocalDate regClosing = LocalDate.parse(date,formatter);
                camp.setRegCloseDate(regClosing);
                break;
            
            } catch (Exception e) {
                System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
            }
        }
        
        

        //User group
        System.out.println("Enter faculty for which the camp is open to: ");
        String fac = sc.next();
        try {
            Faculty faculty = Faculty.valueOf(fac.toUpperCase());
            camp.setUserGroup(faculty);
        } catch (Exception e){
            System.out.println("Invalid faculty.");
        }

        //camp location
        System.out.println("Enter camp location: ");
        String location = sc.next();
        camp.setLocation(location);

        //number of participants slots
        System.out.println("Enter total number of camp slots: ");
        int campSlots = sc.nextInt();
        camp.setTotalSlots(campSlots);
        
        //number of committee slots
        System.out.println("Enter number of camp committeee slots: ");
        int campCommitteeSlots = sc.nextInt();
        camp.setCampCommitteeSlots(campCommitteeSlots);

        //Camp Descriptions
        System.out.println("Enter camp description: ");
        String description = sc.next();
        camp.setDescription(description);
        
        //set visibility
        System.out.println("Set Visibility: ");
        System.out.println("Input 1 to make camp visible, else invisible....");
        int visibility = sc.nextInt();
        if(visibility == 1) camp.setVisibility(true);
        else camp.setVisibility(false);
        
        //set creator and visibility
        camp.setStaffInCharge(AuthData.getCurrentUser().getUserID());

        camp.setRemainingSlot(campSlots);
        

        StaffCampService.addNewCampToDB(camp);
    }

    public void editCamp(){
        String campName = AuthData.getCurrentCamp().getCampName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Camp camp = DatabaseService.getCamp(campName);
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

                    try {
                        LocalDate startDateTime = LocalDate.parse(start,formatter);
                        System.out.println(startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        camp.setCampDate(startDateTime);

                    } catch (Exception e) {
                        System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
                    }

                    System.out.println("Enter ending date in dd-mm-yyyy format:");
                    String end = sc.next();

                    try {
                        LocalDate endDateTime = LocalDate.parse(end,formatter);
                        System.out.println(endDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        camp.setCampDate(endDateTime);

                    } catch (Exception e) {
                        System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
                    }

                    camp.setNumberOfCampDays((int)ChronoUnit.DAYS.between(camp.getCampDate(), camp.getCampEndDate()));
                    break;

                case 3:
                    System.out.println("Edit camp registeration closing date");
                    System.out.println("Enter new closing date:");
                    try {
                        LocalDate regDateTime = LocalDate.parse(sc.next(),formatter);
                        System.out.println(regDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        camp.setRegCloseDate(regDateTime);

                    } catch (Exception e) {
                        System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
                    }
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

    public void deleteCamp(String campName){
        if(StaffCampService.deleteCamp(campName)){
            System.out.println("Unable to delete camp with participants");
        }
        else{
            System.out.println("Camp deleted");
        }
    }

    public void toggleVisibility(){
        Camp camp = AuthData.getCurrentCamp();
        System.out.println("Toggle camp visibility");
       
        while (true){
             System.out.println("Press 1 to turn off, 2 to turn on");
             int choice = sc.nextInt();
            if(choice == 1){
                camp.setVisibility(false);
                break;
            }
            else if(choice == 2){
                camp.setVisibility(true);
                break;
            }
            else{
                System.out.println("Wrong input!! Please try again...");
            }
        }

    }

    public void viewAllCamp(){
        System.out.println("print all camp........");
        StaffCampService.viewAllCamps();
    }

    public void viewCreatedCamp(String userID){
        System.out.println("print staff created camp........");
        StaffCampService.viewCampsCreated(userID);
    }

    public void generateReport(String campName){
        Camp camp = DatabaseService.getCamp(campName);
        StaffCampService.generatePerformanceReport(camp);
    }

    public void viewCommitteeList(String campName){
        Camp camp = DatabaseService.getCamp(campName);
        StaffCampService.generateCommitteeList(camp);
    }

    public void viewAttendeeList(String campName){
        Camp camp = DatabaseService.getCamp(campName);
        if(camp.getAttendees() == null){
            System.out.println("This camp doesn't have any attendee");
        }
        else{
            StaffCampService.generateAttendeeList(camp);
        }
    }

    //Enquiries
    public void viewEnquiries(){
        System.out.println("Select your choice: ");
        System.out.println("Press 1: View Processed Enquiries");
        System.out.println("Press 2: View Unprocessed Enquiries");
        System.out.println("Press 3: View All Enquiries");

        int choice= sc.nextInt();
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> q = camp.getEnquiryList();

        switch (choice) {
            case 1:
                for(int i = 0 ; i < q.size() ; i++){
                    if(q.get(i).getProcessed()){
                        System.out.println("EnqriesID: " + i+1);
                        HelperService.viewEnquiries(q.get(i));
                    }
                }
                break;
            
            case 2:
            for(int i = 0 ; i < q.size() ; i++){
                    if(!q.get(i).getProcessed()){
                        System.out.println("EnqriesID: " + i+1);
                        HelperService.viewEnquiries(q.get(i));
                    }
                }
            break;
        
            case 3:
            for(int i = 0 ; i < q.size() ; i++){
                   System.out.println("EnqriesID: " + i+1);
                   HelperService.viewEnquiries(q.get(i));
             }
            break;

        }

    }

    public void replyEnquiries(){
        System.out.println("Which enquiries you would like to reply: ");
        int index = sc.nextInt();
        Camp camp = AuthData.getCurrentCamp();
        Enquiries q = camp.getEnquiryList().get(index);

        System.out.println("Enter your reply: ");
        String reply = sc.next();
        EnquiriesService.replyEnquiries(q,reply);
        System.out.println("Enquiries replied");
}

//suggestions
public void viewSuggestions(){
     System.out.println("Select your choice: ");
        System.out.println("Press 1: View Suggestions Under Process");  
        System.out.println("Press 2: View Processed Suggestions");
        System.out.println("Press any number: View New Suggestions");


        int choice = sc.nextInt();

        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Suggestions> s = camp.getSuggestionList();
        switch (choice) {
            case 1:
                System.out.println("Suggestions under process: ");
                for(int i = 0 ; i < s.size() ; i++){
                    if(s.get(i).getProcessed() && s.get(i).getAccepted() == null){
                        System.out.println("SuggestionID: " + i+1);
                        HelperService.printSuggestions(s.get(i));
                    }
                }
                break;
            
            case 2:
            for(int i = 0 ; i < s.size() ; i++){
                System.out.println("Processed suggestions");
                    if(s.get(i).getProcessed() && s.get(i).getAccepted() != null){
                        System.out.println("SuggestionID: " + i+1);
                        HelperService.printSuggestions(s.get(i));
                    }
                }
            break;
        
            case 3:
            for(int i = 0 ; i < s.size() ; i++){
                   System.out.println("SuggestionsID: " + i+1);
                   HelperService.printSuggestions(s.get(i));
             }
            break;
        }

    }

    public void processSuggestions(){
        System.out.println("Which suggestion you would like to process: ");
        int index = sc.nextInt();  
        SuggestionsService.processSuggestions(index);
        System.out.println("Suggesstion status set to processing...");
        HelperService.printSuggestions(AuthData.getCurrentCamp().getSuggestionList().get(index));
    }

    public void approveSuggestion(Student student){ 
        System.out.println("Which suggestion you would like to process: ");
        int index = sc.nextInt();            
        System.out.println("Do you want to accept this suggestion? (Y/N)");
        char ans = sc.next().charAt(0);
        boolean approve;

        if(ans == 'Y')
            approve = true;
        else
            approve = false;

        
        SuggestionsService.approveSuggestions(index , approve);
        CampComController.addPoints(student);
        System.out.println("Suggestion has been processed...");
        if(approve)System.out.println("1 point awarded to suggestor");
    }
}
