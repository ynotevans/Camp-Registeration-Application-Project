package CAMs_App.entity;

import java.util.*;

import CAMs_App.enums.*;

public class CampCompMem extends Student{
	private ArrayList<Suggestions> suggestions;
	private String position;
	
	public CampCompMem(Student student, String post) {
		super(student.getUserID(), student.getPassword(), Faculty.valueOf(student.getFaculty()));
		super.setIdentity(student.getIdentity());
		position = post;
		suggestions = new ArrayList<>();
	}
	
	public ArrayList<Suggestions> getSuggestion() {
		return suggestions;
	}

	public void setSuggestion(ArrayList<Suggestions> list) {
		suggestions = list;
	}
    
    public String getPosition() {
    	return position;
    }
    
    public void setPosition(String post) {
    	position = post;
    }

	@Override
	public void setUserID(String userID) {
		// TODO Auto-generated method stub
		super.setUserID(userID);
	}

	@Override
	public String getUserID() {
		// TODO Auto-generated method stub
		return super.getUserID();
	}
    
}
