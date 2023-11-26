package CAMs_App.controllers;


import java.util.ArrayList;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.*;

public class CampComController extends StudentController{
    Student user = (Student)AuthData.getCurrentUser();
    //camp
    public void viewCampDetails(){    
        Camp camp = AuthData.getCurrentCamp();
        HelperService.viewCamp(camp);
    }


    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}
      
	
	//enquiries
    public void viewAllEnquiries(){
        Camp camp = user.getComitteeCamp(); // ???
        ArrayList<Enquiries> q = camp.getEnquiryList();
        for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
            System.out.println("Enquiry: " + (i+1));
            EnquiriesService.viewEnquiries(q.get(i));
        }
    }

    
    public void viewEnquiry(int index){
       Camp camp = user.getComitteeCamp(); 
       EnquiriesService.viewEnquiries(camp.getEnquiryList().get(index));
    }
    
    
    public void replyEnquiry(){
        if(!EnquiriesService.hasNewEnquiries()){
            System.err.println("No new Enquiries to ");
        }
        Camp camp = AuthData.getCurrentCamp();
        EnquiriesService.viewNewEnquiries();
        System.out.println("Which enquiry do you want to reply?");
        int index = HelperService.readInt();
        Enquiries q = camp.getEnquiryList().get(index-1);
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
      public void createSuggestion(){
        System.out.println("Enter your suggestion: ");
        String s = sc.nextLine();
        SuggestionsService.createSuggestion(s);
        System.out.println("Your suggestion has been added...");
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
        if(sList.get(i).getSuggestBy().equals(AuthData.getCurrentUser().getUserID())){
            System.out.println("SuggestionsID " + (i + 1));
            SuggestionsService.printSuggestions(sList.get(i));
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
    
    public void deleteSuggestion(){
        if(!SuggestionsService.submittedSuggestions()){
            System.out.println("You have not submitted any suggestions");
            return;
        }
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Suggestions> sList = camp.getSuggestionList();
        this.viewSuggestion();
        System.out.println("Which suggestion you would like to delete: ");
        int index = HelperService.readInt(1,sList.size() , "Invalid Suggestion ID");
        Suggestions s = sList.get(index -1);
        while(!s.getSuggestBy() .equals(AuthData.getCurrentUser().getUserID())){
            System.out.println("Unable to edit suggestion from other committee member. Please try again");
            index = HelperService.readInt(1,sList.size() , "Invalid Suggestion ID");
            return;
        }
        s = sList.get(index -1);
        if(!s.getStatus().toString().equals("NEW")){
            System.out.println("Unable to delete.\nYour suggestion is currently under process or has been processed");
            return;
        }
        else{
            sList.remove(index);
            System.out.println("Your suggestion has been deleted...");
        }
    }

    //generate report
    public void generateStudentReport(){
        System.out.println("Press 1 to filter report by faculty.(Any number to generate by default)");
        int filter = HelperService.readInt();
        if(filter == 1){
          System.out.println("Faculty: ");
          String faculty = sc.nextLine();
          System.out.println("Generating student attendence report of " + faculty.toUpperCase() + "...");
          CampManagementService.generateStudentListReport(faculty.toUpperCase());
        }
        else{
          System.out.println("Generating student attendence report...");
          CampManagementService.generateStudentListReport();
        }
        
      }
      public void generateEnquiriesReport(){
        System.out.println("Press 1 to filter report by faculty.(Any number to generate by default)");
        int filter = HelperService.readInt();
        if(filter == 1){
          System.out.println("Faculty: ");
          String faculty = sc.nextLine();
          ColouredTextPrinter.printYellow("Generating enquiries report of " + faculty.toUpperCase() + "...\n");
          CampManagementService.enquiriesReport(faculty.toUpperCase());
        }
        else{
          ColouredTextPrinter.printYellow("Generating enquiries report...\n");
          CampManagementService.enquiriesReport();
        }
      }
  

    

   
    

}
