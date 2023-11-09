package CAMs_App.service;

import java.util.ArrayList;
import java.util.Scanner;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Enquiries;
import CAMs_App.entity.Student;

public class StudentService {
	private static final Scanner sc = new Scanner(System.in);
	private Student user = Database.getStudentsData().get(AuthData.getCurrentUser().getUserID()); 
	
	
	
	public void viewAvailableCamp() {
		
	}
	
	public void viewRegisteredCamp() {
		ArrayList<String> list = user.getRegisteredCamp();
		for(int i =0;i < list.size(); i++) {
			
		}
	}
	
	
	public void joinAsAttendee(String camp) {
		
	}
	
	public void joinAsCommittee(String camp) {
		
	}
	
	public void withdrawCamp(String camp) {
		
	}
	
	//Enquiries
	public Enquiries createQuery(){
		System.out.println("Please write down your query: ");
		String query = sc.next();
		
		Enquiries q = new Enquiries(query, user.getUserID());	
		return q;
	}

	public void submitEnquiry(Camp camp,Enquiries q){
		camp.addQuery(q);
	}

	public void viewEnquiry(Camp camp , Enquiries q){
		if(q.getInquirer() != user.getUserID()){
			System.out.println("Unable to view enquiry from other student");
		}
		else{
			q.viewEnquiries();
		}
	}

	public void editEnquiry(Camp camp , Enquiries q){
		if(q.getInquirer() != user.getUserID()){
			System.out.println("You are not allowed to edit enquiry made by other student!!");
		}
		else if(q.getProcessed()){
			System.out.println("Enquiry has been processed");
		}
		else{
			System.out.println("Original question: " + q.getEnquiry());
			System.out.println("Edit: ");
			q.setEnquiry(sc.next());
		}
	}

	public void deleteEnquiry(Camp camp ,Enquiries q) {
		if(q.getProcessed()){
			System.out.println("Unable to delete processed queries");
		}
		else{
			if(q.getInquirer() == user.getUserID()) camp.getEnquiryList().remove(q);
			else{
				System.out.println("You do not have the permisson to delete the selected query!!");
			}
		}

	}
}