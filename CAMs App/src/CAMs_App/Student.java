package CAMs_App;

import java.util.ArrayList;

public class Student extends User {
	
	public Student(int userID, String password, String faculty) {
		super(userID, password, faculty);
	}
	
	private ArrayList<Camp> camp_registered = new ArrayList<>();
	private ArrayList<Camp> camp_withdrawn = new ArrayList<>();
	private Camp comitteeCamp = null;
	private int points = 0;
	
	public void submitEnquiry() {return;}
	public void viewEnquiry() {return;}
	public void editEnquiry() {return;}
	public void deleteEnquiry() {return;}
	
	public void registerCamp (Camp camp) {
		if(camp_registered.contains(camp)) {	
			System.out.println("Student has already registered for this camp.");
			return;
		}
		if(camp_withdrawn.contains(camp)) {	
			System.out.println("Student has already withdrawn from this camp.");
			return;
		}
		if(comitteeCamp == camp) {
			System.out.println("Student is already comittee of camp.");
			return;
		}
		if(checkDateClash(camp)) {
			System.out.println("There is a date clash.");
			return;
		}
		camp_registered.add(camp);
		
	}
	
	public void withdrawCamp (Camp camp) {
		for(int i = 0; i < camp_registered.size(); i++) {
			if (camp_registered.get(i) == camp) {
				camp_registered.remove(i);
				camp_withdrawn.add(camp);
				comitteeCamp = comitteeCamp == camp ? null : comitteeCamp;
				break;
			}
		}
		System.out.println("Student is not registered to this camp.");
	}
	
	public boolean isComittee () {
		return comitteeCamp != null ? true : false;
	}
	
	public boolean checkDateClash (Camp camp) {
		for(int i = 0; i < camp_registered.size(); i++) {
			if (camp_registered.get(i).getCampInfo().getCampDate() == camp.getCampInfo().getCampDate()) {
				return true;
			}
		}
		return false;
	}
	
	public void setPoints(int i) {
		points = i;
	}
	
	public ArrayList<Camp> getAttendingList () {
		return camp_registered;
	}
	
	public Camp getComitteeCamp () {
		return comitteeCamp;
	}
	
	public int getPoints() {
		return points;
	}
}
