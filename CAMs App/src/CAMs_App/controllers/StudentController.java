package CAMs_App.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import CAMs_App.boundary.CampComMenu;
import CAMs_App.boundary.StudentMenu;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.EnquiriesService;
import CAMs_App.service.HelperService;
import CAMs_App.service.StudentCampService;


public class StudentController extends UserController {
    Scanner sc = new Scanner(System.in);
    static Student currentUser = (Student)AuthData.getCurrentUser();

	public boolean viewAvailableCamp(){
        if (StudentCampService.viewAvailableCamps()==0){
            return false;
        }
        return true;
    }

    public void joinAsAttendee(){
        Student student = (Student)AuthData.getCurrentUser();
        String campName = AuthData.getCurrentCamp().getCampName();
        Camp camp = DatabaseService.getCamp(campName);
        ArrayList<String> registeredCamp = student.getRegisteredCamp();
        ArrayList<String> withdrawCamp = student.getWithdrawnCamp();

        if (camp.getRemainingSlot() == 0) {
            System.out.println("Camp is full!");
        }

        else if (registeredCamp.contains(campName)){
            System.out.println("Camp has been registered already.");
        }

        else if ((withdrawCamp.contains(campName))){
            System.out.println("You have withdrawn from this camp before, not allowed to join again!!");
        }

        else {
            StudentCampService.registerAsAttendee();
            System.out.println("Successfully Registered as attendee!\n");
        }
    }

    public boolean joinAsCommittee(){
        Student student = (Student)AuthData.getCurrentUser();
        Camp camp = AuthData.getCurrentCamp();
        if(student.getIsComittee()){
            //check if the student is a committee for any upcoming camp
            // if(!student.getComitteeCamp().getCampEndDate().isAfter(LocalDate.now())){
                System.out.println("You are not allowed to sign up as committee for more than 1 camp");
                return false; 
            // }
        }
        
        ArrayList<String> registeredCamp = student.getRegisteredCamp();

        if (camp.getCampCommitteeRemainingSlots() == 0) {
            System.out.println("Camp Committee slots are full!");
        }

        else if(registeredCamp.contains(camp.getCampName())){
            System.out.println("Camp has been registered already. Going back previous menu...\n");
        }
        
        
        else {
            System.out.println("Enter desired position to be in the camp committee: ");
            String position = sc.next();
            StudentCampService.registerAsCommittee(position);
            student.setCommitteeCamp(camp);
            System.out.println("Successfully Registered as committee!\n");
            return true;
        }
        return false;

    }

    public void withdrawCamp(){
        String campName = AuthData.getCurrentCamp().getCampName();
        Camp camp = DatabaseService.getCamp(campName);
        System.out.println("Are you sure you want to withdraw from this camp? (Y to confirm , any key to cancel)");

        char ans = sc.next().toUpperCase().charAt(0);

        if(ans == 'Y'){
            if(currentUser.getComitteeCamp() == camp){
                StudentCampService.withdrawCamp(campName, currentUser, true);
            }
            else{

                StudentCampService.withdrawCamp(campName, currentUser, false);
   
            }
        }
        else{
            System.out.println("Cancelling withdraw...");
        }

        
    }
    
    public boolean viewRegisteredCamp(){
        if (StudentCampService.viewRegisteredCamp()==0){
            return false;
        }
        return true;
    }

    public void viewEnquiry(){
        if(!EnquiriesService.submittedEnquiries()){
            System.out.println("You have not submitted any enquiry on this camp");
            return;
        }
    	Student student = (Student)AuthData.getCurrentUser();
        ArrayList<Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        
        for(int i=0;i<qList.size();i++){
        	if(qList.get(i).getInquirer().equals(student.getUserID())) {
                System.out.println("Your submitted enquiries on this camp");
                System.out.println("Enquiry: " + (i + 1));
        		EnquiriesService.viewEnquiries(qList.get(i));
                System.out.println(" ");
        	}
        }
    }

    public void createEnquiry(){
    	System.out.println("Please provide your enquiry: ");
        String enquiry = sc.nextLine();
    	
        EnquiriesService.createEnquiries(enquiry);
    }

    public void editEnquiries(){
    	Camp camp = AuthData.getCurrentCamp();
    	Student student = (Student)AuthData.getCurrentUser();
        if(!EnquiriesService.submittedEnquiries()){
            System.out.println("You do not have any submitted enquiry");
            return;
        }
    	ArrayList<Enquiries> qList = camp.getEnquiryList();
        
        this.viewEnquiry();
        System.out.println("Which enquiry you would like to edit ?");
        int index = HelperService.readInt(1 , qList.size() , "Enquiry index out of bound");
        
        while(!(qList.get(index).getInquirer().equals(student.getUserID()))){
        	System.out.println("Invalid enquiry id, please try again");
            index = HelperService.readInt(1 , qList.size() , "Enquiry index out of bound");
        }
        Enquiries q = qList.get(index -1);
        if(q.getProcessed()){
            System.out.println("Unable to edit prcoessed enquiry");
            return;
        }
        System.out.println("Enter your new enquiry: ");
        String enquiry = sc.nextLine();
        q.setEnquiry(enquiry);
        System.out.println("Enquiries Updated");
    }

    public void deleteEnquiries(){
    	Camp camp = AuthData.getCurrentCamp();
    	Student student = (Student)AuthData.getCurrentUser();
        if(!EnquiriesService.submittedEnquiries()){
            System.out.println("You have not submitted any enquiry");
            return;
        }
        ArrayList<Enquiries> qList = camp.getEnquiryList();

        this.viewEnquiry();
        
        System.out.println("Which enquiry you would like to delete ?");
        int index = HelperService.readInt();
        Enquiries q = qList.get(index - 1);
        while(!(q.getInquirer().equals(student.getUserID()))) {
        	System.out.println("Invalid enquiry id, please try again");
            index = HelperService.readInt();
        }
        q = qList.get(index - 1);
        if(q.getProcessed()){
            System.out.println("Unable to delete processed enquiries");
            return;
        }
        
        EnquiriesService.viewEnquiries(qList.get(index - 1));
        System.out.println("\n Press Y to confirm deletion , any key to cancel");
        char choice = sc.next().toUpperCase().charAt(0);
        if(choice == 'Y'){
            qList.remove(index - 1);
        }
        else{
            System.out.println("Deletion canceled...");
        }
    }


    public void switchMode(int currentMode){
        HelperService.clearScreen();
        //from student switch to camp committee
        if(currentMode == 1){
            if(currentUser.getIsComittee()== true){    
                CampComMenu campComMenu = new CampComMenu();
                campComMenu.viewApp();
            }
        }
        else{
            StudentMenu studentMenu = new StudentMenu();
            studentMenu.viewApp();
        }

    }
}
