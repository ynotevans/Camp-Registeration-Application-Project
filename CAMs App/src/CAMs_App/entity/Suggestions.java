package CAMs_App.entity;

import CAMs_App.enums.SuggestionStatus;

// import java.nio.file.FileAlreadyExistsException;

public class Suggestions {
    private String suggestion;
    private String suggestBy;  
    private SuggestionStatus status;
    private Boolean accepted;

    public Suggestions(String suggestion, String suggestBy){
        this.suggestion = suggestion;
        this.suggestBy = suggestBy;
        this.status = SuggestionStatus.NEW;
        this.accepted = null;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    public void setSuggestBy(String suggestBy) {
        this.suggestBy = suggestBy;
    }

    public void setSuggestionStatus(String status){
        this.status = SuggestionStatus.valueOf(status);
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getSuggestBy() {
        return suggestBy;
    }

    public SuggestionStatus getStatus() {
        return this.status;
    }

    public Boolean getAccepted() {
        return accepted;
    }
}
