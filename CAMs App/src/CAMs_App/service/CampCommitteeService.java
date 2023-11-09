package CAMs_App.service;

import java.util.ArrayList;
import java.util.Scanner;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.CampCompMem;
import CAMs_App.entity.Enquiries;
import CAMs_App.entity.Suggestions;
import CAMs_App.entity.User;

public class CampCommitteeService {
	private static final Scanner sc = new Scanner(System.in);
	private CampCompMem user = Database.getCampCompMemData().get(AuthData.getCurrentUser().getUserID());
	
	public void viewAttendeeList() {
		
	}
	
	public void addPoints() {
		
	}
	
	//Enquiries
    public void viewAllEnquiries(){
        Camp camp = user.getComitteeCamp();
        ArrayList<Enquiries> q = camp.getEnquiryList();
        for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
            System.out.println("Enquiry: " + (i+1));
            q.get(i).viewEnquiries();
        }
    }

    //view 1
    public void viewEnquiry(int index){
       Camp camp = user.getComitteeCamp();
       camp.getEnquiryList().get(index).viewEnquiries();
    }
    
    
    public void replyEnquiry(Enquiries q){
        if(!q.getProcessed()){
            System.out.println("Reply to query: ");
            String ans = sc.next();
            q.setAnswer(ans , user.getUserID());
            user.addPoints();

        }
        else{
            System.out.println("This query has been processed...");
        }
    }
    
  //Suggestions
    public void submitSuggestion(){
        Camp camp = user.getComitteeCamp();
        System.out.println("Please provide your suggestion: ");
        String suggest = sc.next();

        Suggestions newSuggestions = new Suggestions(suggest, user.getUserID());
        camp.addSuggestion(newSuggestions);
    }
    
    public void viewSuggestion(Suggestions s){
        if(s.getSuggestBy() == user.getUserID() && s.getProcessed() == false){
            System.out.println("Your submitted suggestion is: " + s.getSuggestion());
        }
        else if(s.getProcessed() == true){
            System.out.println("Your suggestion has been processed!!");
        }
        else{
            System.out.println("No suggestion provided...");
        }
    }
    
    public void editSuggestions(Suggestions s){
        if(s.getSuggestBy() == user.getUserID() && s.getProcessed() == false){
            System.out.println("Your submitted suggestion is: " + s.getSuggestion());
            System.out.println("Please edit your suggestion: ");
            String editSuggestions = sc.next();
            s.setSuggestion(editSuggestions);
        }
        else if(s.getProcessed() == true){
            System.out.println("Your suggestion has been processed!!");
        }
        else{
            System.out.println("No suggestion provided...");
        }
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
    
    //other
    public void generateReport() {
    	
    }
}
