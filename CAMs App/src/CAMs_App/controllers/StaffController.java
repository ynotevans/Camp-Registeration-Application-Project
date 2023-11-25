package CAMs_App.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import CAMs_App.data.AuthData;
import CAMs_App.enums.Faculty;
import CAMs_App.entity.*;
import CAMs_App.service.*;

public class StaffController extends UserController{
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

//camp related methods
    public void createCamp(){
        Camp camp = new Camp();
       
        //read camp name
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        
        while(DatabaseService.checkIfCampNameExists(campName)){
            System.out.println("Camp Name already exists!! Enter another name");
            campName = sc.nextLine();
        }
        camp.setCampName(campName);


        //camp starting date
        while(true){
            try {
                // Parse the user input into a LocalDateTime object
                System.out.println("Enter starting date for camp created in dd-mm-yyyy format:");
                String start = sc.nextLine();
                LocalDate startDateTime = LocalDate.parse(start,formatter);
                if(startDateTime.isBefore(LocalDate.now())){
                    System.out.println("Invalid starting date. Please select a date after the current date: " + LocalDate.now());
                    continue;
                }
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
            System.out.println("Enter end date for camp created in dd-mm-yyyy format:");
            String end = sc.nextLine();
            LocalDate endDateTime = LocalDate.parse(end, formatter);

            if (endDateTime.isBefore(camp.getCampDate())) {
                System.out.println("Camp ending date is before the starting date. Please try again!!!");
                continue;
            }
            camp.setCampEndDate(endDateTime);
            break;

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use dd-mm-yyyy format:");
            sc.next(); // consume the invalid input to avoid an infinite loop
        }
    }



       
        //set camp days
        camp.setNumberOfCampDays((int)ChronoUnit.DAYS.between(camp.getCampDate(), camp.getCampEndDate())+1);
        //System.out.println(camp.getNumberOfCampDays());

                   
        //registration closing date
        while (true) {
            try {
                // Parse the user input into a LocalDateTime object
                System.out.println("Enter camp registration closing date: ");
                String date = sc.next(); 
                LocalDate regClosing = LocalDate.parse(date,formatter);
                if(regClosing.isAfter(camp.getCampDate())){
                    System.out.println("Invalid registration closing date. Please select a date ealier than the camp starting date:" + camp.getCampDate());
                    continue;
                }
                camp.setRegCloseDate(regClosing);
                break;
            
            } catch (Exception e) {
                System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
            }
        }
        
        

        //User group
        System.out.println("Enter faculty for which the camp is open to: ");
        sc.nextLine();
        
        while (true) {
            try {
                String fac = sc.nextLine();
                Faculty faculty = Faculty.valueOf(fac.toUpperCase());
                camp.setUserGroup(faculty);  
                break;
            }
            catch(IllegalArgumentException e){
                 System.out.println("Invalid faculty. Please try again");
            }
        }

        //camp location
        System.out.println("Enter camp location: ");
        String location = sc.nextLine();
        camp.setLocation(location);

        //number of committee slots
        System.out.println("Enter number of camp committeee slots: ");
        int campCommitteeSlots = sc.nextInt();
        
        while(campCommitteeSlots <= 0 || campCommitteeSlots > 10){
            System.out.println("Camp committee slots must be between 0 to 10\n Please try again: ");
            campCommitteeSlots = sc.nextInt();
        }
        camp.setCampCommitteeSlots(campCommitteeSlots);
        camp.setCampCommitteeRemainingSlots(campCommitteeSlots);

        //total number of slots
        System.out.println("Enter total number of camp slots: ");
        int campSlots = HelperService.readInt(campCommitteeSlots, Integer.MAX_VALUE , "total slot cannot be less than committee slot");      
        camp.setTotalSlots(campSlots);


        //Camp Descriptions
        System.out.println("Enter camp description: ");
        sc.nextLine();
        String description = sc.nextLine();
        camp.setDescription(description);
        
        //set visibility
        System.out.println("Set Visibility: ");
        System.out.println("Input 1 to make camp visible, else invisible....");
        int visibility = HelperService.readInt();
        if(visibility == 1) camp.setVisibility(true);
        else camp.setVisibility(false);
        
        //set creator and visibility
        camp.setStaffInCharge(AuthData.getCurrentUser().getUserID());
        camp.setRemainingSlot(campSlots);

        //add camp to database
        StaffCampService.addNewCampToDB(camp);
        System.out.println("Camp created successfully!");
    }

    public void editCamp(){
        String campName = AuthData.getCurrentCamp().getCampName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Camp camp = DatabaseService.getCamp(campName);
        int choice=0;
        do
        {
            HelperService.clearScreen();
            System.out.println("Editting "+camp.getCampName()+"...");
            System.out.println("1.Change camp name");
            System.out.println("2.Change camp dates");
            System.out.println("3.Change registration date");
            System.out.println("4.Open camp to own school or whole NTU");
            System.out.println("5.Edit camp location");
            System.out.println("6.Edit number of total camp slots");
            System.out.println("7.Edit number of camp committee slots");
            System.out.println("8.Edit camp description");
            System.out.println("9.Quit");
            System.out.println("Enter choice:");
            choice = HelperService.readInt();
            switch (choice) {
                case 1:
                    System.out.println("Edit camp name");
                    System.out.println("Change camp name to");
                    String newCampName = sc.nextLine();
                    String oldCampName = camp.getCampName();
                    while(DatabaseService.checkIfCampNameExists(newCampName)){
                        System.out.println("Camp Name already exists!! Enter another name:");
                        newCampName = sc.nextLine();
                    }
                    camp.setCampName(newCampName);
                    System.out.println("Camp Name changed to "+ camp.getCampName()+ "\n");
                    StaffCampService.editCampNameinDB(oldCampName,camp);
                    HelperService.wait(2);
                    break;
                case 2:
                    System.out.println("Edit camp dates");
                    System.out.println("Enter starting date in dd-mm-yyyy format:");
                    String start = sc.nextLine();

                    try {
                        LocalDate startDateTime = LocalDate.parse(start,formatter);
                        System.out.println(startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        camp.setCampDate(startDateTime);

                    } catch (Exception e) {
                        System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
                    }

                    System.out.println("Enter ending date in dd-mm-yyyy format:");
                    String end = sc.nextLine();

                    try {
                        LocalDate endDateTime = LocalDate.parse(end,formatter);
                        System.out.println(endDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        camp.setCampDate(endDateTime);

                    } catch (Exception e) {
                        System.out.println("Error: Invalid date and time format. Please use dd-mm-yyyy format:");
                    }

                    camp.setNumberOfCampDays((int)ChronoUnit.DAYS.between(camp.getCampDate(), camp.getCampEndDate()));
                    HelperService.wait(2);
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
                    HelperService.wait(2);
                    break;

                case 4:
                    System.out.println("Edit camp to open to own school or whole NTU");
                    System.out.println("Enter faculty to open camp to:");
                    String facultyString = sc.next();
                    try {
                        Faculty faculty = Faculty.valueOf(facultyString.toUpperCase());
                        camp.setUserGroup(faculty);
                    } catch (Exception e){
                        System.out.println("Invalid faculty.");
                    }
                    System.out.println("Camp is now open to "+camp.getUserGroup());
                    HelperService.wait(2);
                    break;
                case 5:
                    System.out.println("Edit camp location");
                    System.out.println("Enter camp's new location:");
                    camp.setLocation(sc.nextLine());
                    System.out.println("Camp location changed to: "+camp.getLocation());           
                    HelperService.wait(2);         
                    break;
                
                case 6:
                    System.out.println("Edit total camp slots");
                    int totalSlots;     
                    System.out.println("Set total number of camp slots");
                    int campMember = camp.getTotalSlots() - camp.getRemainingSlot() - camp.getCampCommitteeRemainingSlots();
                    totalSlots = HelperService.readInt(0 , campMember , "New camp slot cannot be lesser than the current total number of attendees and committee");   //new camp slot cannot be less than current number of attendees + committee
                    camp.setTotalSlots(totalSlots);
                    System.out.println("The camp committee slots changed to: "+camp.getCampCommitteeSlots());
                    HelperService.wait(2);
                    break;
                
                case 7:
                    System.out.println("Edit camp committee slots(up to 10 slots)");
                    int committeeSlots;
                    System.out.println("Set number of camp commitee slots");
                    committeeSlots = HelperService.readInt(camp.getCampCommitteeRemainingSlots(),10 , "New camp committee slot cannot be more than 10 or less then the current number of committee member");                            
                    camp.setCampCommitteeSlots(committeeSlots);
                    System.out.println("The camp committee slots changed to: "+camp.getCampCommitteeSlots());
                    HelperService.wait(2);
                    break;

                case 8:
                    System.out.println("Edit camp description");
                    System.out.println("Enter new camp description");
                    camp.setDescription(sc.nextLine());
                    System.out.println("Camp description has been changed to\n"+camp.getDescription());
                    HelperService.wait(2);
                    break;       

                case 9:
                    System.out.println("Exitting camp edits.");
                    HelperService.wait(2);
                    break;    

                default:
                    System.out.println("Re-enter choice");
                    HelperService.wait(2);
                    break;
            }
        }while(choice!=9);
    }

    public void deleteCamp(){
        Camp camp = AuthData.getCurrentCamp();
        if(!(camp.getAttendees().isEmpty() || camp.getCommittee().isEmpty())){
            System.out.println("Unable to delete camp with participants");
        }
        else{
            StaffCampService.deleteCamp(camp);
            System.out.println("Camp deleted");
        }
    }

    public void toggleVisibility(){
        Camp camp = AuthData.getCurrentCamp();
        if(!(camp.getAttendees().isEmpty() && camp.getCommittee().isEmpty())){
            System.out.println("Unable to turn off visibility: there are students who have already signed up for this camp");
            return;
        }
        System.out.println("Toggle camp visibility");
       
        while (true){
             System.out.println("Press 1 to turn off, any number to turn on");
             int choice = HelperService.readInt();
            if(choice == 1){
                camp.setVisibility(false);
                System.out.println("Camp is invisible to students now.");
                HelperService.wait(2);
                break;
            }
            else{
               camp.setVisibility(true);
               System.out.println("Camp is visible to students now.");
                HelperService.wait(2);
               break;
            }
        }

    }

    public void viewAllCamp(){
        StaffCampService.viewAllCamps();
    }

    public void viewCreatedCamp(String userID){
        System.out.println("print staff created camp........");
        StaffCampService.viewCampsCreated(userID);
    }

    public void generateStudentReport(){
      System.out.println("Generating student attendence report...");
      CampManagementService.generateStudentListReport();
    }

    public void generateCommitteeReport(){
      System.out.println("Generating committee performance report...");
      StaffCampService.commiteePerformanceReport();
    }

    public void generateEnquiriesReport(){
        System.out.println("List of all enquiries of the camp");
        CampManagementService.enquiriesReport();
    }



//Enquiries
    public void viewEnquiries(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> q = camp.getEnquiryList();
        if(q.isEmpty()){
            System.out.println("No enquiries");
            return;
        }
        System.out.println("Select your choice: ");
        System.out.println("Press 1: View Processed Enquiries");
        System.out.println("Press 2: View New Enquiries");
        System.out.println("Press any number: View All Enquiries");

        int choice = HelperService.readInt(); 
       
        switch (choice) {
            case 1:
                HelperService.clearScreen();
                HelperService.printRoute("Staff Camp Menu ---> View Processed Enquiries");
                System.out.println("Processed Enquiries: ");
                EnquiriesService.viewProcessedEnquiries();
                HelperService.pressAnyKeyToContinue();

                break;
            case 2:
                HelperService.clearScreen();
                HelperService.printRoute("Staff Camp Menu ---> View New Enquiries");
                System.out.println("New Enquiries ");
                EnquiriesService.viewNewEnquiries();
                HelperService.pressAnyKeyToContinue();
        
                break;
        
            default:
                HelperService.clearScreen();
                HelperService.printRoute("Staff Camp Menu ---> View All Enquiries");
                System.out.println("List of all enquiries:");
                EnquiriesService.viewAllEnquiries();
                HelperService.pressAnyKeyToContinue();
                break;

        }
    }

    public void replyEnquiries(){
        if(!EnquiriesService.hasNewEnquiries()){
            System.out.println("No new enquiries to reply");
            return;
        }
        Camp camp = AuthData.getCurrentCamp();
        EnquiriesService.viewNewEnquiries();
        System.out.println("Which enquiries you would like to reply: ");
        int index = HelperService.readInt(1 , camp.getEnquiryList().size(),"Enquiry index out of bound"); 
        Enquiries q = camp.getEnquiryList().get(index -1);
        if(q.getProcessed()) {
            System.out.println("Wrong ID , Unable to reply to processed enquiry");
            return;
        }
        System.out.println("Enter your reply: ");
        String reply = sc.nextLine();
        q.setAnswer(reply);
        q.setAnswerer(AuthData.getCurrentUser().getUserID());
        q.setProcessed();
        System.out.println("Enquiries replied");
    }

//suggestions
    public void viewSuggestions(){
    System.out.println("Select your choice: ");
    System.out.println("Press 1: View Suggestions Under Process");  
    System.out.println("Press 2: View Processed Suggestions");
    System.out.println("Press 3: View New Suggestions");
    System.out.println("Press any number: View All Suggestions");
    
    int choice = HelperService.readInt();

    Camp camp = AuthData.getCurrentCamp();
    ArrayList<Suggestions> sList = camp.getSuggestionList();

    switch (choice) {
        case 1:
            HelperService.clearScreen();
            HelperService.printRoute("Staff Camp Menu ---> View Processing Suggestions");

            if(!SuggestionsService.hasProcessingSuggestion()){
                System.out.println("Currently no processing suggestion");
                return;
            }
            System.out.println("Suggestions under process: ");
            for(int i = 0 ; i < sList.size() ; i++){
                Suggestions s = sList.get(i);
                if(s.getStatus().toString().equals("PROCESSING")){
                    System.out.println("SuggestionID: " + (i+1));
                    SuggestionsService.printSuggestions(s);
                    System.out.println(" ");
                }
            }
         
            HelperService.pressAnyKeyToContinue(); 
            break;
        
        case 2:
            HelperService.clearScreen();
            HelperService.printRoute("Staff Camp Menu ---> View Processed Suggestions");
            if(!SuggestionsService.hasProcessedSuggestion()){
                System.out.println("Currently no processed suggestion");
                return;
            }
            
            System.out.println("Processed suggestions");
            for(int i = 0 ; i < sList.size() ; i++){
                Suggestions s = sList.get(i);
                if(s.getStatus().toString().toUpperCase().equals("PROCESSED")){
                    System.out.println("SuggestionID: " + (i+1));
                    SuggestionsService.printSuggestions(s);
                    System.out.println(" ");
                }
            }
            HelperService.pressAnyKeyToContinue(); 
        break;

        case 3:
            HelperService.clearScreen();
            HelperService.printRoute("Staff Camp Menu ---> View New Suggestions");
            if(!SuggestionsService.hasNewSuggestion()){
                System.out.println("Currently no new suggestion");
                return;
            }
            System.out.println("New suggestions: ");
            for(int i = 0 ; i < sList.size() ; i++){
                Suggestions s = sList.get(i);
                if(s.getStatus().toString().equals("NEW")){
                    System.out.println("SuggestionID: " + (i+1));
                    SuggestionsService.printSuggestions(s);
                    System.out.println(" ");
                }
            }
            HelperService.pressAnyKeyToContinue();    
        break;

        default:
        HelperService.clearScreen();
        HelperService.printRoute("Staff Camp Menu ---> View All Suggestions");
        
           if(sList.isEmpty()){
                System.out.println("Currently no suggestion submitted");
                return;
            }
        
        System.out.println("List of all suggestions: ");
        for(int i = 0 ; i <sList.size() ; i ++){
            System.out.println("SuggestionID: " + (i+1));
            SuggestionsService.printSuggestions(sList.get(i));
            System.out.println(" ");
        }
        HelperService.pressAnyKeyToContinue(); 
        break;
    }
 }
    public void processSuggestions(){
        ArrayList <Suggestions> sList = AuthData.getCurrentCamp().getSuggestionList();
        if(!SuggestionsService.hasNewSuggestion()){
            System.out.println("No new suggestion to process");
            return;
        }
         System.out.println("New suggestions: ");
            for(int i = 0 ; i < sList.size() ; i++){
                Suggestions s = sList.get(i);
                if(s.getStatus().toString().equals("NEW")){
                    System.out.println("SuggestionID: " + (i+1));
                    SuggestionsService.printSuggestions(s);
                    System.out.println(" ");
                }
            }    
      
        System.out.println("Which suggestion you would like to process: ");
        int index = HelperService.readInt(1 , sList.size() , "Invalid Suggestion ID"); 
        if(!sList.get(index - 1).getStatus().toString().equals("NEW")){
            System.out.println("Suggestion is already under process / has been processed");
            return;
        }
        else{
            System.out.println("Suggestion status set to processing...");
            sList.get(index - 1).setSuggestionStatus("PROCESSING");
        }

      
        SuggestionsService.printSuggestions(sList.get(index - 1));
    }

    public void approveSuggestion(){ 
         ArrayList <Suggestions> sList = AuthData.getCurrentCamp().getSuggestionList();
         if(!(SuggestionsService.hasNewSuggestion() ||SuggestionsService.hasProcessingSuggestion())){
            System.out.println("There is no suggestion pending approval");
            return;
         }
         System.out.println("Suggestion pending approval: ");
         for(int i = 0 ; i < sList.size() ; i++){
                Suggestions s = sList.get(i);
                if(!s.getStatus().toString().equals("PROCESSED")){
                    System.out.println("SuggestionID: " + (i+1));
                    SuggestionsService.printSuggestions(s);
                    System.out.println(" ");
                }
            }    
       
      
    System.out.println("Which suggestion you would like to approve/reject: ");
    int index = HelperService.readInt(1,sList.size() , "Invalid Suggestion ID");
    Suggestions s = sList.get(index -1);
    System.out.println("Do you want to accept this suggestion? (Y to approve , any key to reject)");
    char ans = sc.next().toUpperCase().charAt(0);
    boolean approve;

    if(ans =='Y')  approve = true;
    else approve = false;

    s.setSuggestionStatus("PROCESSED");
    s.setAccepted(approve);
    Student student = (Student)DatabaseService.getStudent(s.getSuggestBy());
    CampComController.addPoints(student);
   
    if(approve){
        System.out.println("Suggestion has been approved...");
        System.out.println("1 point awarded to suggestor");

        System.out.println("Press 1 to made changes to camp details based on suggestion. Other numbers to quit");
        int choice = HelperService.readInt();
        if(choice == 1){
            this.editCamp();
        }
        
    }
    else{
        System.out.println("Suggestion has been rejected...");
        System.out.println("0 point awarded to suggestor");

    }

}

}
