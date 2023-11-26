package CAMs_App.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import CAMs_App.entity.Student;
import CAMs_App.entity.Suggestions;
import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Enquiries;
import CAMs_App.entity.Staff;
import CAMs_App.enums.Faculty;



/**
 * The {@link DatabaseService} class provides methods to reading and writing of data into CSV file for student,staff and camp entities
 * 
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public class DatabaseService {
    /**
     * Writes student data to CSV file
	 * @param dataMap : Map containing the student data
	 * 
     */
	public static void writetoStudentCSV(Map<String, Student> dataMap){
		// Specify the file path for the CSV file
        String csvFilePath = "CAMs App/csvdata/student.csv";
		Set<String> header = new LinkedHashSet<String>();
		header.add("userID");
		header.add("password");
		header.add("Faculty");
		header.add("Name");
		header.add("isCommittee");
		header.add("points");
		header.add("committeeCamp");
		header.add("registeredCamp");  //ArrayList
		header.add("withdrawnCamp");   //ArrayList
		header.add("position");        //committee position

        // Write data to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write the header (keys of the Map) to the CSV file
            writer.write(String.join(",", header));
            writer.newLine();
			String registeredCamp,withdrawnCamp;

			for (Student student : dataMap.values()){
				if(student.getRegisteredCamp() == null|| student.getRegisteredCamp().size()==0)
					registeredCamp = "null";
				else
					registeredCamp = String.join("|", student.getRegisteredCamp());
				
				if(student.getWithdrawnCamp() == null || student.getWithdrawnCamp().size()==0)
					withdrawnCamp = "null";
				else
					withdrawnCamp = String.join("|", student.getWithdrawnCamp());

				// Write the values to the CSV file
            	writer.write(String.format("%s,%s,%s,%s,%b,%d,%s,%s,%s,%s" , student.getUserID(),student.getPassword(), 
											student.getFaculty(),student.getName(),student.getIsComittee(),student.getPoints(),
											student.getComitteeCamp()==null ? "null": student.getComitteeCamp().getCampName(),
											registeredCamp.isEmpty() ? "null" :registeredCamp,
											withdrawnCamp.isEmpty() ? "null" :withdrawnCamp,
											student.getCampComMem().getPosition()));
				writer.newLine();
			}

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
     * Reads data from a CSV file and import to the database by creating new student instances.
     */
	public static void readfromStudentCSV(){
		String csvFilePath = "CAMs App/csvdata/student.csv";
		Map<String, Student> user1 = Database.getStudentsData();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            
			String line;
			reader.readLine();  //skip the header
			String[] values;
            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of values using a comma as the delimiter
				values = line.split(",");
				int i=0;
				Student student = new Student(values[i], values[++i], Faculty.valueOf(values[++i]), values[++i]);
				student.setIsComittee(Boolean.parseBoolean(values[++i]));
				student.setPoints(Integer.parseInt(values[++i]));
				student.setCommitteeCampName(values[++i]);
			
				ArrayList<String> registerCamp = new ArrayList<String>();
				ArrayList<String> withdrawCamp = new ArrayList<String>();
				
				//insert the data into ArrayList
				String[] registeredCampArray = values[++i].split("\\|");
				if (!registeredCampArray[0].equals("null")){
					registerCamp.addAll(Arrays.asList(registeredCampArray));
					student.setRegisteredCamp(registerCamp);
					//student.getRegisteredCamp().addAll(Arrays.asList(registeredCampArray));
				}
				else{
					student.setRegisteredCamp(registerCamp);
				}
				

                	
                String[] withdrawnCampArray = values[++i].split("\\|");
				if (!withdrawnCampArray[0].equals("null")){
					withdrawCamp.addAll(Arrays.asList(withdrawnCampArray));
					student.getWithdrawnCamp().addAll(Arrays.asList());
				}
				else{
					student.setWithdrawnCamp(withdrawCamp);
				}

				student.getCampComMem().setPosition(values[++i]);
                	
				user1.put(values[0], student);
                System.out.println(); // Move to the next line for the next row
            }

			Database.setStudentsData(user1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
     * Writes staff data to CSV file
	 * @param dataMap : Map containing staff data
     */
	public static void writetoStaffCSV(Map<String, Staff> dataMap){
		// Specify the file path for the CSV file
        String csvFilePath = "CAMs App/csvdata/staff.csv";
		Set<String> header = new LinkedHashSet<String>();
		header.add("userID");
		header.add("password");
		header.add("Faculty");
		header.add("Name");
		

        // Write data to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write the header (keys of the Map) to the CSV file
            writer.write(String.join(",", header));
            writer.newLine();

			for (Staff staff : dataMap.values()){
				// Write the values to the CSV file
            	writer.write(String.format("%s,%s,%s,%s" , staff.getUserID(),staff.getPassword(), 
											staff.getFaculty(),staff.getName()));
				writer.newLine();
			}

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
     * Reads staff data from a CSV file and import to the database by creating new staff instances
     */
	public static void readfromStaffCSV(){
		String csvFilePath = "CAMs App/csvdata/staff.csv";
		Map<String, Staff> user = Database.getStaffData();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            
			String line;
			reader.readLine();  //skip the header

            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of values using a comma as the delimiter
				String[] values = line.split(",");
				int i=0;
				Staff staff = new Staff(values[i], values[++i], Faculty.valueOf(values[++i]),values[++i]);
				
				user.put(values[0], staff);
                System.out.println(); // Move to the next line for the next row
            }

			Database.setstaffData(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
     * Writes camp data to CSV file
	 * @param dataMap : Map containing camp data
     */
	public static void writetoCampCSV(Map<String, Camp> dataMap){
		// Specify the file path for the CSV file
        String csvFilePath = "CAMs App/csvdata/camp.csv";
		Set<String> header = new LinkedHashSet<String>();
		header.add("campName");
		header.add("campDate");
		header.add("campEndDate");
		header.add("regCloseDate");
		header.add("userGroup");
		header.add("location");
		header.add("totalSlots");
		header.add("remainingSlot");
		header.add("campCommitteeSlots");
		header.add("campCommitteeRemainingSlot");
		header.add("description");
		header.add("staffInCharge");
		header.add("visibility");
		header.add("numberOfCampDays");
		header.add("attendees");  //ArrayList
		header.add("committee");   //ArrayList
		header.add("enquiries");  //ArrayList
		header.add("sugggestion");   //ArrayList
		

        // Write data to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write the header (keys of the Map) to the CSV file
            writer.write(String.join(",", header));
            writer.newLine();

			ArrayList<String> attendeestr = new ArrayList<String>();
			ArrayList<String> committeestr = new ArrayList<String>();
			ArrayList<String> enquiriesstr = new ArrayList<String>();
			ArrayList<String> suggestionsstr = new ArrayList<String>();
			ArrayList<String> singleenquiry = new ArrayList<String>();
			ArrayList<String> singlesuggestion = new ArrayList<String>();
			

			for (Camp camp : dataMap.values()){
				//convert to ArrayList<String> to store to csv
				attendeestr.clear();
				committeestr.clear();
				enquiriesstr.clear();
				suggestionsstr.clear();
				singleenquiry.clear();
				singlesuggestion.clear();

				for(int i=0; i<camp.getAttendees().size();i++){
					attendeestr.add(camp.getAttendees().get(i).getUserID());
				}

				for(int i=0; i<camp.getCommittee().size();i++){
					committeestr.add(camp.getCommittee().get(i).getUserID());
				}

				for(int i=0; i<camp.getEnquiryList().size();i++){
					singleenquiry.add(camp.getEnquiryList().get(i).getEnquiry());
					singleenquiry.add(camp.getEnquiryList().get(i).getAnswer());
					singleenquiry.add(camp.getEnquiryList().get(i).getInquirer());
					singleenquiry.add(camp.getEnquiryList().get(i).getAnswerer());
					singleenquiry.add(String.valueOf(camp.getEnquiryList().get(i).getProcessed()));

					enquiriesstr.add( String.join("*", singleenquiry));
					singleenquiry.clear();
				}

				for(int i=0; i<camp.getSuggestionList().size();i++){
					singlesuggestion.add(camp.getSuggestionList().get(i).getSuggestion());
					singlesuggestion.add(camp.getSuggestionList().get(i).getSuggestBy());
					singlesuggestion.add(String.valueOf(camp.getSuggestionList().get(i).getStatus()));
					singlesuggestion.add(String.valueOf(camp.getSuggestionList().get(i).getAccepted()));

					suggestionsstr.add( String.join("*", singlesuggestion));
					singlesuggestion.clear();
				}

				

				String attendees = String.join("|", attendeestr);
				String committee = String.join("|", committeestr);
				String enquiries = String.join("|", enquiriesstr);
				String suggestions = String.join("|", suggestionsstr);

				// Write the values to the CSV file
            	writer.write(String.format("%s,%s,%s,%s,%s,%s,%d,%d,%d,%d,%s,%s,%b,%d,%s,%s,%s,%s", 
											camp.getCampName(),camp.getCampDate().toString(), 
											camp.getCampEndDate().toString(), camp.getRegCloseDate().toString(), 
											camp.getUserGroup(),camp.getLocation(),camp.getTotalSlots(),camp.getRemainingSlot(),
											camp.getCampCommitteeSlots(),camp.getCampCommitteeRemainingSlots(),
											camp.getDescription(),camp.getStaffInCharge(),
											camp.getVisibility(),camp.getNumberOfCampDays(),
											attendeestr.isEmpty() ? "null" : attendees,
											committeestr.isEmpty() ? "null" : committee,
											enquiriesstr.isEmpty() ? "null" : enquiries,
											suggestionsstr.isEmpty() ? "null" : suggestions));
				writer.newLine();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
     * Reads camp data from CSV file and import to the database by creating new camp instances
     */
	public static void readfromCampCSV(){
		String csvFilePath = "CAMs App/csvdata/camp.csv";
		Map<String, Camp> user = Database.getCampData();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            
			String line;
			int counter = 0;
			reader.readLine();  //skip the header

            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
				counter++;
                // Split the line into an array of values using a comma as the delimiter
				String[] values = line.split(",");
				int i=0;
				String campName = values[i];
				LocalDate campDate = LocalDate.parse(values[++i],formatter);
				LocalDate campEndDate = LocalDate.parse(values[++i],formatter);
				LocalDate regCloseDate = LocalDate.parse(values[++i],formatter);
				Faculty userGroup = Faculty.valueOf(values[++i]);
				String location = values[++i];
				int totalSlots = Integer.parseInt(values[++i]);
				int remainingSlot = Integer.parseInt(values[++i]);        
				int campCommitteeSlots = Integer.parseInt(values[++i]);
				int campCommitteeRemainingSlot = Integer.parseInt(values[++i]);
				String description = values[++i];
				String staffInCharge = values[++i];
				Boolean visibility = Boolean.valueOf(values[++i]);
				//int numberOfCampDays = Integer.parseInt(values[++i]);	
				Integer.parseInt(values[++i]);	
				String[] attendeeArr = values[++i].split("\\|");
				String[] committeeArr = values[++i].split("\\|");
				String[] enquiriestr = values[++i].split("\\|");
				String[] suggestionstr = values[++i].split("\\|");

				Camp camp = new Camp( campName,  campDate,  campEndDate, 
									  regCloseDate,  userGroup, location, 
									  totalSlots,  campCommitteeSlots,  description, 
									  staffInCharge,  visibility);
				
				//camp.setNumberOfCampDays(numberOfCampDays);
				camp.setRemainingSlot(remainingSlot);
				camp.setCampCommitteeRemainingSlots(campCommitteeRemainingSlot);
				
				// //insert the data into ArrayList
				if (!attendeeArr[0].equals("null")){
					for(int j=0; j<attendeeArr.length; j++){
						Student student = getStudent(attendeeArr[j]);
						camp.addAttendees(student);
					}
				}

				if (!committeeArr[0].equals("null")){
					for(int j=0; j<committeeArr.length; j++){
						Student student = getStudent(committeeArr[j]);
						camp.addCommittee(student);
					}
				}

				//split the data to create enquiry and suggestions
				if(!enquiriestr[0].equals("null")){
					for(int j=0; j<enquiriestr.length; j++){
						String[] singleEnquiry = enquiriestr[j].split("\\*");
						String enquirymessage = singleEnquiry[0];
						String asnwer = singleEnquiry[1];
						String inquirer = singleEnquiry[2];
						String asnwerer = singleEnquiry[3];
						boolean enquiryProccessd = Boolean.valueOf(singleEnquiry[4]);

						Enquiries enquiries = new Enquiries(enquirymessage,inquirer);
						enquiries.setAnswerer(asnwerer);
						enquiries.setAnswer(asnwer);
						if(enquiryProccessd)
							enquiries.setProcessed();
						camp.addQuery(enquiries);
					}
				}
				
				if(!suggestionstr[0].equals("null")){
					for(int j=0; j<suggestionstr.length; j++){
						String[] singleSuggestion = suggestionstr[j].split("\\*");
						System.out.println(singleSuggestion);
						String message = singleSuggestion[0];
						String suggester = singleSuggestion[1];
						String status = singleSuggestion[2].toUpperCase();
						boolean accepted = Boolean.valueOf(singleSuggestion[3]);

						
						Suggestions newSuggestion = new Suggestions(message, suggester);
						newSuggestion.setSuggestionStatus(status);
						newSuggestion.setAccepted(accepted);
						camp.addSuggestion(newSuggestion);
						
					}
					
				}
				
				if(counter!=0)
					user.put(values[0], camp);
				
                System.out.println(); // Move to the next line for the next row
            }

			Database.setCampData(user);
			for(Student student:Database.getStudentsData().values()){
				String campName = student.getCommitteeCampName();
				student.setCommitteeCamp(campName);;
			}
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	/**
     * Get camp reference through the camp name
	 * @param campName : Name of camp to retrive
	 * @return the camp data from the retrived camp name
     */
	public static Camp getCamp(String campName){
		Map<String ,Camp> campData = Database.getCampData();
		return campData.get(campName);
	}
	/**
     * Get student reference through the student userID
	 * @param userID : userID of student to retrive
	 * @return the student data from the retrived userID
     */
	public static Student getStudent(String userID){
		Map<String ,Student> student = Database.getStudentsData();
		return student.get(userID);
	}
	/**
     * Check if input campName and userID match with the camp, used to print staff created camp
	 * @param campName : Name of camp to check
	 * @param userID : Staff user's userID to check
	 * @return {@code true} if the staff user is in charge of the camp,{@code false} otherwise
     */
	public static boolean checkCampName(String campName, String userID){
		Map<String, Camp> camp1 = Database.getCampData(); 

        for (Camp camp : camp1.values()){
            if(camp.getStaffInCharge().equals(userID) && camp.getCampName().equals(campName)){
				return true;
			}
                //return true;
        }
		return false;
	}
	/**
     * Check if camp exist of a specific name exist in the Database
	 * @param campName : Name of camp to check
	 * @return {@code true} if the camp exist in the database, {@code false} otherwise
     */
	public static boolean checkIfCampNameExists(String campName){
		Map<String, Camp> camp1 = Database.getCampData(); 
        for (Camp camp : camp1.values()){
            if(camp.getCampName().equals(campName)){
				return true;
			}
        }
		return false;
	}


}
