package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;
import CAMs_App.service.DatabaseService;

public class Student extends User implements Comparable<Student> {
	private boolean isCommittee = false;
	private ArrayList<String> registeredCamp;
	private ArrayList<String> withdrawnCamp;
	private Camp committeeCamp = null;
	private String committeeCampName;
	private int points = 0;
	private CampComMem campComMem = new CampComMem();
	
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

	public void setCommitteeCamp(String campName) {
		this.committeeCamp = DatabaseService.getCamp(campName);
	}
	
	public void setCampComMem(String position){
		this.campComMem.setPosition(position);
	}

	public CampComMem getCampComMem(){
		return this.campComMem;
	}

	public String getCommitteeCampName(){
		return this.committeeCampName;
	}

	public void setCommitteeCampName(String committeeCampName){
		this.committeeCampName = committeeCampName;
	}

	/**
     * Compares the different parameters between students.
     */
	@Override
    public int compareTo(Student other) {
        return Comparator.comparing(Student::getName)
                .thenComparing(Student::getFaculty)
                .compare(this, other);
    }

}
