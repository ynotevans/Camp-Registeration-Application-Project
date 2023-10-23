package CAMs_App;

import java.util.ArrayList;

public class Student extends User {
	//User values
	//UserID
	//password
	//faculty
	
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
	
	public boolean isComittee () {
		return comitteeCamp != null ? true : false;
	}
	
	public boolean checkDateClash (Camp camp) {
		for(int i = 0; i < attending.size(); i++) {
			if (attending(i).date == camp.date) {
				return true;
			}
		}
		return false;
	}
}
