package CAMs_App.entity;

import java.nio.file.FileAlreadyExistsException;

public class Suggestions {
    private String suggestion;
    private int suggestBy;  //UserID, use string?
    private Boolean processed;
    private Boolean accepted;

    public Suggestions(String suggestion, int suggestBy){
        this.suggestion = suggestion;
        this.suggestBy = suggestBy;
        this.processed = false;
        this.accepted = false;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    public void setSuggestBy(int suggestBy) {
        this.suggestBy = suggestBy;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public int getSuggestBy() {
        return suggestBy;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public Boolean getAccepted() {
        return accepted;
    }
}
