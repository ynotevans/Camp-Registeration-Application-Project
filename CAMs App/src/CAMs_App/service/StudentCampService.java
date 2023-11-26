package CAMs_App.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;
import CAMs_App.enums.Faculty;
/**
 * The class {@link StudentCampService} is responsible in managing the camp-related functionalities for student users
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class StudentCampService {
	/**
	 * Checks where the two camps clash/overlaps in terms of date
	 * @param camp1 : The first camp
	 * @param camp2 : The second camp
	 * @return {@code true} if the dates do not clash,{@code false } dates do clash
	 */
	private static boolean checkClash(Camp camp1 , Camp camp2){
		LocalDate camp1Start = camp1.getCampDate();
		LocalDate camp1End  = camp1Start.plusDays(camp1.getNumberOfCampDays());

		LocalDate camp2Start = camp2.getCampDate();
		LocalDate camp2End  = camp2Start.plusDays(camp2.getNumberOfCampDays());

		boolean isClash = !((camp1End.isBefore(camp2Start)) || (camp1Start.isAfter(camp2End)));
    return isClash;
	}
	/**
	 * Checks if the camp to be registered clashes with the camps that already has been registered.
	 * @return {@code true} if there is a date clash, {@code false} otherwise
	 */
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
	/**
	 * Checks if the register deadline for the current camp has passed
	 * @return {@code true} if the register deadline has not passed, {@code false} otherwise
	 */
	public static boolean checkRegisterDeadline(){
		Camp camp = AuthData.getCurrentCamp();
		if(camp.getRegCloseDate().isBefore(LocalDate.now()))return true;
		return false;

	}
	/**
	 * Check is the current camp capacity is full
	 * @return {@code true} if the camp has no more slots,{@code false} otherwise
	 */
	public static boolean isCampFull(){
		Camp camp = AuthData.getCurrentCamp();
		if(camp.getRemainingSlot() == 0) return true;
		return false;
	}
	/**
	 * Updates the current camp's remaining slots after a student registers for the camp
	 */
	public static void updateRemainingSlot(){
		Camp camp = AuthData.getCurrentCamp();
		camp.setRemainingSlot(camp.getRemainingSlot()-1);
	}
	/**
	 * Checks if the student has withdrawn from the camp before
	 * @return {@code true} if the student has withdrawn from the camp, {@code false} otherwise
	 */
	public static boolean checkWithdrawBefore(){
		Student user = (Student)AuthData.getCurrentUser();
		ArrayList<String> withdraw = user.getWithdrawnCamp();
		Camp camp = AuthData.getCurrentCamp();
		
		for(int i = 0 ; i < withdraw.size() ; i++){
			if(withdraw.get(i) == camp.getCampName()) return true; 
		}
		return false;
	}
	/**
	 * The student user register the current camp as an attendee
	 */
	public static void registerAsAttendee(){
		Student user = (Student)AuthData.getCurrentUser();
		Camp camp = AuthData.getCurrentCamp();
		camp.addAttendees(user);
		ArrayList<String> registeredCamp = user.getRegisteredCamp();
		registeredCamp.add(camp.getCampName());
		camp.setRemainingSlot(camp.getRemainingSlot()-1);
		
	}
	/**
	 * The student user register the current camp as a camp committee member fo that camp
	 */
	public static void registerAsCommittee(String position){
		Student student = (Student)AuthData.getCurrentUser();
		Camp camp = AuthData.getCurrentCamp();
	
		student.setCampComMem(position);
		camp.addCommittee(student);
		student.getRegisteredCamp().add(camp.getCampName());
		student.setIsComittee(true);
		student.setCommitteeCamp(camp);
		camp.setCampCommitteeRemainingSlots(camp.getCampCommitteeRemainingSlots()-1);
	}
	/**
	 * View the available camps to join based on the visibility and faculty
	 * @return the number of available camps
	 */
	// view available camp
    public static int viewAvailableCamps(){
		int i=0;
		List<Camp> sortArr= HelperService.filter();

		HelperService.clearScreen();
		HelperService.printRoute("Student Menu ---> View Available Camp");
        for (Camp camp : sortArr){
			if((camp.getVisibility() == true && camp.getUserGroup().toString() == AuthData.getCurrentUser().getFaculty()) 
				|| camp.getUserGroup() == Faculty.NTU){
					HelperService.viewCamp(camp);
					i++;
				}
        }
		return i;
    }
	/**
	 * View the camps registered by the current Student user
	 * @ return the number of registered camps
	 */
	public static int viewRegisteredCamp(){
		Student user = (Student)AuthData.getCurrentUser();
		int i = 0;
		ArrayList<String> registeredCamp = user.getRegisteredCamp();
		List<Camp> sortArr= HelperService.sortCampListByName(registeredCamp);
		if(sortArr != null){
			HelperService.clearScreen();
			HelperService.printRoute("Student Menu ---> View Registered Camp");

			for (Camp camp1 : sortArr){
				HelperService.printRegisteredCamp(camp1, (Student)AuthData.getCurrentUser());
				i++;
			}
		}
		return i;
    }

	/**
	 * Student user withdraws from the current camp
	 * @param campName : Name of camp to be withdrawn
	 * @param student : Current student
	 * @param isComittee : Whether the student is a camp committee member
	 */
	public static void withdrawCamp(String campName, Student student, boolean isComittee){
		Camp camp = DatabaseService.getCamp(campName);
		// Student user = (Student)AuthData.getCurrentUser();
		ArrayList<String> registeredCamp = student.getRegisteredCamp();
		ArrayList<String> withdrawCamp = student.getWithdrawnCamp();
		
		if(isComittee){
			System.out.println("Camp Committee cannot quit camp!!!");
		}

		else if (!(registeredCamp.contains(campName))){
			System.out.println("You are not registered to this camp, cannot withdraw. Going back previous menu...");

		}
		
		else {
			registeredCamp.remove(campName);
			camp.getAttendees().remove(student);
			withdrawCamp.add(campName);
			camp.setRemainingSlot(camp.getRemainingSlot()+1);
			System.out.println("Withdrawn successfully!");
		}
		
	}

	
	
	
	
}