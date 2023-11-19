package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;

public class Student extends User {
	private boolean isCommittee;
	private static ArrayList<String> registeredCamp;
	private static ArrayList<String> withdrawnCamp;
	private Camp committeeCamp;
	private int points;
	
	public Student(String userID, String password, Faculty faculty) {
		super(userID, password, faculty);
		super.setIdentity(Identity.student);
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
	
	public static ArrayList<String> getRegisteredCamp() {
		return registeredCamp;
	}
	
	public void setRegisteredCamp(ArrayList<String> list) {
		registeredCamp = list;
	}
	
	public static ArrayList<String> getWithdrawnCamp() {
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

}
