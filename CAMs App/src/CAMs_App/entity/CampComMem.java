package CAMs_App.entity;

import java.util.*;

public class CampComMem {
	private ArrayList<Suggestions> suggestions = new ArrayList<>();
	private String position;

	public CampComMem(String postion){
		this.position = postion;
	};
	
	public CampComMem(){}
	
	
	
	public ArrayList<Suggestions> getSuggestion() {
		return suggestions;
	}

	public void setSuggestion(Suggestions s) {
		this.suggestions.add(s);
	}
    
    public String getPosition() {
    	return position;
    }
    
    public void setPosition(String position) {
    	this.position = position;
    }


}
