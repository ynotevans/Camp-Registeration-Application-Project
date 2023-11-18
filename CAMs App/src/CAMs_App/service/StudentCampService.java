package CAMs_App.service;

import java.time.LocalDate;
import java.util.ArrayList;
import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;

public class StudentCampService {
	
	
	private static boolean checkClash(Camp camp1 , Camp camp2){
		LocalDate camp1Start = camp1.getCampDate();
		LocalDate camp1End  = camp1Start.plusDays(camp1.getNumberOfCampDays());

		LocalDate camp2Start = camp2.getCampDate();
		LocalDate camp2End  = camp2Start.plusDays(camp2.getNumberOfCampDays());

		boolean isClash = !((camp1End.isBefore(camp2Start)) || (camp1Start.isAfter(camp2End)));
    return isClash;
	}

	public static boolean checkCampClashDate(String campName){
		Student student = (Student)AuthData.getCurrentUser();
		Camp newCamp = DatabaseService.getCamp(campName); //to check clash date with this camp
		
		ArrayList<String> registeredCamp = student.getRegisteredCamp();
		Camp camp; //camp registered by current student

		for(int i = 0 ; i < registeredCamp.size() ; i++){
			camp = DatabaseService.getCamp(registeredCamp.get(i));
			if(!checkClash(newCamp , camp)) continue;
			return true;
		}
	return false;
	}

	public static boolean checkRegisterDeadline(String campName){
		Camp camp = DatabaseService.getCamp(campName);
		if(camp.getRegCloseDate().isBefore(LocalDate.now()))return true;
		return false;

	}

	public static boolean isCampFull(String campName){
		Camp camp = DatabaseService.getCamp(campName);
		if(camp.getRemainingSlot() == 0) return true;
		return false;
	}

	public static void upDateRemainingSlot(String campName){
		Camp camp = DatabaseService.getCamp(campName);
		camp.setRemainingSlot(camp.getRemainingSlot()-1);
	}

	public static boolean checkWithdrawBefore(String campName){
		Student user = (Student)AuthData.getCurrentUser();
		ArrayList<String> withdraw = user.getWithdrawnCamp();
		
		for(int i = 0 ; i < withdraw.size() ; i++){
			if(withdraw.get(i) == campName) return true; 
		}
		return false;
	}

	public static void registerAsAttendee(String campName){
		Camp camp = DatabaseService.getCamp(campName);
		camp.addAttendees((Student)AuthData.getCurrentUser());
	}
	public static void registerAsCommittee(String campName){
		Camp camp = DatabaseService.getCamp(campName);
		camp.addCommittee((Student)AuthData.getCurrentUser());
	}

	
	
	
	
}