package CAMs_App.service;

import java.util.ArrayList;

import CAMs_App.data.AuthData;
import CAMs_App.entity.*;


public class SuggestionsService {
    
   
    public static void createSuggestion(String suggestion){
        Camp camp = AuthData.getCurrentCamp();
        User user = AuthData.getCurrentUser();
        Suggestions s = new Suggestions(suggestion, user.getUserID());
      
        camp.getSuggestionList().add(s);

    }

 

    public static boolean submittedSuggestions(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Suggestions> sList = camp.getSuggestionList();
        for(int i = 0 ; i < sList.size() ; i++){
            if(sList.get(i).getSuggestBy() == AuthData.getCurrentUser().getUserID()) return true;
        }
    return false;
    }

    public static void printSuggestions(Suggestions s){
        System.out.println("Suggested by: " + s.getSuggestBy());
        System.out.println("Suggested by: " + s.getSuggestion());
        
        System.out.print("Status: ");     
        if(s.getProcessed()){
          if(s.getAccepted()){
            System.out.println("Suggestion accepted!!");
          }
          else{
            System.out.println("Suggestion rejected");
          }
        }
        else{
          System.out.println("Suggestion under process...");
        }
      }
       //public static void approveSuggestions(int index, boolean approve){
    //     Camp camp = AuthData.getCurrentCamp();
    //     Suggestions s = camp.getSuggestionList().get(index);

    //     if(approve == true){
    //         s.setAccepted(true);
    //     }else{
    //         s.setAccepted(false);
    //     }

    //     s.setProcessed(true);
    // }


    // public static void processSuggestions(int index){
    //     Camp camp = AuthData.getCurrentCamp();
    //     Suggestions s = camp.getSuggestionList().get(index);
    //     s.setProcessed(true);
    // }
       // public static boolean editSuggestion(String CampName, int index, String suggestion){
    //     Camp camp = DatabaseService.getCamp(CampName);
    //     Suggestions s = camp.getSuggestionList().get(index);
    //     if(s.getProcessed()) 
    //         return false;
    //     else{
    //         s.setSuggestion(suggestion);
    //         return true;
    //     }
    // }

    // public static boolean deleteSuggestion(String CampName, int index){
    //     Camp camp = DatabaseService.getCamp(CampName);
    //     Suggestions s = camp.getSuggestionList().get(index);
    //     if(s.getProcessed()) return false;
    //     else{
    //        camp.getSuggestionList().remove(s);
    //        return true;
    //     }
    // }

    
}

