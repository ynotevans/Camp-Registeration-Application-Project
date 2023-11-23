package CAMs_App.service;
import CAMs_App.entity.*;
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

    public static boolean editEnquiries(String CampName , int index , String question){
        Camp camp = DatabaseService.getCamp(CampName);
        Enquiries q = camp.getEnquiryList().get(index);
        if(q.getProcessed()) return false;
        else{
            q.setEnquiry(question);
            return true;
        }
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
}
