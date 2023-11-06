package CAMs_App;

import java.util.*;

import CAMs_App.enums.*;

public class CampCompMem extends Student{
    //constructor
    CampCompMem(int userID, String password, Faculty faculty, Status status) {
		super(userID, password, faculty, status);
	}


    //Enquiries
    //view all
    public void viewAllEnquiries(){
        Camp camp = this.getComitteeCamp();
        ArrayList<Enquiries> q = camp.getEnquiryList();
        for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
            System.out.println("Enquiry: " + (i+1));
            q.get(i).viewEnquiries();
        }
    }

    //view 1
    public void viewEnquiry(int index){
       Camp camp = this.getComitteeCamp();
       camp.getEnquiryList().get(index).viewEnquiries();
    };
    
    
    public void replyEnquiry(Enquiries q){
        if(!q.getProcessed()){
            System.out.println("Reply to query: ");
            String ans = scan.next();
            q.setAnswer(ans , this.getUserID());
            this.addPoints();

        }
        else{
            System.out.println("This query has been processed...");
        }
    };
    

    //Suggestions
    Scanner scan = new Scanner(System.in);
    private ArrayList<String> suggestions = new ArrayList<>();
    
    public void submitSuggestion(){
        Camp camp = this.getComitteeCamp();
        System.out.println("Please provide your suggestion: ");
        String suggest = scan.next();

        Suggestions newSuggestions = new Suggestions(suggest, this.getUserID());
        camp.addSuggestion(newSuggestions);
    };
    
    public void viewSuggestion(Suggestions s){
        if(s.getSuggestBy() == this.getUserID() && s.getProcessed() == false){
            System.out.println("Your submitted suggestion is: " + s.getSuggestion());
        }
        else if(s.getProcessed() == true){
            System.out.println("Your suggestion has been processed!!");
        }
        else{
            System.out.println("No suggestion provided...");
        }
    };
    
    public void editSuggestions(Suggestions s){
        if(s.getSuggestBy() == this.getUserID() && s.getProcessed() == false){
            System.out.println("Your submitted suggestion is: " + s.getSuggestion());
            System.out.println("Please edit your suggestion: ");
            String editSuggestions = scan.next();
            s.setSuggestion(editSuggestions);
        }
        else if(s.getProcessed() == true){
            System.out.println("Your suggestion has been processed!!");
        }
        else{
            System.out.println("No suggestion provided...");
        }
    };
    
    public void deleteSuggestion(Camp camp, Suggestions s){
         if(s.getSuggestBy() == this.getUserID() && s.getProcessed() == false){
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
    };
}
