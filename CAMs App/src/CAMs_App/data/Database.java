package CAMs_App.data;
import java.util.HashMap;
import java.util.Map;

import CAMs_App.entity.Camp;
import CAMs_App.entity.Staff;
import CAMs_App.entity.Student;
import CAMs_App.service.DatabaseService;

/**
 * The {@link Database} class manages the data storage within the application. 
 * It offers methods to get and set the data maps for different data types,
 * as well as calling methods from {@link DatabaseService} for import and export data 
 * to and from the file system.
 * 
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public class Database {
	/**
	 * {@link Map} that stores all instances of {@link Student} users.
	 */
	private static Map<String, Student> studentsData = new HashMap<String, Student>();
	/**
	 * {@link Map} that store all instances of {@link Staff} users.
	 */
	private static Map<String, Staff> staffData = new HashMap<String, Staff>();
	/**
	 * {@link Map} that store all instances of {@link Camp} objects.
	 */
	private static Map<String, Camp> campData = new HashMap<String, Camp>();

	/**
	 * Calls the methods from {@link DatabaseService} for reading from files.
	 */
	public static void readData() {
		DatabaseService.readfromStaffCSV();
		DatabaseService.readfromStudentCSV();
		DatabaseService.readfromCampCSV();
	}
	
	/**
	 * Calls the methods from {@link DatabaseService} for writing to files.
	 */
	public static void writeData() {
		DatabaseService.writetoStaffCSV(Database.getStaffData());
		DatabaseService.writetoStudentCSV(Database.getStudentsData());
		DatabaseService.writetoCampCSV(Database.getCampData());
	}

	// ---------- Student ---------- //
	/**
	 * Gets the students data map.
	 *
	 * @return a {@link Map} containing student ID as the key and {@link Student}
	 *         objects as the value
	 */
	public static Map<String, Student> getStudentsData() {
		return Database.studentsData;
	}

	/**
	 * Sets the students data map and saves the data to the file system.
	 *
	 * @param studentsData a {@link Map} containing student ID as the key and
	 *                     {@link Student} objects as the value
	 */
	public static void setStudentsData(Map<String, Student> studentsData) {
		Database.studentsData = studentsData;
	}

	// ---------- Staff ---------- //
	/**
	 * Gets the Staff data map.
	 *
	 * @return a {@link Map} containing supervisor ID as the key and
	 *         {@link Supervisor} objects as the value
	 */
	public static Map<String, Staff> getStaffData() {
		return Database.staffData;
	}

	/**
	 * Sets the staff data map and saves the data to the file system.
	 *
	 * @param staffData a {@link Map} containing supervisor ID as the key and
	 *                        {@link Staff} objects as the value
	 */
	public static void setstaffData(Map<String, Staff> staffData) {
		Database.staffData = staffData;
	}

	// ---------- Camp ---------- //
	/**
	 * Gets the Camp data map.
	 *
	 * @return a {@link Map} containing Camp ID as the key and {@link Camp}
	 *         objects as the value
	 */
	public static Map<String, Camp> getCampData() {
		return Database.campData;
	}

	/**
	 * Sets the Camp data map and saves the data to the file system.
	 *
	 * @param projectsData a {@link Map} containing project ID as the key and
	 *                     {@link Camp} objects as the value
	 */
	public static void setCampData(Map<String, Camp> campData) {
		Database.campData = campData;
	}

}
