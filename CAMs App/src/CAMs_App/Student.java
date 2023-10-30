package CAMs_App;

import java.util.ArrayList;

public class Student extends User {
	
	public Student(int userID, String password, String faculty) {
		super(userID, password, faculty);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Camp> camp_registered = new ArrayList<>();
	
	private Camp comitteeCamp = null;
	
	public void registerCamp (Camp camp, boolean comittee) {
		if(comittee) {
			comitteeCamp = camp;
			camp_registered.add(camp);
		} else {
			camp_registered.add(camp);
		}
	}
	
	public void withdrawCamp (Camp camp) {
		for(int i = 0; i < camp_registered.size(); i++) {
			if (camp_registered.get(i) == camp) {
				camp_registered.remove(i);
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
		for(int i = 0; i < camp_registered.size(); i++) {
			if (camp_registered.get(i).getCampInfo().getCampDate() == camp.getCampInfo().getCampDate()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Camp> getAttendingList () {
		return camp_registered;
	}
	
	public Camp getComitteeCamp () {
		return comitteeCamp;
	}
}
