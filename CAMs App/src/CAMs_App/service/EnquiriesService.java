package CAMs_App.service;
import CAMs_App.entity.*;

import java.util.ArrayList;

import CAMs_App.data.AuthData;
public final class EnquiriesService {

   
    public static void createEnquiries(String enquiry){
        Camp camp = AuthData.getCurrentCamp();
        User user = AuthData.getCurrentUser();
        Enquiries q = new Enquiries(enquiry,user.getUserID());
        camp.getEnquiryList().add(q);

    }
    public static boolean submittedEnquiries(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> qList = camp.getEnquiryList();
        for(int i = 0 ; i < qList.size() ; i++){
            if(qList.get(i).getInquirer().equals(AuthData.getCurrentUser().getUserID())) return true;
        }
    return false;
    }
    public static void viewEnquiries(Enquiries q){
        System.out.println("Inquirer:" + q.getInquirer());
        System.out.println("Enquiry: " + q.getEnquiry());
  
        System.out.println("Respondent: " + q.getAnswerer());
        System.out.println("Answer: " + q.getAnswer());
        System.out.println(" ");
    }

    public static void viewNewEnquiries(){
     ArrayList <Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        int count = 0;
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            if(!q.getProcessed()){
                count++;
                System.out.println("EnquiriesID: " + (i+1));
                EnquiriesService.viewEnquiries(q);
            }
        }
        if(count == 0) System.out.println("No new enquiries");
    }

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

    public static void viewAllEnquiries(){
        ArrayList <Enquiries> qList = AuthData.getCurrentCamp().getEnquiryList();
        for(int i = 0 ; i < qList.size() ; i++){
            Enquiries q = qList.get(i);
            System.out.println("EnquiriesID: " + (i+1));
            EnquiriesService.viewEnquiries(q);

        }
    }

     // public static void replyEnquiries(Enquiries q ,String reply){
       
    //         q.setAnswer(reply);
    //         q.setAnswerer(AuthData.getCurrentUser().getUserID());
    //         q.setProcessed();
    
    // }
    
    // public static boolean editEnquiries(Enquiries q , String question){
    //    if(q.getProcessed()) return false;
    //    else{
    //     q.setEnquiry(question);
    //    }
    //    return true;
        
    // }

    // public static boolean deleteEnquiry(String CampName , int index){
    //     Camp camp = DatabaseService.getCamp(CampName);
    //     Enquiries q = camp.getEnquiryList().get(index);
    //     if(q.getProcessed()) return false;
    //     else{
    //        camp.getEnquiryList().remove(q);
    //        return true;
    //     }
    // }

    
}
