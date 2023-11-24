package CAMs_App.entity;

import java.util.*;

import CAMs_App.enums.*;

public class CampCompMem {
	private ArrayList<Suggestions> suggestions;
	private String position;

	public CampCompMem(String postion){
		this.position = postion;
	};
	public CampCompMem(){}
	
	
	
	public ArrayList<Suggestions> getSuggestion() {
		return suggestions;
	}

	public void setSuggestion(Suggestions s) {
		this.suggestions.add(s);
	}
    
    public String getPosition() {
    	return position;
    }
    
    public void setPosition(String post) {
    	position = post;
    }


}
