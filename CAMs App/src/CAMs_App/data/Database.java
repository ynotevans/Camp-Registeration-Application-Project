package CAMs_App.data;
import java.util.HashMap;
import java.util.Map;

import CAMs_App.entity.Camp;
import CAMs_App.entity.CampCompMem;
import CAMs_App.entity.Enquiries;
import CAMs_App.entity.Staff;
import CAMs_App.entity.Student;
import CAMs_App.entity.Suggestions;

// import interfaces.IFileDataService;


public class Database {

	private static Map<String, Student> studentsData = new HashMap<String, Student>();

	
	private static Map<String, Staff> staffData = new HashMap<String, Staff>();

	
	private static Map<String, CampCompMem> campCompMemData = new HashMap<String, CampCompMem>();

	private static Map<Integer, Camp> campData = new HashMap<Integer, Camp>();

	private static Map<Integer, Suggestions> suggestionsData = new HashMap<Integer, Suggestions>();

    private static Map<Integer, Enquiries> enquiriesData = new HashMap<Integer, Enquiries>();

	
	private Database() {
	}

	/**
	 * Saves the data from the DataStore to the file system.
	 *
	 * @return {@code true} if the data is saved successfully, {@code false}
	 *         otherwise
	 */
	public static boolean saveData() {
		Database.setStudentsData(studentsData);
		Database.setstaffData(staffData);
		Database.setCampCompMemData(campCompMemData);
		Database.setCampData(campData);
		Database.setEnquiriesData(enquiriesData);
        Database.setEnquiriesData(enquiriesData);
        

		return true;
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
		// fileDataService.exportStudentData(filePathsMap.get("user"), filePathsMap.get("student"), studentsData);
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
		// fileDataService.exportSupervisorData(filePathsMap.get("user"), filePathsMap.get("supervisor"), supervisorsData);
	}

	// ---------- Camp Committee member ---------- //
	/**
	 * Gets the FYP coordinators data map.
	 *
	 * @return a {@link Map} containing CampCompMem ID as the key and
	 *         {@link CampCompMem} objects as the value
	 */
	public static Map<String, CampCompMem> getCampCompMemData() {
		return Database.campCompMemData;
	}

	/**
	 * Sets the CampCompMem data map and saves the data to the file system.
	 *
	 * @param fypcoordinatorsData a {@link Map} containing CampCompMem ID as the
	 *                            key and {@link CampCompMem} objects as the
	 *                            value
	 */
	public static void setCampCompMemData(Map<String, CampCompMem> campCompMemData) {
		Database.campCompMemData = campCompMemData;
		// fileDataService.exportFYPCoordinatorData(filePathsMap.get("user"), filePathsMap.get("supervisor"),
		// 		filePathsMap.get("fypcoordinator"), fypcoordinatorsData);
	}

	// ---------- Camp ---------- //
	/**
	 * Gets the Camp data map.
	 *
	 * @return a {@link Map} containing Camp ID as the key and {@link Camp}
	 *         objects as the value
	 */
	public static Map<Integer, Camp> getCampData() {
		return Database.campData;
	}

	/**
	 * Sets the Camp data map and saves the data to the file system.
	 *
	 * @param projectsData a {@link Map} containing project ID as the key and
	 *                     {@link Camp} objects as the value
	 */
	public static void setCampData(Map<Integer, Camp> campData) {
		Database.campData = campData;
		// fileDataService.exportProjectData(filePathsMap.get("project"), projectsData);
	}

	// ---------- Suggestion ---------- //
	/**
	 * Gets the Suggestion data map.
	 *
	 * @return a {@link Map} containing Suggestion ID as the key and {@link Request}
	 *         objects as the value
	 */
	public static Map<Integer, Suggestions> getSuggestionsData() {
		return Database.suggestionsData;
	}

	/**
	 * Sets the Suggestion data map and saves the data to the file system.
	 *
	 * @param requestData a {@link Map} containing Suggestion ID as the key and
	 *                    {@link Request} objects as the value
	 */
	public static void setSuggestionsData(Map<Integer, Suggestions> suggestionsData) {
		Database.suggestionsData = suggestionsData;
		// fileDataService.exportRequestData(filePathsMap.get("request"), filePathsMap.get("transferStudentRequest"),
		// 		filePathsMap.get("changeProjectTitleRequest"), requestData);
    }

    // ---------- Enqueiries ---------- //
	/**
	 * Gets the Enqueiries data map.
	 *
	 * @return a {@link Map} containing Enqueiries ID as the key and {@link Enqueiries}
	 *         objects as the value
	 */
	public static Map<Integer, Enquiries> getEnquiriesData() {
		return Database.enquiriesData;
	}

	/**
	 * Sets the Enqueiries data map and saves the data to the file system.
	 *
	 * @param requestData a {@link Map} containing Enqueiries ID as the key and
	 *                    {@link Enqueiries} objects as the value
	 */
	public static void setEnquiriesData(Map<Integer, Enquiries> EnquiriesData) {
		Database.enquiriesData = EnquiriesData;
		// fileDataService.exportRequestData(filePathsMap.get("request"), filePathsMap.get("transferStudentRequest"),
		// 		filePathsMap.get("changeProjectTitleRequest"), requestData);
	}

/*
    // private static ArrayList<User> database = new ArrayList<>();
    // private static ArrayList<Camp> campDataBase = new ArrayList<>();

    // public ArrayList<Camp> getCampDataBase() {
    //     return campDataBase;
    // }

    // public ArrayList<User> getDatabase() {
    //     return database;
    // }

    // public static void setCampDataBase(ArrayList<Camp> campDataBase) {
    //     Database.campDataBase = campDataBase;
    // }

    // public static void setDatabase(ArrayList<User> database) {
    //     Database.database = database;
    // }


    // // to incorporate service 
    // public static int findUserPos(User user, ArrayList<User> database){
    //     int j;
    //     for (j=0;j<database.size();j++) {
    //         if (database.get(j).getUserID() == user.getUserID())
    //             return j;
    //     }
    //     return -1;
    // }

    // public static void addUserToDatabase(User user, ArrayList<User> database){
    //     int j = findUserPos(user, database);
    //     if (j!=-1) {
    //         database.set(j,user);
    //         System.out.println(user.getUserID() + " successfully added.");
    //         return;
    //     }

    //     database.add(user);
    //     System.out.println(user.getUserID() + " successfully added.");
    //     return;
    // }

    // // Camp

    // public static int findCampPos(Camp camp, ArrayList<Camp> campDataBase){
    //     int j;
    //     for (j=0;j<campDataBase.size();j++) {
    //         if (campDataBase.get(j).getCampName() == camp.getCampName())
    //             return j;
    //     }
    //     return -1;
    // }

    // public static boolean findCamp(Camp camp, ArrayList<Camp> campDataBase){
    //     int j;
    //     for (j=0;j<campDataBase.size();j++) {
    //         if (campDataBase.get(j).getCampName() == camp.getCampName())
    //             return true;
    //     }
    //     return false;
    // }    

    // public static void addCampToDatabase(Camp camp, ArrayList<Camp> campDataBase){
    //     int j = findCampPos(camp, campDataBase);
    //     if (j!=-1) {
    //         campDataBase.set(j,camp);
    //         System.out.println(camp.getCampName() + " successfully added.");
    //         return;
    //     }

    //     campDataBase.add(camp);
    //     System.out.println(camp.getCampName() + " successfully added.");
    //     return;
    // }

    // public static boolean removeCampFromDatabase(Camp camp, ArrayList<Camp> campDataBase){
    //     int j = findCampPos(camp, campDataBase);
    //     if (j!=-1) {
    //         campDataBase.remove(j);
    //         System.out.println("Successfully removed.");
    //         return true;
    //     }
    //     return false;
    // }
*/
}
