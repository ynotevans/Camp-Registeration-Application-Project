package CAMs_App.service;

import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Enquiries;
import CAMs_App.entity.Suggestions;

public class SuggestionsService {
    
    public boolean approveSuggestions(String CampName, int index, boolean approve){
        Camp camp = DatabaseService.getCamp(CampName);
        Suggestions s = camp.getSuggestionList().get(index);

        if(approve == true){
            s.setAccepted(true);
            return true;
        }else{
            System.out.println("Your suggestion is not accepted. Thank you...");
            s.setAccepted(false);
            return false;
        }
    }

    public void createSuggestion(String CampName, String suggestion){
        Suggestions s = new Suggestions(suggestion, AuthData.getCurrentUser().getUserID());
        Camp camp = DatabaseService.getCamp(CampName);

        camp.getSuggestionList().add(s);

    }

    public boolean editSuggestion(String CampName, int index, String suggestion){
        Camp camp = DatabaseService.getCamp(CampName);
        Suggestions s = camp.getSuggestionList().get(index);
        if(s.getProcessed()) 
            return false;
        else{
            s.setSuggestion(suggestion);
            return true;
        }
    }

    public boolean deleteSuggestion(String CampName, int index){
        Camp camp = DatabaseService.getCamp(CampName);
        Suggestions s = camp.getSuggestionList().get(index);
        if(s.getProcessed()) return false;
        else{
           camp.getSuggestionList().remove(s);
           return true;
        }
    }
}
