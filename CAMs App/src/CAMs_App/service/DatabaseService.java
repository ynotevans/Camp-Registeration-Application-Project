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
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.crypto.Data;

import CAMs_App.entity.Student;
import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Staff;
import CAMs_App.enums.Faculty;




public class DatabaseService {
    
	public static void writetoStudentCSV(Map<String, Student> dataMap){
		// Specify the file path for the CSV file
        String csvFilePath = "CAMs App/csvdata/output.csv";
		Set<String> header = new LinkedHashSet<String>();
		header.add("userID");
		header.add("password");
		header.add("Faculty");
		header.add("isCommittee");
		header.add("points");
		header.add("registeredCamp");  //ArrayList
		header.add("withdrawnCamp");   //ArrayList

        // Write data to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write the header (keys of the Map) to the CSV file
            writer.write(String.join(",", header));
            writer.newLine();

			for (Student student : dataMap.values()){

				String registeredCamp = String.join("|", student.getRegisteredCamp());
				String withdrawnCamp = String.join("|", student.getWithdrawnCamp());

				// Write the values to the CSV file
            	writer.write(String.format("%s,%s,%s,%b,%d,%s,%s" , student.getUserID(),student.getPassword(), 
											student.getFaculty(),student.getIsComittee(),student.getPoints(),
											registeredCamp.isEmpty() ? "null" :registeredCamp,
											withdrawnCamp.isEmpty() ? "null" :withdrawnCamp));
				writer.newLine();
			}

            
            System.out.println("Data written to " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

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
				Student student = new Student(values[i], values[++i], Faculty.valueOf(values[++i]));
				student.setIsComittee(Boolean.parseBoolean(values[++i]));
				student.setPoints(Integer.parseInt(values[++i]));
				ArrayList<String> registerCamp = new ArrayList<String>();
				ArrayList<String> withdrawCamp = new ArrayList<String>() ;
				
				//insert the data into ArrayList
				String[] registeredCampArray = values[++i].split("\\|");
				if (!registeredCampArray[0].equals("null")){
					registerCamp.addAll(Arrays.asList(registeredCampArray));
					student.setRegisteredCamp(registerCamp);
				}
                	
                String[] withdrawnCampArray = values[++i].split("\\|");
				if (!withdrawnCampArray[0].equals("null")){
					withdrawCamp.addAll(Arrays.asList(withdrawnCampArray));
					student.setRegisteredCamp(withdrawCamp);
				}
                	
				user1.put(values[0], student);
                System.out.println(); // Move to the next line for the next row
            }

			Database.setStudentsData(user1);
			System.out.println("read data from student.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void writetoStaffCSV(Map<String, Staff> dataMap){
		// Specify the file path for the CSV file
        String csvFilePath = "CAMs App/csvdata/staff.csv";
		Set<String> header = new LinkedHashSet<String>();
		header.add("userID");
		header.add("password");
		header.add("Faculty");
		

        // Write data to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            // Write the header (keys of the Map) to the CSV file
            writer.write(String.join(",", header));
            writer.newLine();

			for (Staff staff : dataMap.values()){
				// Write the values to the CSV file
            	writer.write(String.format("%s,%s,%s" , staff.getUserID(),staff.getPassword(), 
											staff.getFaculty()));
				writer.newLine();
			}

            
            System.out.println("Data written to " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

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
				Staff staff = new Staff(values[i], values[++i], Faculty.valueOf(values[++i]));
				
				user.put(values[0], staff);
                System.out.println(); // Move to the next line for the next row
            }

			Database.setstaffData(user);
			System.out.println("read data from output.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

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
		header.add("campCommitteeSlots");
		header.add("description");
		header.add("staffInCharge");
		header.add("visibility");
		header.add("remainingSlot");
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

			for (Camp camp : dataMap.values()){
				//convert to ArrayList<String> to store to csv
				attendeestr.clear();
				committeestr.clear();

				for(int i=0; i<camp.getAttendees().size();i++){
					attendeestr.add(camp.getAttendees().get(i).getUserID());
				}

				for(int i=0; i<camp.getCommittee().size();i++){
					committeestr.add(camp.getCommittee().get(i).getUserID());
				}

				String attendees = String.join("|", attendeestr);
				String committee = String.join("|", committeestr);

				// Write the values to the CSV file
            	writer.write(String.format("%s,%s,%s,%s,%s,%s,%d,%d,%s,%s,%b,%d,%s,%s", 
											camp.getCampName(),camp.getCampDate().toString(), 
											camp.getCampEndDate().toString(), camp.getRegCloseDate().toString(), 
											camp.getUserGroup(),camp.getLocation(),camp.getTotalSlots(),
											camp.getCampCommitteeSlots(),camp.getDescription(),camp.getStaffInCharge(),
											camp.getVisibility(),camp.getRemainingSlot(),
											attendeestr.isEmpty() ? "null" : attendees,
											committeestr.isEmpty() ? "null" : committee));
				writer.newLine();
			}

            System.out.println("Data written to " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void readfromCampCSV(){
		String csvFilePath = "CAMs App/csvdata/camp.csv";
		Map<String, Camp> user = Database.getCampData();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            
			String line;
			reader.readLine();  //skip the header

            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
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
				int campCommitteeSlots = Integer.parseInt(values[++i]);
				String description = values[++i];
				String staffInCharge = values[++i];
				Boolean visibility = Boolean.valueOf(values[++i]);
				int numberOfCampDays = Integer.parseInt(values[++i]);
				int remainingSlot;        //need to update ltr
				String[] attendeeArr = values[++i].split("\\|");
				String[] committeeArr = values[++i].split("\\|");
				

				// Camp camp = new Camp( campName,  campDate,  campEndDate, 
				// 					  regCloseDate,  userGroup, location, 
				// 					  totalSlots,  campCommitteeSlots,  description, 
				// 					  staffInCharge,  visibility,  numberOfCampDays);
				
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
				user.put(values[0], camp);
                System.out.println(); // Move to the next line for the next row
            }

			Database.setCampData(user);
			System.out.println("read data from camp.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	public static Camp getCamp(String campName){
		Map<String ,Camp> campData = Database.getCampData();
		return campData.get(campName);
	}

	public static Student getStudent(String userID){
		Map<String ,Student> stduent = Database.getStudentsData();
		return stduent.get(userID);
	}

	public static boolean checkCampName(String campName, String userID){
		Map<String, Camp> camp1 = Database.getCampData(); 

        for (Camp camp : camp1.values()){
            if(camp.getStaffInCharge() == userID && camp.getCampName().equals(campName)){
				return true;
			}
                //return true;
        }
		return false;
	}

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
