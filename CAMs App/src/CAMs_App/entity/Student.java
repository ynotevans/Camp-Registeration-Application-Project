package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;

public class Student extends User {
	private boolean isCommittee;
	private ArrayList<String> registeredCamp;
	private ArrayList<String> withdrawnCamp;
	private String committeeCamp;
	private int points;
	
	Student(int userID, String password, Faculty faculty, Status status) {
		super(userID, password, faculty, status);
		isCommittee = false;
		registeredCamp = new ArrayList<String>();
		withdrawnCamp  = new ArrayList<String>();
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
	
	public String getComitteeCamp() {
		return committeeCamp;
	}
	
	public void setCommitteeCamp(String camp) {
		committeeCamp = camp;
	}

}
