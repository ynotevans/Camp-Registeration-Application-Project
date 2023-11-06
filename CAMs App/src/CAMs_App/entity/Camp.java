package CAMs_App.entity;

import java.util.ArrayList;

import CAMs_App.enums.*;

public class Camp extends CampInfo{

    private ArrayList<Student> attendees = new ArrayList<>();
    private ArrayList<Student> committee = new ArrayList<>();
    private ArrayList<Enquiries> enquiries = new ArrayList<>();
    private ArrayList<Suggestions> suggestions = new ArrayList<>();
    
    //constructor
    Camp(String campName , String campDate , String regCloseDate , Faculty userGroup ,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int numberOfCampDays){
             super(campName , campDate , regCloseDate ,  userGroup , location, totalSlots, campCommitteeSlots, description,  staffInCharge,  visibility, numberOfCampDays);
       
    }

    //default constructor
    Camp(){
        super();
    }

    public void addAttendees(Student attendee){
        this.attendees.add(attendee);
    }

    public void addCommittee(Student committee){
        this.committee.add(committee);
    }

    public void addQuery(Enquiries q){
        this.enquiries.add(q);
    }

    public void addSuggestion(Suggestions s){
        this.suggestions.add(s);
    }
    
    //getters
    public ArrayList<Student> getAttendees(){
        return this.attendees;
    }

    public ArrayList<Student> getCommittee(){
        return this.committee;
    }

    public ArrayList<Enquiries> getEnquiryList(){
        return this.enquiries;
    }

    public ArrayList<Suggestions> getSuggestionList(){
        return this.suggestions;
    }
    
}
