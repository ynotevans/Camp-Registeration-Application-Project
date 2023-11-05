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
    
    public void submitSuggestion(){};
    
    public void viewSuggestion(){};
    
    public void editSuggestions(){};
    
    public void deleteSuggestion(){};
}
