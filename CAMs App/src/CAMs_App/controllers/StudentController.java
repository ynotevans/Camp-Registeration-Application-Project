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
import CAMs_App.service.ColouredTextPrinter;
/**
 * The {@link StudentController} class is responsible for handling the
 * student-specific user interface and user interactions. It extends the
 * {@link UserController} class and provides functionality for students to
 * view available and registered camps, register for camps, withdraw from camps,
 * as well as create, edit and delete enquiries to camps they registered for.
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class StudentController extends UserController {
	/**
	 * {@link Scanner} object to get input from the user.
	 */
    Scanner sc = new Scanner(System.in);
    //Student currentUser = (Student)AuthData.getCurrentUser();

    /**
     * Calls {@link StudentCampService} to display all camps available for register to this student.
     * 
     * @return {@code false} if no camps are available, {@code true} if otherwise.
     */
	public boolean viewAvailableCamp(){
        if (StudentCampService.viewAvailableCamps()==0){
            return false;
        }
        return true;
    }

	/**
	 * Registers for the current camp as a regular attendee by
     * calling registerAsAttendee method from the corresponding
     * {@link StudentCampService} object.
	 * The register will fail if:
	 * <ol>
	 * <li>The camp is full</li>
     * <li>The student has already registered for the camp</li>
     * <li>The student has withdrawn from this camp before</li>
     * </ol>
	 */
    public void joinAsAttendee(){
        Student student = (Student)AuthData.getCurrentUser();
        String campName = AuthData.getCurrentCamp().getCampName();
        Camp camp = DatabaseService.getCamp(campName);
        ArrayList<String> registeredCamp = student.getRegisteredCamp();
        ArrayList<String> withdrawCamp = student.getWithdrawnCamp();

        if (camp.getRemainingSlot() == 0) {
            ColouredTextPrinter.printRed("Camp is full!");
        }

        else if (registeredCamp.contains(campName)){
            ColouredTextPrinter.printRed("Camp has been registered already.");
        }

        else if ((withdrawCamp.contains(campName))){
           ColouredTextPrinter.printRed("You have withdrawn from this camp before, not allowed to join again!!");
        }

        else {
            StudentCampService.registerAsAttendee();
            System.out.println("Successfully Registered as attendee!\n");
        }
    }

    /**
     * Register for the current camp as a committee member by
     * calling registerAsCommittee method from the corresponding
     * {@link StudentCampService} object.
     * Prompt user to key in the position as the committee member
	 * The register will fail if:
	 * <ol>
	 * <li>The camp's committee slots are full</li>
     * <li>The student has already registered for the camp</li>
     * </ol>
     * @return {@code true} if register successful, {@code false} if otherwise.
     */
    public boolean joinAsCommittee(){
        Student student = (Student)AuthData.getCurrentUser();
        Camp camp = AuthData.getCurrentCamp();
        if(student.getIsComittee()){
            //check if the student is a committee for any upcoming camp
            // if(!student.getComitteeCamp().getCampEndDate().isAfter(LocalDate.now())){
                 ColouredTextPrinter.printRed("You are not allowed to sign up as committee for more than 1 camp");
                return false; 
            // }
        }
        
        ArrayList<String> registeredCamp = student.getRegisteredCamp();

        if (camp.getCampCommitteeRemainingSlots() == 0) {
            ColouredTextPrinter.printRed("Camp Committee slots are full!");
        }

        else if(registeredCamp.contains(camp.getCampName())){
            System.out.println("Camp has been registered already. Going back previous menu...\n");
        }
        
        
        else {
            System.out.println("Enter desired position to be in the camp committee: ");
            String position = sc.nextLine();
            StudentCampService.registerAsCommittee(position);
            student.setCommitteeCamp(camp);
            System.out.println("Successfully Registered as committee!\n");
            return true;
        }
        return false;

    }

    /**
     * Withdraws the student from the current camp by
     * calling withdrawCamp method from the corresponding
     * {@link StudentCampService} object.
     */
    public void withdrawCamp(){
        String campName = AuthData.getCurrentCamp().getCampName();
        Student currentUser =(Student) AuthData.getCurrentUser();
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
    
    /**
     * Calls viewRegisteredCamp method from {@link StudentCampService} to display 
     * all registered camp of this student.
     * 
     * @return {@code false} if no camps are registered, {@code true} if otherwise.
     */
    public boolean viewRegisteredCamp(){
        if (StudentCampService.viewRegisteredCamp()==0){
            return false;
        }
        return true;
    }

    /**
     * Calls viewEnquiries method from {@link EnquiriesService} to display 
     * all enquiries created by this student.
     */
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

    /**
     * Calls createEnquiries method from {@link EnquiriesService} to create enquiry.
     * Prompts the student to enter a enquiry for the current camp.
     */
    public void createEnquiry(){
    	System.out.println("Please provide your enquiry: ");
        String enquiry = sc.nextLine();
    	
        EnquiriesService.createEnquiries(enquiry);
    }
    
    /**
     * Prompts the student to select a submitted enquiry for edit, 
     * the edit will fail if enquiry is already processed.
     */
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

    /**
     * Prompts the student to select a submitted enquiry to delete, 
     * the delete will fail if enquiry is already processed.
     */
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
        int index = HelperService.readInt(1,qList.size() , "Invalid Enquiry ID, please enter a valid enquiry ID: ");
        Enquiries q = qList.get(index - 1);
        while(!(q.getInquirer().equals(student.getUserID()))) {
        	 ColouredTextPrinter.printRed("Invalid enquiry id, please try again");
             index = HelperService.readInt(1,qList.size() , "Invalid Enquiry ID, please enter a valid enquiry ID: ");
             q = qList.get(index - 1);
        }
        
        if(q.getProcessed()){
            System.out.println("Unable to delete processed enquiries");
            return;
        }
        
        EnquiriesService.viewEnquiries(qList.get(index - 1));
        System.out.println("\n Press Y to confirm deletion , any key to cancel");
        char choice = sc.next().toUpperCase().charAt(0);
        if(choice == 'Y'){
            qList.remove(index - 1);
            System.out.println("Your enquiry has been deleted...");
        }
        else{
            System.out.println("Deletion canceled...");
        }
    }

    /**
     * Switches the current user mode to camp committee mode
     * @param currentMode The current user mode the system is in.
     */
    public void switchMode(int currentMode){
        HelperService.clearScreen();
        //from student switch to camp committee
        Student currentUser = (Student)AuthData.getCurrentUser();
        if(currentMode == 1){
            if(currentUser.getIsComittee()== true){    
                AuthData.setCurrentCamp(currentUser.getComitteeCamp());
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
