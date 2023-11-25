package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;
import CAMs_App.service.DatabaseService;

/**
 * The {@link Student} class represents a student user in the system.
 * It extends {@link User} class and includes additional information 
 * such as registered camps and points earned. Each student object has a corresponding
 * {@link CampComMem} object.
 */
public class Student extends User {
	/**
	 * Whether this student is in a committee or not.
	 */
	private boolean isCommittee = false;
	/**
	 * The list of camp names this student has registered for.
	 */
	private ArrayList<String> registeredCamp;
	/**
	 * The list of camp names this student has withdrawn from.
	 */
	private ArrayList<String> withdrawnCamp;
	/**
	 * The {@link Camp} this student is committee for.
	 */
	private Camp committeeCamp = null;
	/**
	 * The name of the camp this student is committee for.
	 */
	private String committeeCampName;
	/**
	 * The number of points this student earned.
	 */
	private int points = 0;
	/**
	 * The corresponding {@link CampComMem} object to this student.
	 */
	private CampComMem campComMem = new CampComMem();
	
	/**
	 * Constructs a {@link Student} object with the given user info.
	 * 
	 * @param userID	The ID of the student.
	 * @param password	The password of the student.
	 * @param faculty	The {@link Faculty} of the student.
	 * @param name		The name of the student.
	 */
	public Student(String userID, String password, Faculty faculty, String name) {
		super(userID, password, faculty, name);
		isCommittee = false; 
		committeeCamp = null;
		points = 0;
	}
	
	/**
	 * Gets the committee status of the student.
	 * 
	 * @return Whether this student is in a committee or not.
	 */
	public boolean getIsComittee () {
		return isCommittee;
	}
	
	/**
	 * Sets the whether this student is in a committee or not.
	 * 
	 * @param isCommittee The status to be set.
	 */
	public void setIsComittee(boolean isCommittee) {
		this.isCommittee = isCommittee;
	}
	
	/**
	 * Gets the points this student has earned.
	 * 
	 * @return The number of points.
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Sets the points this student has earned.
	 * 
	 * @param points The number of points to be set.
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Gets the list of camps this student registered for.
	 * 
	 * @return A list of the registered camps names.
	 */
	public ArrayList<String> getRegisteredCamp() {
		return registeredCamp;
	}
	
	/**
	 * Sets the list of camps this student registered for.
	 * 
	 * @param registeredCamp The list of registered camps names.
	 */
	public void setRegisteredCamp(ArrayList<String> registeredCamp) {
		this.registeredCamp = registeredCamp;
	}
	
	/**
	 * Gets the list of camps this student has withdrawn from.
	 * 
	 * @return A list of withdrawn camp names.
	 */
	public ArrayList<String> getWithdrawnCamp() {
		return withdrawnCamp;
	}
	
	/**
	 * Sets the list of camps this student has withdrawn from.
	 * 
	 * @param withdrawnCamp The list of withdrawn camp names.
	 */
	public void setWithdrawnCamp(ArrayList<String> withdrawnCamp) {
		this.withdrawnCamp = withdrawnCamp;
	}
	
	/**
	 * Gets the {@link Camp} object this student is committee for.
	 * 
	 * @return The committee {@link Camp}.
	 */
	public Camp getComitteeCamp() {
		return committeeCamp;
	}
	
	/**
	 * Sets the {@link Camp} object this student is committee for.
	 * 
	 * @param camp The {@link Camp} object.
	 */
	public void setCommitteeCamp(Camp camp) {
		this.committeeCamp = camp;
	}

	/**
	 * Sets the {@link Camp} object this student is committee for.
	 * 
	 * @param campName The name of the camp.
	 */
	public void setCommitteeCamp(String campName) {
		this.committeeCamp = DatabaseService.getCamp(campName);
	}
	
	/**
	 * Sets the position the student holds as a camp committee.
	 * 
	 * @param position The name of the position.
	 */
	public void setCampComMem(String position){
		this.campComMem.setPosition(position);
	}
	
	/**
	 * Gets the corresponding {@link CampComMem} object to this student.
	 * 
	 * @return The {@link CampComMem} object.
	 */
	public CampComMem getCampComMem(){
		return this.campComMem;
	}

	/**
	 * Gets the name of the camp this student is committee for.
	 * 
	 * @return The name of the comitteeCamp
	 */
	public String getCommitteeCampName(){
		return this.committeeCampName;
	}

	/**
	 * Sets the name of the camp this student is committee for.
	 * 
	 * @param committeeCampName The name of the camp to be set.
	 */
	public void setCommitteeCampName(String committeeCampName){
		this.committeeCampName = committeeCampName;
	}

}
