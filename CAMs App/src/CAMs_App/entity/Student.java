package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;

public class Student extends User {
	private boolean isCommittee = false;
	private ArrayList<String> registeredCamp;
	private ArrayList<String> withdrawnCamp;
	private Camp committeeCamp = null;
	private int points = 0;
	private CampCompMem campCompMem = new CampCompMem();
	
	public Student(String userID, String password, Faculty faculty, String name) {
		super(userID, password, faculty, name);
		super.setIdentity(Identity.student);
		isCommittee = false; 
		committeeCamp = null;
		points = 0;
	}
	
	public boolean getIsComittee () {
		return isCommittee;
	}
	
	public void setIsComittee(boolean is_committee) {
		isCommittee = is_committee;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int i) {
		points = i;
	}
	
	public ArrayList<String> getRegisteredCamp() {
		return registeredCamp;
	}
	
	public void setRegisteredCamp(ArrayList<String> list) {
		registeredCamp = list;
	}
	
	public ArrayList<String> getWithdrawnCamp() {
		return withdrawnCamp;
	}
	
	public void setWithdrawnCamp(ArrayList<String> list) {
		withdrawnCamp = list;
	}
	
	public Camp getComitteeCamp() {
		return committeeCamp;
	}
	
	public void setCommitteeCamp(Camp camp) {
		committeeCamp = camp;
	}

	
	public void setCommittee(String position){
		this.campCompMem.setPosition(position);
	}

	public void setSuggestion(Suggestions s){
		this.campCompMem.setSuggestion(s);
	}
	public CampCompMem getCampComm(){
		return this.campCompMem;
	}

}
