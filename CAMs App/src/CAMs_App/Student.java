package CAMs_App;

import java.util.ArrayList;

public class Student extends User {
	
	public Student(int userID, String password, String faculty) {
		super(userID, password, faculty);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Camp> attending = new ArrayList<>();
	private Camp comitteeCamp = null;
	
	public void registerCamp (Camp camp, boolean comittee) {
		if(comittee) {
			comitteeCamp = camp;
			attending.add(camp);
		} else {
			attending.add(camp);
		}
	}
	
	public void withdrawCamp (Camp camp) {
		for(int i = 0; i < attending.size(); i++) {
			if (attending.get(i) == camp) {
				attending.remove(i);
				//TODO ban list
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
		for(int i = 0; i < attending.size(); i++) {
			if (attending.get(i).getCampInfo().getCampDate() == camp.getCampInfo().getCampDate()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Camp> getAttendingList () {
		return attending;
	}
	
	public Camp getComitteeCamp () {
		return comitteeCamp;
	}
}
