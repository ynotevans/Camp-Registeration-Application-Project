package CAMs_App.controllers;


import java.util.ArrayList;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.enums.Faculty;
import CAMs_App.service.*;

/**
 * The {@link StudentController} class is responsible for handling the
 * camp committee-specific user interface and user interactions. It extends the
 * {@link UserController} class and provides functionality for committee memebers to
 * view and reply to enquiries, create and edit suggestions, as well as generate reports.
 * 
 *  @author Tony
 *  @version 1.0
 *  @since 2023-10-25
 */
public class CampComController extends StudentController{
	/**
	 * The current user as obtained from {@link AuthData}.
	 */
    Student user = (Student)AuthData.getCurrentUser();

    /**
     * View the details of the current camp.
     */
    public void viewCampDetails(){    
        Camp camp = AuthData.getCurrentCamp();
        HelperService.viewCamp(camp);
    }

    /**
     * Award a point to the given {@link Student} .
     * 
     * @param student The student who will be recieving a point.
     */
    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}
      

    /**
     * Calls {@link EnquiriesService} to display a specific enquiry with the given index.
     * @param index Index of the enquiry to view.
     */
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
              HelperService.printRoute("Committee Camp Menu ---> View Processed Enquiries");
              System.out.println("Processed Enquiries:\n");
              EnquiriesService.viewProcessedEnquiries();
              HelperService.pressAnyKeyToContinue();

              break;
          case 2:
              HelperService.clearScreen();
              HelperService.printRoute("Committee Camp Menu ---> View New Enquiries");
              System.out.println("New Enquiries:\n");
              EnquiriesService.viewNewEnquiries();
              HelperService.pressAnyKeyToContinue();
      
              break;
      
          default:
              HelperService.clearScreen();
              HelperService.printRoute("Committee Camp Menu ---> View All Enquiries");
              System.out.println("List of all enquiries:\n");
              EnquiriesService.viewAllEnquiries();
              HelperService.pressAnyKeyToContinue();
              break;

      }
  }
    
    /**
     * Prompts the committee to select an enquiry from the list to reply to award a point to the committee.
     */
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
         ColouredTextPrinter.printRed("Wrong ID , Unable to reply to processed enquiry");
          return;
      }
      System.out.println("Enter your reply: ");
      String reply = sc.nextLine();
      q.setAnswer(reply);
      q.setAnswerer(AuthData.getCurrentUser().getUserID());
      q.setProcessed();
      System.out.println("Enquiries replied, 1 point awarded to you");
      addPoints((Student)AuthData.getCurrentUser());
  }

  //Suggestions
    /**
     * Calls createSuggestion method from {@link SuggestionService} to create suggestion.
     * Prompts the committee to enter a suggestion for their committee camp and award a point to the committee
     */
      public void createSuggestion(){
        System.out.println("Enter your suggestion: ");
        String s = sc.nextLine();
        SuggestionsService.createSuggestion(s);
        System.out.println("Your suggestion has been added, one point awarded to you");
        addPoints((Student)AuthData.getCurrentUser());
    }
    
      /**
       * Calls {@link SuggestionService} to display all suggestions created by this member.
       */
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
    
    /**
     * Prompts the member to select a submitted suggestion for edit. Only allowed to edit when
     * suggestion status is new
     */
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
     /**
      * Prompts the member to select a submitted suggestion for edit,
      * delete will fail if suggestion is processing or processed, 
      * or if suggestion was submitted by someone else.
      */
    public void deleteSuggestion(){
        if(!SuggestionsService.submittedSuggestions()){
            System.out.println("You have not submitted any suggestions");
            return;
        }
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Suggestions> sList = camp.getSuggestionList();
        this.viewSuggestion();
        System.out.println("Which suggestion you would like to delete: ");
        int index = HelperService.readInt(1,sList.size() , "Invalid Suggestion ID, please enter a valid suggestion ID: ");
        Suggestions s = sList.get(index -1);
        while(!s.getSuggestBy() .equals(AuthData.getCurrentUser().getUserID())){
            System.out.println("Unable to edit suggestion from other committee member. Please try again");
            index = HelperService.readInt(1,sList.size() , "Invalid Suggestion ID, please enter a valid suggestion ID:");
            return;
        }
        s = sList.get(index -1);
        if(!s.getStatus().toString().equals("NEW")){
            System.out.println("Unable to delete.\nYour suggestion is currently under process or has been processed");
            return;
        }
        else{
            sList.remove(index-1);
            System.out.println("Your suggestion has been deleted...");
        }
    }

    //generate report
    /**
     * Calls {@link CampManagementService} to generate student list report, can
     * choose to filter by {@link Faculty}.
     */
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
    /**
     * Calls {@link CampManagementService} to generate enquiry list report, can
     * choose to filter by {@link Faculty}.
     */
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

 /**
     * Calls {@link CampManagementService} to generate enquiry list report in .txt file, can
     * choose to filter by {@link Faculty}.
     */
      public void EnquiriesReportFile(){
        System.out.println("Press 1 to filter report by faculty.(Any number to generate by default)");
        int filter = HelperService.readInt();
        if(filter == 1){
          System.out.println("Faculty: ");
          String faculty = sc.nextLine();
          ColouredTextPrinter.printYellow("Generating committee enquiries report of " + faculty.toUpperCase() + " in .txt...\n");
          CampManagementService.enquiriesReportTXT(faculty.toUpperCase());
        }
        else{
          ColouredTextPrinter.printYellow("Generating enquiries report in .txt...\n");
          CampManagementService.enquiriesReportTXT();
        }
      }

/**
     * Calls {@link CampManagementService} to generate student list report in .txt file, can
     * choose to filter by {@link Faculty}.
     */
      public void studentReportFile(){
        System.out.println("Press 1 to filter report by faculty.(Any number to generate by default)");
        int filter = HelperService.readInt();
        if(filter == 1){
        System.out.println("Faculty: ");
        String faculty = sc.nextLine();
        ColouredTextPrinter.printYellow("Generating student attendence report of " + faculty.toUpperCase() + " in .txt...\n");
        CampManagementService.StudentListTXT(faculty.toUpperCase());
        }
        else{
            ColouredTextPrinter.printYellow("Generating student attendence report in .txt...\n");
            CampManagementService.StudentListTXT();
        }
    }
  

    

   
    

}
