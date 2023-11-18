package CAMs_App.controllers;

import java.util.Scanner;

import CAMs_App.entity.Student;
import CAMs_App.entity.Suggestions;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.StaffCampService;
import CAMs_App.service.SuggestionsService;

public class StaffController extends UserController{
    SuggestionsService suggestionsService = new SuggestionsService();
    StaffCampService staffCampService = new StaffCampService();

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
        staffCampService.viewAllCamps();
    }

    public void viewCreatedCamp(String userID){
        System.out.println("print staff created camp........");
        staffCampService.viewCampsCreated(userID);
    }

    public void generateReport(String campName){

    }

    public void viewCommitteeList(String campName){

    }

    public void viewAttendeeList(String campName){

    }

    public void viewSuggestions(){

    }

    public void processSuggestions(int index){                   //set the process attribute
        suggestionsService.processSuggestions(null, index);
    }

    public void approveSuggestion(Student student, int index){                    //only show the processed suggestions
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to accept this suggestion? (Y/N)");
        char ans = sc.next().charAt(0);
        boolean approve;

        if(ans == 'Y')
            approve = true;
        else
            approve = false;

        
        if(suggestionsService.approveSuggestions(null, index, approve)){
            CampComController.addPoints(student);
        }

        sc.close();
    }

    public void viewEnquiries(){

    }

    public void replyEnquiries(){

    }

    

}
