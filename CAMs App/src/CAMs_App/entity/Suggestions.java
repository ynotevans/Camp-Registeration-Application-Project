package CAMs_App.entity;

// import java.nio.file.FileAlreadyExistsException;

public class Suggestions {
    private String suggestion;
    private String suggestBy;  //UserID, use string?
    private Boolean processed;
    private Boolean accepted;

    public Suggestions(String suggestion, String suggestBy){
        this.suggestion = suggestion;
        this.suggestBy = suggestBy;
        this.processed = false;
        this.accepted = false;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    public void setSuggestBy(String suggestBy) {
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

    public String getSuggestBy() {
        return suggestBy;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public Boolean getAccepted() {
        return accepted;
    }
}
