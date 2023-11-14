package CAMs_App.service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.crypto.Data;

import CAMs_App.entity.Student;
import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.CampCompMem;
import CAMs_App.entity.Staff;
import CAMs_App.enums.Faculty;




public class DatabaseService implements IFileDataService {
    private static List<String> userCsvHeaders = new ArrayList<String>();

	/**
	 * The list of headers for the CSV file that stores student data.
	 */
	private static List<String> studentCsvHeaders = new ArrayList<String>();

	/**
	 * The list of headers for the CSV file that stores supervisor data.
	 */
	private static List<String> staffCsvHeaders = new ArrayList<String>();

	/**
	 * The list of headers for the CSV file that stores FYP coordinator data.
	 */
	//private static List<String> campCompMemCsvHeaders = new ArrayList<String>();

	/**
	 * The list of headers for the CSV file that stores project data.
	 */
	private static List<String> campCsvHeaders = new ArrayList<String>();

	/**
	 * The list of headers for the CSV file that stores request data.
	 */
	// private static List<String> suggestionsCsvHeaders = new ArrayList<String>();

	/**
	 * The list of headers for the CSV file that stores transfer student request
	 * data.
	 */
	// private static List<String> enquiriesCsvHeaders = new ArrayList<String>();


    public DatabaseService() {
    };

	// ---------- Helper Function ---------- //
	/**
	 * Reads data from the CSV file located at the given file path and returns it as
	 * a list of string arrays.
	 *
	 * @param filePath the file path of the CSV file to read
	 * @param headers  the list of headers for the CSV file
	 * @return a list of string arrays containing the CSV data
	 */
	public List<String[]> readCsvFile(String filePath, List<String> headers) {
		List<String[]> dataList = new ArrayList<String[]>();
		headers.clear();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			// Headers
			String[] headerRow = br.readLine().split(",");
			for (String header : headerRow) {
				headers.add(header);
			}

			// Content
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				dataList.add(values);
			}

		} catch (IOException e) {
			System.out.println("Cannot import data!");
		}

		return dataList;
	}

	/**
	 * Writes the given data to a CSV file located at the given file path.
	 *
	 * @param filePath the file path of the CSV file to write
	 * @param headers  the list of headers for the CSV file
	 * @param lines    the list of lines to write to the CSV file
	 * @return true if the data is written successfully, false otherwise
	 */
	public boolean writeCsvFile(String filePath, List<String> headers, List<String> lines) {
		try (FileWriter writer = new FileWriter(filePath)) {
			// Write Headers
			String headerLine = String.join(",", headers);
			writer.write(headerLine + "\n");

			// Write Content
			for (String line : lines) {
				writer.write(line + "\n");
			}
		} catch (IOException e) {
			System.out.println("Cannot export data!");
			return false;
		}
		return true;
	}

	/**
	 * Parses a string array containing user data and returns a map of user
	 * information.
	 *
	 * @param userRow the string array containing the user data
	 * @return a map of user information, where the keys are "userID", "password",
	 *         "email", "role", and "name" and the values are the corresponding
	 *         values in the userRow array
	 */
	private Map<String, String> parseUserRow(String[] userRow) {
		String userID = userRow[0];
		String password = userRow[1];
        Faculty faculty = Faculty.valueOf(userRow[2]);
        String facString = faculty.toString();

        // ProjectStatus status = ProjectStatus.valueOf(projectRow[2]);

		// to handle names with comma
		for (int i = 5; i < userRow.length; i++) {
			if (i != 4)
				userID += ",";
			userID += userRow[i];
		}

		// Return
		Map<String, String> userInfoMap = new HashMap<String, String>();
		userInfoMap.put("userID", userID);
		userInfoMap.put("password", password);
		userInfoMap.put("faculty", facString);
		
		return userInfoMap;
	}

	// ---------- Interface method implementation ---------- //
	// Student
    // will add interface
	@Override
	public Map<String, Student> importStudentData(String usersFilePath, String studentsFilePath) {
		Map<String, Student> studentsMap = new HashMap<String, Student>();

		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		List<String[]> studentsRows = this.readCsvFile(studentsFilePath, studentCsvHeaders);

		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);

			String identity = userInfoMap.get("identity");
			if (!identity.equals("student"))
				continue;

			String userID = userInfoMap.get("userID");
			String password = userInfoMap.get("password");
            String facString = userInfoMap.get("faculty");
            Faculty faculty = Faculty.valueOf(facString);
			
			// get the associated student data

			for (String[] studentRow : studentsRows) {
				if (!studentRow[0].equals(userID))
					continue;
			}

			Student student = new Student(userID, password, faculty);

			studentsMap.put(userID, student);
        }
		return studentsMap;
	}

	@Override
	public boolean exportStudentData(String usersFilePath, String studentsFilePath, Map<String, Student> studentMap) {
		List<String> studentLines = new ArrayList<String>();
		List<String> userLines = new ArrayList<String>();

		// User
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);
			String userLine = String.format("%s,%s,%s",
					userInfoMap.get("userID"),
					userInfoMap.get("password"),
					userInfoMap.get("faculty"));

			if (userInfoMap.get("identity").equals("student")) {
				Student student = studentMap.get(userInfoMap.get("userID"));

				userLine = String.format("%s,%s,%s",
						student.getUserID(),
						student.getPassword(),
						student.getFaculty());
			}

			userLines.add(userLine);
		}

		// Student
		for (Student student : studentMap.values()) {
			String studentLine = String.format("%s",
					student.getUserID());

			studentLines.add(studentLine);
		}

		// Write to CSV
		boolean success1 = this.writeCsvFile(usersFilePath, userCsvHeaders, userLines);
		boolean success2 = this.writeCsvFile(studentsFilePath, studentCsvHeaders, studentLines);
		return success1 && success2;
	}

	// Supervisor
	@Override
	public Map<String, Staff> importStaffData(String usersFilePath, String staffFilePath) {
		Map<String, Staff> staffMap = new HashMap<String, Staff>();

		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		List<String[]> supervisorsRows = this.readCsvFile(staffFilePath, staffCsvHeaders);

		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);

			String role = userInfoMap.get("identity");
			if (!role.equals("staff"))
				continue;

			String userID = userInfoMap.get("userID");
			String password = userInfoMap.get("password");
            String facString = userInfoMap.get("faculty");
            Faculty faculty = Faculty.valueOf(facString);

			// get the associated supervisor data
			for (String[] supervisorRow : supervisorsRows) {
				if (!supervisorRow[0].equals(userID))
					continue;
			}

			Staff staff = new Staff(userID, password, faculty);

			staffMap.put(userID, staff);
		}

		return staffMap;
	}

	@Override
	public boolean exportStaffData(String usersFilePath, String staffFilePath,
			Map<String, Staff> staffMap) {
		List<String> staffLines = new ArrayList<String>();
		List<String> userLines = new ArrayList<String>();

		// User
		List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
		for (String[] userRow : usersRows) {
			Map<String, String> userInfoMap = parseUserRow(userRow);
			String userLine = String.format("%s,%s,%s",
					userInfoMap.get("userID"),
					userInfoMap.get("password"),
					userInfoMap.get("faculty"));

			if (userInfoMap.get("identity").equals("staff")) {
				Staff staff = staffMap.get(userInfoMap.get("userID"));

				userLine = String.format("%s,%s,%s,%s,%s",
						staff.getUserID(),
						staff.getPassword(),
						staff.getFaculty());
			}

			userLines.add(userLine);
		}

		// Supervisor
		for (Staff staff : staffMap.values()) {
			String staffLine = String.format("%s",
					staff.getUserID());

			staffLines.add(staffLine);
		}

		// Write to CSV
		boolean success1 = this.writeCsvFile(usersFilePath, userCsvHeaders, userLines);
		boolean success2 = this.writeCsvFile(staffFilePath, staffCsvHeaders, staffLines);
		return success1 && success2;
	}

	// Projects
	@Override
	public Map<String, Camp> importCampData(String campFilePath, String usersFilePath,
			String studentsFilePath, String staffFilePath, String campCompMemFilePath) {
		Map<String, Camp> campMap = new HashMap<String, Camp>();

		List<String[]> campRows = this.readCsvFile(campFilePath, campCsvHeaders);
        
        // Faculty faculty = Faculty.valueOf(facString);
        // Camp(String campName , LocalDate campDate , LocalDate regCloseDate , Faculty userGroup ,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int numberOfCampDays, int remainingSlot)

		for (String[] campRow : campRows) {
			String campName = campRow[0];
            // LocalDate campDate = LocalDate.parse(campRow[1]);
            // LocalDate regCloseDate = LocalDate.parse(campRow[2]);   
            // Faculty userGroup = Faculty.valueOf(campRow[3]);
            // String location = campRow[4];
            // int totalSlots = Integer.parseInt(campRow[5]);
            // int campCommitteeSlots = Integer.parseInt(campRow[6]);
            // String description= campRow[7];
            // String staffInCharge = campRow[8];
            // Boolean visibility = Boolean.parseBoolean(campRow[9]);
            // int numberOfCampDays = Integer.parseInt(campRow[10]);
            // int remainingSlot = Integer.parseInt(campRow[11]);
            
			Camp camp = new Camp();
            
			campMap.put(campName, camp);
        }
		

		return campMap;
	}

	@Override
	public boolean exportCampData(String campFilePath, Map<String, Camp> campMap) {
		List<String> campLines = new ArrayList<String>();

		// Project
		for (Camp camp : campMap.values()) {
			String campLine = String.format("%s,%s,%s,%s,%s,%d,%d,%s,%s,%b,%d,%d",
					camp.getCampName(),
					camp.getCampDate(),
					camp.getRegCloseDate(),
					camp.getUserGroup(),
					camp.getLocation(),
                    camp.getTotalSlots(),
                    camp.getCampCommitteeSlots(),
                    camp.getDescription(),
                    camp.getStaffInCharge(),
                    camp.getVisibility(),
                    camp.getNumberOfCampDays(),
                    camp.getRemainingSlot());


			campLines.add(campLine);
		}

		// Write to CSV
		return this.writeCsvFile(campFilePath, campCsvHeaders, campLines);
	}

	public static Camp getCamp(String campName){
		Map<String ,Camp> campData = Database.getCampData();
		return campData.get(campName);
	}

	// // Requests
	// @Override
	// public Map<Integer, Request> importRequestData(String requestsFilePath, String transferStudentRequestsFilePath,
	// 		String changeProjectTitleRequestsFilePath) {
	// 	Map<Integer, Request> requestsMap = new HashMap<Integer, Request>();
	// 	List<String[]> requestsRows = this.readCsvFile(requestsFilePath, requestCsvHeaders);
	// 	List<String[]> transferStudentRequestsRows = this.readCsvFile(transferStudentRequestsFilePath,
	// 			transferStudentRequestCsvHeaders);
	// 	List<String[]> changeProjectTitleRequestsRows = this.readCsvFile(changeProjectTitleRequestsFilePath,
	// 			changeProjectTitleRequestCsvHeaders);

	// 	for (String[] requestRow : requestsRows) {
	// 		int requestID = Integer.parseInt(requestRow[0]);
	// 		int projectID = Integer.parseInt(requestRow[1]);
	// 		String senderID = requestRow[2];
	// 		String receiverID = requestRow[3];
	// 		RequestStatus status = RequestStatus.valueOf(requestRow[4]);
	// 		ArrayList<String> history = new ArrayList<String>(Arrays.asList(requestRow[5].split(";")));
	// 		RequestType type = RequestType.valueOf(requestRow[6]);

	// 		// Handle different Requests subclasses
	// 		if (type == RequestType.TRANSFER_STUDENT) { // TransferStudentRequest
	// 			for (String[] transferStudentRequestRow : transferStudentRequestsRows) {
	// 				if (requestID != Integer.parseInt(transferStudentRequestRow[0]))
	// 					continue;

	// 				String replacementSupervisorID = transferStudentRequestRow[1];
	// 				Request transferStudentRequest = new TransferStudentRequest(senderID, receiverID, projectID,
	// 						requestID, status, history, replacementSupervisorID);

	// 				requestsMap.put(requestID, transferStudentRequest);
	// 				break;
	// 			}
	// 		} else if (type == RequestType.CHANGE_PROJECT_TITLE) { // ChangeProjectTitleRequest
	// 			for (String[] changeProjectTitleRequestRow : changeProjectTitleRequestsRows) {
	// 				if (requestID != Integer.parseInt(changeProjectTitleRequestRow[0]))
	// 					continue;

	// 				String newTitle = changeProjectTitleRequestRow[1];
	// 				Request changeProjectTitleRequest = new ChangeProjectTitleRequest(senderID, receiverID, projectID,
	// 						requestID, status, history, newTitle);

	// 				requestsMap.put(requestID, changeProjectTitleRequest);
	// 				break;
	// 			}
	// 		} else if (type == RequestType.ALLOCATE_PROJECT) { // AllocateProjectRequest
	// 			Request allocateProjectRequest = new AllocateProjectRequest(senderID, receiverID, projectID, requestID,
	// 					status, history);
	// 			requestsMap.put(requestID, allocateProjectRequest);
	// 		} else if (type == RequestType.DEREGISTER_PROJECT) { // DeregisterProjectRequest
	// 			Request deregisterProjectRequest = new DeregisterProjectRequest(senderID, receiverID, projectID,
	// 					requestID, status, history);
	// 			requestsMap.put(requestID, deregisterProjectRequest);
	// 		}
	// 	}

	// 	return requestsMap;
	// }

	// @Override
	// public boolean exportRequestData(String requestsFilePath, String transferStudentRequestsFilePath,
	// 		String changeProjectTitleRequestsFilePath, Map<Integer, Request> requestMap) {
	// 	List<String> requestLines = new ArrayList<String>();
	// 	List<String> transferStudentRequestLines = new ArrayList<String>();
	// 	List<String> changeProjectTitleLines = new ArrayList<String>();

	// 	// Request
	// 	for (Request request : requestMap.values()) {
	// 		// To handle different Requests subclasses
	// 		if (request.getType() == RequestType.TRANSFER_STUDENT) {
	// 			TransferStudentRequest transferStudentRequest = (TransferStudentRequest) request;
	// 			String transferStudentRequestLine = String.format("%d,%s",
	// 					transferStudentRequest.getRequestID(),
	// 					transferStudentRequest.getReplacementSupervisorID());

	// 			transferStudentRequestLines.add(transferStudentRequestLine);
	// 		} else if (request.getType() == RequestType.CHANGE_PROJECT_TITLE) {
	// 			ChangeProjectTitleRequest changeProjectTitleRequest = (ChangeProjectTitleRequest) request;
	// 			String changeProjectTitleRequestLine = String.format("%d,%s",
	// 					changeProjectTitleRequest.getRequestID(),
	// 					changeProjectTitleRequest.getNewTitle());

	// 			changeProjectTitleLines.add(changeProjectTitleRequestLine);
	// 		}

	// 		// Request base class
	// 		String requestLine = String.format("%d,%d,%s,%s,%s,%s,%s",
	// 				request.getRequestID(),
	// 				request.getProject().getProjectID(),
	// 				request.getSender().getUserID(),
	// 				request.getReceiver().getUserID(),
	// 				request.getStatus(),
	// 				String.join(";", request.getHistory()),
	// 				request.getType());

	// 		requestLines.add(requestLine);
	// 	}

	// 	// Write to CSV
	// 	boolean success1 = this.writeCsvFile(requestsFilePath, requestCsvHeaders, requestLines);
	// 	boolean success2 = this.writeCsvFile(transferStudentRequestsFilePath, transferStudentRequestCsvHeaders,
	// 			transferStudentRequestLines);
	// 	boolean success3 = this.writeCsvFile(changeProjectTitleRequestsFilePath, changeProjectTitleRequestCsvHeaders,
	// 			changeProjectTitleLines);
	// 	return success1 && success2 && success3;
	// test}


	// // Camp Committee, dont need this, remove during final check
	// @Override
	// public Map<String, CampCompMem> importCampCompMemData(String usersFilePath, String studentFilePath,
	// 		String campCompMemFilePath) {
	// 	Map<String, CampCompMem> campCompMemMap = new HashMap<String, CampCompMem>();

	// 	List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
	// 	List<String[]> campCompMemRows = this.readCsvFile(campCompMemFilePath, campCompMemCsvHeaders);

	// 	for (String[] userRow : usersRows) {
	// 		Map<String, String> userInfoMap = parseUserRow(userRow);

	// 		String role = userInfoMap.get("identity");
	// 		if (!role.equals("student"))
	// 			continue;

				
	// 		String userID = userInfoMap.get("userID");
	// 		String password = userInfoMap.get("password");
    //         String facString = userInfoMap.get("faculty");
    //         String position = userInfoMap.get("position");
    //         Faculty faculty = Faculty.valueOf(facString);
			
			
			
			

		
	// 		for (String[] campCompMemRow : campCompMemRows) {
	// 			if (!campCompMemRow[0].equals(userID))
	// 				continue;

	// 		}

	// 		CampCompMem campCompMem = new CampCompMem(student, position); // need fix

	// 		campCompMemMap.put(userID, campCompMem);
	// 	}

	// 	return campCompMemMap;
	// }

	// @Override
	// public boolean exportCampCompMemData(String usersFilePath, String studentFilePath,
	// 		String campCompMemFilePath, Map<String, CampCompMem> campCompMemMap) {
	// 	List<String> campCompMemLines = new ArrayList<String>();
	// 	List<String> userLines = new ArrayList<String>();

	// 	// User
	// 	List<String[]> usersRows = this.readCsvFile(usersFilePath, userCsvHeaders);
	// 	for (String[] userRow : usersRows) {
	// 		Map<String, String> userInfoMap = parseUserRow(userRow);
	// 		String userLine = String.format("%s,%s,%s,%s,%s",
	// 				userInfoMap.get("userID"),
	// 				userInfoMap.get("password"),
	// 				userInfoMap.get("email"),
	// 				userInfoMap.get("role"),
	// 				userInfoMap.get("name"));

	// 		if (userInfoMap.get("Identity").equals("student")) {
	// 			CampCompMem campCompMem = campCompMemMap.get(userInfoMap.get("userID"));

	// 			userLine = String.format("%s,%s,%s,%s,%s",
	// 					campCompMem.getUserID(),
	// 					campCompMem.getPassword());
						
	// 		}

	// 		userLines.add(userLine);
	// 	}

	// 	// FYP Coordinator
	// 	for (CampCompMem campCompMem : campCompMemMap.values()) {
	// 		String campCompMemLine = String.format("%s", campCompMem.getUserID());

	// 		campCompMemLines.add(campCompMemLine);
	// 	}

	// 	// Write to CSV
	// 	boolean success1 = this.writeCsvFile(usersFilePath, userCsvHeaders, userLines);
	// 	boolean success2 = this.writeCsvFile(campCompMemFilePath, campCompMemCsvHeaders, campCompMemLines);
	// 	return success1 && success2;
	// }

}
