package CAMs_App.service;
import CAMs_App.entity.*;

import java.util.ArrayList;

import CAMs_App.data.AuthData;
public final class EnquiriesService {

    public static void replyEnquiries(Enquiries q ,String reply){
       
            q.setAnswer(reply);
            q.setAnswerer(AuthData.getCurrentUser().getUserID());
            q.setProcessed();
    
    }

    public static void createEnquiries(String CampName , String question){
        Enquiries q = new Enquiries(question,AuthData.getCurrentUser().getUserID());
        Camp camp = DatabaseService.getCamp(CampName);

        camp.getEnquiryList().add(q);

    }

    public static boolean editEnquiries(Enquiries q , String question){
       if(q.getProcessed()) return false;
       else{
        q.setEnquiry(question);
       }
       return true;
        
    }

    public static boolean deleteEnquiry(String CampName , int index){
        Camp camp = DatabaseService.getCamp(CampName);
        Enquiries q = camp.getEnquiryList().get(index);
        if(q.getProcessed()) return false;
        else{
           camp.getEnquiryList().remove(q);
           return true;
        }
    }

    public static boolean submittedEnquiries(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Enquiries> qList = camp.getEnquiryList();
        for(int i = 0 ; i < qList.size() ; i++){
            if(qList.get(i).getInquirer() == AuthData.getCurrentUser().getUserID()) return true;
        }
    return false;
    }
}
