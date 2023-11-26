package CAMs_App.service;
import CAMs_App.entity.*;

import java.util.ArrayList;

import CAMs_App.data.AuthData;
/**
 * The {@link EnquiriesService} class is responsible in managing the enquiries of a specified camp
 * This class provides methods in creating and viewing of enquiries
 */
public final class EnquiriesService {

    /**
     * Creates new enquiry for the current camp
     * @param enquiry : Content of enquiry
     */
    public static void createEnquiries(String enquiry){
        Camp camp = AuthData.getCurrentCamp();
        User user = AuthData.getCurrentUser();
        Enquiries q = new Enquiries(enquiry,user.getUserID());
        camp.getEnquiryList().add(q);

    }
    /**
     * Checks if the user submitted enquiries for the current camp
     * @return {@code true} if the user submitted enquiries, {@code false} otherwise
     */
    public static boolean submittedEnquiries(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> qList = camp.getEnquiryList();
        for(int i = 0 ; i < qList.size() ; i++){
            if(qList.get(i).getInquirer().equals(AuthData.getCurrentUser().getUserID())) return true;
        }
    return false;
    }
    /**
     * Check if there are new enquiries for the current camp
     * @return {@code true} if there are new enquiries, {@code false} otherwise
     */
    public static boolean hasNewEnquiries(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList <Enquiries>  qList = camp.getEnquiryList(); 
        for(int i = 0 ; i < qList.size() ; i++){
            if(!qList.get(i).getProcessed()) return true;
        }
        return false;
    }
    /**
     * Displays the details of a specific enquiry
     * @param q : Enquiry to be displayed
     */
    public static void viewEnquiries(Enquiries q){
        System.out.println("Inquirer:" + q.getInquirer());
        System.out.println("Enquiry: " + q.getEnquiry());
  
        System.out.println("Respondent: " + q.getAnswerer());
        System.out.println("Answer: " + q.getAnswer());
        System.out.println();
    }
    /**
     * Displays the new enquiries for the current camps.
     * If there are no new enquiries, prints "No new enquiries".
     */
    public static void viewNewEnquiries(){
    if(!hasNewEnquiries()) System.out.println("No new enquiries");
     ArrayList <Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            if(!q.getProcessed()){
                System.out.println("EnquiriesID: " + (i+1));
                EnquiriesService.viewEnquiries(q);
            }
        }
    }
    /**
     * Displays the proccessed enquiries for the current camp
     * If there are no processed enquiries, it will print "No processed enquiries"
     */
    public static void viewProcessedEnquiries(){
        ArrayList <Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        int count = 0;
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            if(q.getProcessed()){
                count++;
                System.out.println("EnquiriesID: " + (i+1));
                EnquiriesService.viewEnquiries(q);
            }
        }
        if(count == 0) System.out.println("No processed enquiries");
    }
    /**
     * Displays all enquiries and its details for the current camp
     */
    public static void viewAllEnquiries(){
        ArrayList <Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            System.out.println("EnquiriesID: " + (i+1));
            EnquiriesService.viewEnquiries(q);

        }
    }
 
}
