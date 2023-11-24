package CAMs_App.controllers;

import java.util.ArrayList;

import CAMs_App.boundary.CampComMenu;
import CAMs_App.boundary.StudentMenu;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.EnquiriesService;
import CAMs_App.service.HelperService;
import CAMs_App.service.StudentCampService;


public class StudentController extends UserController {
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

        if (camp.getRemainingSlot() == 0) {
            System.out.println("Camp is full!");
        }

        else if (registeredCamp.contains(campName)){
            System.out.println("Camp has been registered already.");
        }

        else {
            StudentCampService.registerAsAttendee();
            System.out.println("Successfully Registered as attendee!\n");
        }
    }

    public void joinAsCommittee(){
        Student student = (Student)AuthData.getCurrentUser();
        String campName = AuthData.getCurrentCamp().getCampName();
        Camp camp = DatabaseService.getCamp(campName);
        ArrayList<String> registeredCamp = student.getRegisteredCamp();

        if (camp.getCampCommitteeSlots() == 0) {
            System.out.println("Camp Committee slots are full!");
        }

        else if(registeredCamp.contains(campName)) {
            System.out.println("Camp has been registered already. Going back previous menu...\n");
        }
        
        
        else {
            StudentCampService.registerAsCommittee(campName);
            System.out.println("Successfully Registered as committee!\n");
        }


    }

    public void withdrawCamp(){
        String campName = AuthData.getCurrentCamp().getCampName();

        System.out.println("Are you sure you want to withdraw from this camp? (Y to confirm , any key to cancel)");

        char ans = sc.next().toUpperCase().charAt(0);

        if(ans == 'Y'){
            if(currentUser.getIsComittee()){
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
    	Student student = (Student)AuthData.getCurrentUser();
        ArrayList<Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        
        for(int i=0;i<qList.size();i++){
        	if(qList.get(i).getInquirer()==student.getUserID()) {
                System.out.println("Your submitted enquiries on this camp");
                System.out.println("Enquiry: " + i + 1);
        		HelperService.viewEnquiries(qList.get(i));
                System.out.println(" ");
        	}
        }
    }

    public void createEnquiry(){
    	Camp camp = AuthData.getCurrentCamp();
    	System.out.println("Please provide your enquiry: ");
        sc.nextLine();
        String enquiry = sc.nextLine();
    	
        EnquiriesService.createEnquiries(camp.getCampName(), enquiry);
    }

    public void editEnquiries(){
    	Camp camp = AuthData.getCurrentCamp();
    	Student student = (Student)AuthData.getCurrentUser();
        if(!EnquiriesService.submittedEnquiries(student.getUserID(), camp)){
            System.out.println("You do not have any submitted enquiry");
            return;
        }
    	ArrayList<Enquiries> qList = camp.getEnquiryList();
        
        this.viewEnquiry();
        System.out.println("Which enquiry you would like to edit ?");
        int index = HelperService.readInt(1 , qList.size() , "Enquiry index out of bound");
        
        while(!(qList.get(index).getInquirer()==student.getUserID())) {
        	System.out.println("Invalid enquiry id, please try again");
            index = HelperService.readInt(1 , qList.size() , "Enquiry index out of bound");
        }
        System.out.println("Enter your new enquiry: ");
        String enquiry = sc.nextLine();
        Enquiries q = qList.get(index-1);
        if(EnquiriesService.editEnquiries(q, enquiry)){
            System.out.println("Enquiry updated");
        }
        else{
            System.out.println("Unable to edit prcoessed enquiry");
        }
    }

    public void deleteEnquiries(){
    	Camp camp = AuthData.getCurrentCamp();
    	Student student = (Student)AuthData.getCurrentUser();
        if(!EnquiriesService.submittedEnquiries(student.getUserID(), camp)){
            System.out.println("You do not have any submitted enquiry");
            return;
        }
        ArrayList<Enquiries> qList = camp.getEnquiryList();

        this.viewEnquiry();
        
        System.out.println("Which enquiry you would like to delete ?");
        int index = HelperService.readInt();
        
        while(!(qList.get(index).getInquirer()==student.getUserID())) {
        	System.out.println("Invalid enquiry id, please try again");
            index = HelperService.readInt();
        }
        
        HelperService.viewEnquiries(qList.get(index - 1));
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
            if(currentUser.getIsComittee()== true){    //need to change the condition
                CampComMenu campComMenu = new CampComMenu();
                campComMenu.viewApp();
            }
            else{
                System.out.println("You are not a committe member of any camp");
                System.out.println("Unable to switch mode !!!\n");
                StudentMenu studentMenu = new StudentMenu();
                studentMenu.printMenu();
            }
        }
        else{
            StudentMenu studentMenu = new StudentMenu();
            studentMenu.viewApp();
        }

    }
}
