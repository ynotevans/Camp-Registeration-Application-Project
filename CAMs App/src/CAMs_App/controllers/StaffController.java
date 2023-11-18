package CAMs_App.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import CAMs_App.data.Database;
import CAMs_App.entity.Student;
import CAMs_App.entity.Camp;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.StaffCampService;
import CAMs_App.service.SuggestionsService;

public class StaffController extends UserController{
    SuggestionsService suggestionsService = new SuggestionsService();

    public void createCamp(){
    
    }

    public void editCamp(String campName){
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

    public void deleteCamp(){

    }

    public void toggleVisibility(){

    }

    public void viewAllCamp(){
        System.out.println("print all camp........");
    }

    public void viewCreatedCamp(String staffID){
        System.out.println("print staff created camp........");
    }

    public void generateReport(String campName){

    }

    public void viewCommitteeList(String campName){

    }

    public void viewAttendeeList(String campName){

    }

    public void viewSuggestions(){

    }

    public void processSuggestions(){                   //set the process attribute

    }

    public void approveSuggestion(Student student){                    //only show the processed suggestions
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to accept this suggestion? (Y/N)");
        char ans = sc.next().charAt(0);
        boolean approve;

        if(ans == 'Y')
            approve = true;
        else
            approve = false;

        
        if(suggestionsService.approveSuggestions(null, 0, approve)){
            CampComController.addPoints(student);
        }

        sc.close();
    }

    public void viewEnquiries(){

    }

    public void replyEnquiries(){

    }

    

}
