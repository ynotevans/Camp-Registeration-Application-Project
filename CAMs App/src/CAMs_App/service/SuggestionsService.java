package CAMs_App.service;

import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Suggestions;

public class SuggestionsService {
    
    public static void approveSuggestions(int index, boolean approve){
        Camp camp = AuthData.getCurrentCamp();
        Suggestions s = camp.getSuggestionList().get(index);

        if(approve == true){
            s.setAccepted(true);
        }else{
            s.setAccepted(false);
        }
    }


    public static void processSuggestions(int index){
        Camp camp = AuthData.getCurrentCamp();
        Suggestions s = camp.getSuggestionList().get(index);
        s.setProcessed(true);
    }

    public static void createSuggestion(String CampName, String suggestion){
        Suggestions s = new Suggestions(suggestion, AuthData.getCurrentUser().getUserID());
        Camp camp = DatabaseService.getCamp(CampName);

        camp.getSuggestionList().add(s);

    }

    public static boolean editSuggestion(String CampName, int index, String suggestion){
        Camp camp = DatabaseService.getCamp(CampName);
        Suggestions s = camp.getSuggestionList().get(index);
        if(s.getProcessed()) 
            return false;
        else{
            s.setSuggestion(suggestion);
            return true;
        }
    }

    public static boolean deleteSuggestion(String CampName, int index){
        Camp camp = DatabaseService.getCamp(CampName);
        Suggestions s = camp.getSuggestionList().get(index);
        if(s.getProcessed()) return false;
        else{
           camp.getSuggestionList().remove(s);
           return true;
        }
    }
}
