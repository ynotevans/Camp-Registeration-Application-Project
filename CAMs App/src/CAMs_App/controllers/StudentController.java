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
            StudentCampService.registerAsAttendee(campName);
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

        System.out.println("Are you sure you want to withdraw from this camp? (Y/N)");

        char ans = sc.next().charAt(0);

        if(ans == 'Y'){
            if(currentUser.getIsComittee()){
                StudentCampService.withdrawCamp(campName, currentUser, true);
            }
            else{
                StudentCampService.withdrawCamp(campName, currentUser, false);
            }
        }
        else{
            System.out.println("cancel withdraw...");
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
        
        for(int i=0;i<qList.size();i++) {
        	if(qList.get(i).getInquirer()==student.getUserID()) {
        		HelperService.viewEnquiries(qList.get(i));
        	}
        }
    }

    public void createEnquiry(){
    	Camp camp = AuthData.getCurrentCamp();
    	System.out.println("Please provide your enquiry: ");
        String enquiry = sc.next();
    	
        EnquiriesService.createEnquiries(camp.getCampName(), enquiry);
    }

    public void editEnquiries(){
    	Camp camp = AuthData.getCurrentCamp();
    	Student student = (Student)AuthData.getCurrentUser();
    	ArrayList<Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        
        for(int i=0;i<qList.size();i++) {
        	if(qList.get(i).getInquirer()==student.getUserID()) {
        		if(qList.get(i).getProcessed() == true){
                    System.out.println("Your enquiry has been processed!!");
                }
        		else {
        			HelperService.viewEnquiries(qList.get(i));
        			System.out.println("Please edit your enquiry: ");
                    String editEnquiry = sc.next();
                    EnquiriesService.editEnquiries(camp.getCampName(), i, editEnquiry);
        		}
        	}
        }
    }

    public void deleteEnquiries(){
    	Camp camp = AuthData.getCurrentCamp();
    	Student student = (Student)AuthData.getCurrentUser();
        ArrayList<Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        
        for(int i=0;i<qList.size();i++) {
        	if(qList.get(i).getInquirer()==student.getUserID()) {
        		if(qList.get(i).getProcessed() == true){
                    System.out.println("Your enquiry has been processed!!");
                }
        		else {
        			HelperService.viewEnquiries(qList.get(i));
        			System.out.println("Comfirm to delete (y/n): ");
                    String confirm = sc.next();
                    if(confirm=="y") {
                    	EnquiriesService.deleteEnquiry(camp.getCampName(), i);
                    	System.out.println("Successfully deleted. ");
                    }
        		}
        	}
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
