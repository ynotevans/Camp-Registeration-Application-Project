package CAMs_App.controllers;


import java.util.ArrayList;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.*;

public class CampComController extends StudentController{
    Student user = (Student)AuthData.getCurrentUser();
    
    public void createSuggestion(){
    
    }

    public void viewCampDetails(){     ///print the camp details
        
        String campName = AuthData.getCurrentCamp().getCampName();
        Camp camp = DatabaseService.getCamp(campName);
        HelperService.viewCamp(camp);
    }

    public void generateReport(){}

    public void deleteSuggestion(){}
	
	
    public void viewAllEnquiries(){
        Camp camp = user.getComitteeCamp(); // ???
        ArrayList<Enquiries> q = camp.getEnquiryList();
        for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
            System.out.println("Enquiry: " + (i+1));
            HelperService.viewEnquiries(q.get(i));
        }
    }

    
    public void viewEnquiry(int index){
       Camp camp = user.getComitteeCamp(); // ??
       HelperService.viewEnquiries(camp.getEnquiryList().get(index));
    }
    
    
    public void replyEnquiry(int index){
         Camp camp = user.getComitteeCamp();
         Enquiries q = camp.getEnquiryList().get(index - 1);

        if(!q.getProcessed()){
            System.out.println("Reply to query: ");
            String ans = sc.nextLine();
            q.setAnswer(ans);
            CampComController.addPoints(user);            //addpoint fucntion put at campComController or put at staffCampService
            System.out.println("Query processed");
            System.out.println("1 point awarded");
            System.out.println("Current point(s): " + user.getPoints());

        }
        else{
            System.out.println("This query has already been processed...");
        }
    }
    
  //Suggestions
    public void submitSuggestion(){
        Camp camp = user.getComitteeCamp();
        System.out.println("Please provide your suggestion: ");
        String suggest = sc.nextLine();

        Suggestions Suggestions = new Suggestions(suggest, user.getUserID());
        camp.addSuggestion(Suggestions);
    }
    
    public void viewSuggestion(){
       if(!SuggestionsService.submittedSuggestions()){
          System.out.println("You do not have any suggestions submitted");
          return;
       }
       Camp camp = AuthData.getCurrentCamp();
       ArrayList<Suggestions> sList = camp.getSuggestionList();

       System.out.println("Your submitted suggestions on this camp: ");
       for(int i = 0 ; i < sList.size() ; i++){
        if(sList.get(i).getSuggestBy() == AuthData.getCurrentUser().getUserID()){
            System.out.println("SuggestionsID " + (i + 1));
            HelperService.printSuggestions(sList.get(i));
            System.out.println(" ");
        }
       }
    }
    
    public void editSuggestions(){
        if(!SuggestionsService.submittedSuggestions()){
          System.out.println("You do not have any suggestions submitted");
          return;
       }

       this.viewSuggestion();
       ArrayList <Suggestions> sList = AuthData.getCurrentCamp().getSuggestionList();

       System.out.println("Which suggestion you would like to edit? ");
       int index = HelperService.readInt(1 , sList.size() , "Suggestion id out of bound");

       Suggestions s = sList.get(index-1);
       while (s.getSuggestBy() != AuthData.getCurrentUser().getUserID()){
        System.out.println("Invalid suggestion id, you can only edit your own suggestion");
        index = HelperService.readInt(1 , sList.size() , "Suggestion id out of bound");
        s = sList.get(index-1);
       }

       System.out.println("Enter your new suggestion: ");
       String suggestion = sc.nextLine();

       s.setSuggestion(suggestion);
       System.out.println("Your suggestion has been updated");

    }
    
    public void deleteSuggestion(Camp camp, Suggestions s){
         if(s.getSuggestBy() == user.getUserID() && s.getProcessed() == false){
            System.out.println("Deleting your suggestion...");
            camp.getSuggestionList().remove(s);                          //need to modify again to check the index of the suggestion to be deleted
            System.out.println("Successfully deleted. ");
        }
        else if(s.getProcessed() == true){
            System.out.println("Your suggestion has been processed!!");
        }
        else{
            System.out.println("No suggestion provided...");
        }
    }

    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}
    

}
