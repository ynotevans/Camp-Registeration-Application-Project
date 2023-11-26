package CAMs_App.entity;

import java.util.*;

/**
 * The {@link CampComMem} class represents a committee member of a camp.
 * 
 *  @author Denis Yu
 *  @version 1.0
 *  @since 2023-10-25
 */

public class CampComMem {
	/**
	 * List of {@link Suggestions} this member has submitted.
	 */
	private ArrayList<Suggestions> suggestions = new ArrayList<>();
	/**
	 * The position this member holds in the committee.
	 */
	private String position = "null";
	
	/**
	 * Constructs a {@link CampComMem} object with the given position.
	 * 
	 * @param postion Position of the committee member.
	 */
	public CampComMem(String postion){
		this.position = postion;
	};
	
	/**
	 * Constructs a {@link CampComMem} object with no position.
	 */
	public CampComMem(){}
	
	
	/**
	 * Returns the list of suggestions this member has submitted.
	 * @return The the list of submitted {@link Suggestions}
	 */
	public ArrayList<Suggestions> getSuggestion() {
		return suggestions;
	}
	
	/**
	 * Adds a suggestion to the list of {@link Suggestions} this member has submitted.
	 * 
	 * @param s The suggestion to add.
	 */
	public void setSuggestion(Suggestions s) {
		this.suggestions.add(s);
	}
    
	/**
	 * Returns the position this member holds in the committee.
	 * 
	 * @return the position this member holds in the committee.
	 */
    public String getPosition() {
    	return position;
    }
    
    /**
     * Sets the position this member holds in the committee.
     * @param position The position to set.
     */
    public void setPosition(String position) {
    	this.position = position;
    }


}
