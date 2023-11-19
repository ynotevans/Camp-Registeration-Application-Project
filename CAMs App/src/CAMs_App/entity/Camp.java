package CAMs_App.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import CAMs_App.enums.*;

public class Camp extends CampInfo{

    
    private ArrayList<Student> attendees = new ArrayList<>();
    private ArrayList<Student> committee = new ArrayList<>();
    private ArrayList<Enquiries> enquiries = new ArrayList<>();
    private ArrayList<Suggestions> suggestions = new ArrayList<>();
    
    //constructor
    public Camp(String campName , LocalDate campDate , LocalDate campEndDate, LocalDate regCloseDate , Faculty userGroup ,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int numberOfCampDays, int remainingSlot){
             super(campName , campDate ,campEndDate, regCloseDate ,  userGroup , location, totalSlots, campCommitteeSlots, description,  staffInCharge,  visibility, numberOfCampDays, remainingSlot);
       
    }
    
    //default constructor
    public Camp(){
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
