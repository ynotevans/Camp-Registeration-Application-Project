package CAMs_App.entity;

import CAMs_App.enums.SuggestionStatus;

// import java.nio.file.FileAlreadyExistsException;

/**
 * The {@link Suggestions} class represents a suggestion submitted to a camp
 * by a camp committee member.
 * 
 *  @author Wan Ismail
 *  @version 1.0
 *  @since 2023-10-25
 */
public class Suggestions {
	/**
	 * The message of the suggestion.
	 */
    private String suggestion;
    /**
     * The userID of the committee member who made the suggestion.
     */
    private String suggestBy;  
    /**
     * The {@link SuggestionStatus} of the suggestion.
     */
    private SuggestionStatus status;
    /**
     * The accepted status of the suggestion.
     */
    private Boolean accepted;
    
    /**
     * Constructs a {@link Suggestions} object with the given info.
     * 
     * @param suggestion The message of the suggestion.
     * @param suggestBy The name of the committee member who made the suggestion.
     */
    public Suggestions(String suggestion, String suggestBy){
        this.suggestion = suggestion;
        this.suggestBy = suggestBy;
        this.status = SuggestionStatus.NEW;
        this.accepted = false;
    }
    
    /**
     * Sets the message of the suggestion.
     * 
     * @param suggestion The text to be set.
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
    /**
     * Sets the committee member who made the suggestion.
     * 
     * @param suggestBy The name of the suggester.
     */
    public void setSuggestBy(String suggestBy) {
        this.suggestBy = suggestBy;
    }
    
    /**
     * Sets the {@link SuggestionStatus} of the suggestion.
     * 
     * @param status The {@link SuggestionStatus} to be set.
     */
    public void setSuggestionStatus(String status){
        this.status = SuggestionStatus.valueOf(status);
    }
    
    /**
     * Sets the status of the suggestion to accepted.
     */
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
    
    /**
     * Gets the message of the suggestion.
     * 
     * @return The message in string form.
     */
    public String getSuggestion() {
        return suggestion;
    }
    
    /**
     * Gets the committee member who made the suggestion.
     * 
     * @return The name of the suggester.
     */
    public String getSuggestBy() {
        return suggestBy;
    }
    
    /**
     * Gets the {@link SuggestionStatus} of the suggestion.
     * 
     * @return The {@link SuggestionStatus}.
     */
    public SuggestionStatus getStatus() {
        return this.status;
    }
    
    /**
     * Gets the accepted status of the suggestion.
     * 
     * @return The accepted status.
     */
    public Boolean getAccepted() {
        return accepted;
    }
}
