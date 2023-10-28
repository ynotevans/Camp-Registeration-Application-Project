package CAMs_App;

public class Suggestions {
    private String suggestion;
    private CampCompMem suggestBy;
    private Boolean processed;
    private Boolean accepted;

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    public void setSuggestBy(CampCompMem suggestBy) {
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

    public CampCompMem getSuggestBy() {
        return suggestBy;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public Boolean getAccepted() {
        return accepted;
    }
}
