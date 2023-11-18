package CAMs_App.controllers;

import java.util.Scanner;

import CAMs_App.entity.Student;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.StaffCampService;
import CAMs_App.service.SuggestionsService;

public class StaffController extends UserController{
    SuggestionsService suggestionsService = new SuggestionsService();

    public void createCamp(){

    }

    public void editCamp(){

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

    public void approveSuggestion(){                    //only show the processed suggestions
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to accept this suggestion? (Y/N)");
        char ans = sc.next().charAt(0);
        boolean approve;

        if(ans == 'Y')
            approve = true;
        else
            approve = false;

        
        if(suggestionsService.approveSuggestions(null, 0, approve)){
            StaffCampService.addPoints(Student);
        }

        sc.close();
    }

    public void viewEnquiries(){

    }

    public void replyEnquiries(){

    }

    

}
