package CAMs_App.service;

import java.util.Scanner;

import CAMs_App.entity.Camp;
import CAMs_App.entity.Enquiries;

public class StudentService {
	private static final Scanner sc = new Scanner(System.in);
	public void viewAvailableCamp() {
		
	}
	
	public void joinAsAttendee(String camp) {
		
	}
	
	
	
	
	//Enquiries
	public Enquiries createQuery(){
		System.out.println("Please write down your query: ");
		String query = sc.next();
		
		Enquiries q = new Enquiries(query, this.getUserID());	
		return q;
	}

	public void submitEnquiry(Camp camp,Enquiries q){
		camp.addQuery(q);
	}

	public void viewEnquiry(Camp camp , Enquiries q){
		if(q.getInquirer() != this.getUserID()){
			System.out.println("Unable to view enquiry from other student");
		}
		else{
			q.viewEnquiries();
		}
	}

	public void editEnquiry(Camp camp , Enquiries q){
		if(q.getInquirer() != this.getUserID()){
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
			if(q.getInquirer() == this.getUserID()) camp.getEnquiryList().remove(q);
			else{
				System.out.println("You do not have the permisson to delete the selected query!!");
			}
		}

	}
}