package CAMs_App.service;

import java.util.ArrayList;

import CAMs_App.data.AuthData;
import CAMs_App.entity.*;

/**
 * The class {@link SuggestionsService} provides methods in managing the Suggestions of a specific camp
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class SuggestionsService {
    
    /**
     * Creates a new suggestion for the current camp
     * @param suggestion : The content of the suggestion
    */
    public static void createSuggestion(String suggestion){
        Camp camp = AuthData.getCurrentCamp();
        User user = AuthData.getCurrentUser();
        Suggestions s = new Suggestions(suggestion, user.getUserID());
        s.setSuggestionStatus("NEW");
        camp.getSuggestionList().add(s);

    }

     /**
     * Checks if there is any new suggestions for the current camp
     * @return {@code true} if there is at least 1 new suggestion, {@code false } otherwise
    */
    public static boolean hasNewSuggestion(){
        ArrayList <Suggestions> sList = AuthData.getCurrentCamp().getSuggestionList();
        for(int i = 0 ; i < sList.size() ;i++){
          if(sList.get(i).getStatus().toString().equals("NEW")) return true;
        }
        return false;
    }
    /**
     * Checks if there is any suggestions under process for the current camp
     * @return {@code true} if there is at least one processing suggestion, {@code false } otherwise
    */
    public static boolean hasProcessingSuggestion(){
        ArrayList <Suggestions> sList = AuthData.getCurrentCamp().getSuggestionList();
        for(int i = 0 ; i < sList.size() ;i++){
          if(sList.get(i).getStatus().toString().equals("PROCESSING")) return true;
        }
        return false;
    }
    /**
     * Checks if there is any suggestions that have been proccessed for the current camp
     * @return {@code true} if there is at least one processed suggestion,{@code false} otherwise
    */
    public static boolean hasProcessedSuggestion(){
         ArrayList <Suggestions> sList = AuthData.getCurrentCamp().getSuggestionList();
        for(int i = 0 ; i < sList.size() ;i++){
          if(sList.get(i).getStatus().toString().equals("PROCESSED")) return true;
        }
        return false;
    }
    /**
     * Check if current user have submitted any suggestions for the current camp
     * @return {@code true} if the user submitted at least one suggestion, {@code false} otherwise
    */
    public static boolean submittedSuggestions(){
        Camp camp = AuthData.getCurrentCamp();
        ArrayList<Suggestions> sList = camp.getSuggestionList();
        for(int i = 0 ; i < sList.size() ; i++){
            if(sList.get(i).getSuggestBy().equals(AuthData.getCurrentUser().getUserID())) return true;
        }
    return false;
    }
    /**
     * Prints the given suggestion details
     * @param suggestion : Prints the selected suggestion
    */
    public static void printSuggestions(Suggestions suggestion){
        System.out.println("Suggested by: " + suggestion.getSuggestBy());
        System.out.println("Suggestion details : " + suggestion.getSuggestion());
        
        System.out.print("Status: ");     
        if(suggestion.getStatus().toString().equals("NEW")){
         System.out.println("Suggestion pending process...");
        }
        else if(suggestion.getStatus().toString().equals("PROCESSING")){
          System.out.println("Suggestion is under process");
        }
        else{
          if(suggestion.getAccepted()){
            System.out.println("Suggestion accepted");
          }
          else{
            System.out.println("Suggestion rejected");
          }
        }

      }
    }