package CAMs_App.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import CAMs_App.enums.*;

public class Camp extends CampInfo{

    
    private static ArrayList<Student> attendees = new ArrayList<>();
    private static ArrayList<Student> committee = new ArrayList<>();
    private static ArrayList<Enquiries> enquiries = new ArrayList<>();
    private static ArrayList<Suggestions> suggestions = new ArrayList<>();
    
    //constructor
    public Camp(String campName , LocalDate campDate , LocalDate campEndDate, LocalDate regCloseDate , Faculty userGroup ,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int numberOfCampDays){
             super(campName , campDate ,campEndDate, regCloseDate ,  userGroup , location, totalSlots, campCommitteeSlots, description,  staffInCharge,  visibility, numberOfCampDays);
       
    }
    
    //default constructor
    public Camp(){
        super();
    }


    public void addAttendees(Student attendee){
        Camp.attendees.add(attendee);
    }

    public void addCommittee(Student committee){
        Camp.committee.add(committee);
    }

    public void addQuery(Enquiries q){
        Camp.enquiries.add(q);
    }

    public void addSuggestion(Suggestions s){
        Camp.suggestions.add(s);
    }
    
    //getters
    public ArrayList<Student> getAttendees(){
        return Camp.attendees;
    }

    public ArrayList<Student> getCommittee(){
        return Camp.committee;
    }

    public ArrayList<Enquiries> getEnquiryList(){
        return Camp.enquiries;
    }

    public ArrayList<Suggestions> getSuggestionList(){
        return Camp.suggestions;
    }
    
}
