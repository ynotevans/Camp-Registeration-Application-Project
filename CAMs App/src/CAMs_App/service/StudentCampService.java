package CAMs_App.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;
import CAMs_App.enums.Faculty;

public class StudentCampService {
	
	private static boolean checkClash(Camp camp1 , Camp camp2){
		LocalDate camp1Start = camp1.getCampDate();
		LocalDate camp1End  = camp1Start.plusDays(camp1.getNumberOfCampDays());

		LocalDate camp2Start = camp2.getCampDate();
		LocalDate camp2End  = camp2Start.plusDays(camp2.getNumberOfCampDays());

		boolean isClash = !((camp1End.isBefore(camp2Start)) || (camp1Start.isAfter(camp2End)));
    return isClash;
	}

	public static boolean checkCampClashDate(){
		Student student = (Student)AuthData.getCurrentUser();
		Camp newCamp = AuthData.getCurrentCamp(); //to check clash date with this camp
		
		ArrayList<String> registeredCamp = student.getRegisteredCamp();
		Camp camp; //camp registered by current student

		for(int i = 0 ; i < registeredCamp.size() ; i++){
			camp = DatabaseService.getCamp(registeredCamp.get(i));
			if(!checkClash(newCamp , camp)) continue;
			return true;
		}
	return false;
	}

	public static boolean checkRegisterDeadline(){
		Camp camp = AuthData.getCurrentCamp();
		if(camp.getRegCloseDate().isBefore(LocalDate.now()))return true;
		return false;

	}

	public static boolean isCampFull(){
		Camp camp = AuthData.getCurrentCamp();
		if(camp.getRemainingSlot() == 0) return true;
		return false;
	}

	public static void updateRemainingSlot(){
		Camp camp = AuthData.getCurrentCamp();
		camp.setRemainingSlot(camp.getRemainingSlot()-1);
	}

	public static boolean checkWithdrawBefore(){
		Student user = (Student)AuthData.getCurrentUser();
		ArrayList<String> withdraw = user.getWithdrawnCamp();
		Camp camp = AuthData.getCurrentCamp();
		
		for(int i = 0 ; i < withdraw.size() ; i++){
			if(withdraw.get(i) == camp.getCampName()) return true; 
		}
		return false;
	}

	public static void registerAsAttendee(){
		Student user = (Student)AuthData.getCurrentUser();
		Camp camp = AuthData.getCurrentCamp();
		camp.addAttendees(user);
		ArrayList<String> registeredCamp = user.getRegisteredCamp();
		registeredCamp.add(camp.getCampName());
		camp.setRemainingSlot(camp.getRemainingSlot()-1);
		
	}
	
	public static void registerAsCommittee(String position){
		Student student = (Student)AuthData.getCurrentUser();
		Camp camp = AuthData.getCurrentCamp();
	
		student.setCommittee(position);
		camp.addCommittee(student);
		student.getRegisteredCamp().add(camp.getCampName());
		student.setIsComittee(true);
		camp.setCampCommitteeRemainingSlots(camp.getCampCommitteeRemainingSlots()-1);
	}

	// view available camp
    public static int viewAvailableCamps(){
		int i=0;
        Map<String, Camp> camp1 = Database.getCampData(); 

        for (Camp camp : camp1.values()){
			if((camp.getVisibility() == true && camp.getUserGroup().toString() == AuthData.getCurrentUser().getFaculty()) 
				|| camp.getUserGroup() == Faculty.NTU){
					HelperService.viewCamp(camp);
					i++;
				}
        }
		return i;
    }

	public static int viewRegisteredCamp(){
		Student user = (Student)AuthData.getCurrentUser();
		int i = 0;
		ArrayList<String> registeredCamp = user.getRegisteredCamp();
		if(registeredCamp != null){
			for (String camp : registeredCamp){
				System.out.println(camp);
				i++;
			}
		}
		return i;
    }


	public static void withdrawCamp(String campName, Student student, boolean isComittee){
		Camp camp = DatabaseService.getCamp(campName);
		// Student user = (Student)AuthData.getCurrentUser();
		ArrayList<String> registeredCamp = student.getRegisteredCamp();
		
		if(isComittee){
			System.out.println("Camp Committee cannot quit camp!!!");
		}

		else if (!(registeredCamp.contains(campName))){
			System.out.println("You are not registered to this camp, cannot withdraw. Going back previous menu...");

		}
		
		else {
			registeredCamp.remove(campName);
			camp.getAttendees().remove(student);
			camp.setRemainingSlot(camp.getRemainingSlot()+1);
			System.out.println("Withdrawn successfully!");
		}
		
	}

	
	
	
	
}