package CAMs_App;

import java.util.*;
import CAMs_App.enums.*;
public class Student extends User {
	Scanner scan = new Scanner(System.in);
	Student(int userID, String password, Faculty faculty, Status status) {
		super(userID, password, faculty, status);
	}
	
	private ArrayList<Camp> camp_registered = new ArrayList<>();
	private ArrayList<Camp> camp_withdrawn = new ArrayList<>();
	private Camp comitteeCamp = null;
	private int points = 0;
	
	public void registerCamp (Camp camp) {
		if(camp_registered.contains(camp)) {	
			System.out.println("Student has already registered for this camp.");
			return;
		}
		if(camp_withdrawn.contains(camp)) {	
			System.out.println("Student has already withdrawn from this camp.");
			return;
		}
		if(comitteeCamp == camp) {
			System.out.println("Student is already comittee of camp.");
			return;
		}
		if(checkDateClash(camp)) {
			System.out.println("There is a date clash.");
			return;
		}
		camp_registered.add(camp);
		
	}
	
	public void withdrawCamp (Camp camp) {
		for(int i = 0; i < camp_registered.size(); i++) {
			if (camp_registered.get(i) == camp) {
				camp_registered.remove(i);
				camp_withdrawn.add(camp);
				comitteeCamp = comitteeCamp == camp ? null : comitteeCamp;
				break;
			}
		}
		System.out.println("Student is not registered to this camp.");
	}
	
	public boolean isComittee () {
		return comitteeCamp != null ? true : false;
	}
	
	public boolean checkDateClash (Camp camp) {
		for(int i = 0; i < camp_registered.size(); i++) {
			if (camp_registered.get(i).getCampDate() == camp.getCampDate()) {
				return true;
			}
		}
		return false;
	}
	
	public void setPoints(int i) {
		points = i;
	}
	
	public ArrayList<Camp> getAttendingList () {
		return camp_registered;
	}
	
	public Camp getComitteeCamp () {
		return comitteeCamp;
	}
	
	public int getPoints() {
		return points;
	}
	public void addPoints(){
		this.points++;
	}


	//Enquiries
	public Enquiries createQuery(){
		System.out.println("Please write down your query: ");
		String query = scan.next();
		
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
			q.setEnquiry(scan.next());
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
