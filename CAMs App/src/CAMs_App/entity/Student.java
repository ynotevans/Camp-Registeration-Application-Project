package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;

public class Student extends User {
	private boolean isCommittee = false;
	private ArrayList<String> registeredCamp;
	private ArrayList<String> withdrawnCamp;
	private Camp committeeCamp = null;
	private int points = 0;
	private CampComMem campCompMem = new CampComMem();
	
	public Student(String userID, String password, Faculty faculty, String name) {
		super(userID, password, faculty, name);
		isCommittee = false; 
		committeeCamp = null;
		points = 0;
	}
	
	public boolean getIsComittee () {
		return isCommittee;
	}
	
	public void setIsComittee(boolean isCommittee) {
		this.isCommittee = isCommittee;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public ArrayList<String> getRegisteredCamp() {
		return registeredCamp;
	}
	
	public void setRegisteredCamp(ArrayList<String> registeredCamp) {
		this.registeredCamp = registeredCamp;
	}
	
	public ArrayList<String> getWithdrawnCamp() {
		return withdrawnCamp;
	}
	
	public void setWithdrawnCamp(ArrayList<String> withdrawnCamp) {
		this.withdrawnCamp = withdrawnCamp;
	}
	
	public Camp getComitteeCamp() {
		return committeeCamp;
	}
	
	public void setCommitteeCamp(Camp camp) {
		this.committeeCamp = camp;
	}
	
	public void setCampComMem(String position){
		this.campCompMem.setPosition(position);
	}

	public CampComMem getCampComMem(){
		return this.campCompMem;
	}

}
