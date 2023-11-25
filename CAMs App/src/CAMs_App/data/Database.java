package CAMs_App.data;
import java.util.HashMap;
import java.util.Map;

import CAMs_App.entity.Camp;
import CAMs_App.entity.Staff;
import CAMs_App.entity.Student;
import CAMs_App.service.DatabaseService;

public class Database {

	private static Map<String, Student> studentsData = new HashMap<String, Student>();

	private static Map<String, Staff> staffData = new HashMap<String, Staff>();

	private static Map<String, Camp> campData = new HashMap<String, Camp>();


	public static void readData() {
		DatabaseService.readfromStaffCSV();
		DatabaseService.readfromStudentCSV();
		DatabaseService.readfromCampCSV();
	}

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
