package CAMs_App.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import CAMs_App.enums.*;
	
/**
 * The {@link Camp} class represents a camp in the system.
 * It extends the {@link CampInfo} class, which contains basic camp information.
 * It implements the comparable interface for sorting purpose.
 * 
 *  @author Denis Yu
 *  @version 1.0
 *  @since 2023-10-25
 */

public class Camp extends CampInfo implements Comparable<Camp>{
	
    /**
     * List of {@link Student} who are attending this camp.
     */
    private ArrayList<Student> attendees = new ArrayList<>();
    /**
     * List of {@link Student} who are committee members.
     */
    private ArrayList<Student> committee = new ArrayList<>();
    /**
     * List of submitted {@link Enquiries} from attendees.
     */
    private ArrayList<Enquiries> enquiries = new ArrayList<>();
    /**
     * List of submitted {@link Suggestions} from committee members.
     */
    private ArrayList<Suggestions> suggestions = new ArrayList<>();
    
    /**
     * Constructs a {@link Camp} object for a new camp using the given camp info.
     * 
     * @param campName				The name of this camp.
     * @param campDate				The starting date of this camp.
     * @param campEndDate			The ending date of this camp.
     * @param regCloseDate			The closing date for registration of this camp.
     * @param userGroup				The {@link Faculty} this camp is for.
     * @param location				The location of this camp.
     * @param totalSlots			The total slots for attendees for this camp.
     * @param campCommitteeSlots	The total slots for committee members for this camp.
     * @param description			An introductory description of the camp.
     * @param staffInCharge			The staff that is in charge of this camp.
     * @param visibility			Whether this camp is visible to students or not.
     */
    public Camp(String campName , LocalDate campDate , LocalDate campEndDate, LocalDate regCloseDate , Faculty userGroup ,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility){
             super(campName , campDate ,campEndDate, regCloseDate ,  userGroup , location, totalSlots, campCommitteeSlots, description,  staffInCharge,  visibility, totalSlots); //initially total slot = remaining slot
       
    }
    
    /**
     * Constructs a {@link Camp} object with empty parameters.
     */
    public Camp(){
        super();
    }

    /**
     * Adds a {@link Student} to the list of attendees.
     * 
     * @param attendee The {@link Student} being added.
     */
    public void addAttendees(Student attendee){
        this.attendees.add(attendee);
    }
    
    /**
     * Adds a {@link Student} to the list of committee members
     * .
     * @param committee The {@link Student} being added.
     */
    public void addCommittee(Student committee){
        this.committee.add(committee);
    }
    
    /**
     * Adds an entry to the list of {@link Enquiries}.
     * 
     * @param q The inquiry to add.
     */
    public void addQuery(Enquiries q){
        this.enquiries.add(q);
    }

    /**
     * Adds an entry to the list of {@link Suggestions}.
     * 
     * @param s The suggestion to add.
     */
    public void addSuggestion(Suggestions s){
        this.suggestions.add(s);
    }
    
    /**
     * Returns the list of attendees.
     * 
     * @return The list of {@link Student} who are attending.
     */
    public ArrayList<Student> getAttendees(){
        return this.attendees;
    }

    /**
     * Returns the list of committee.
     * 
     * @return The list of {@link Student} who are committee members.
     */
    public ArrayList<Student> getCommittee(){
        return this.committee;
    }
    
    /**
     * Returns the list of enquiries.
     * 
     * @return The list of {@link Enquiries} that are submitted to this camp.
     */
    public ArrayList<Enquiries> getEnquiryList(){
        return this.enquiries;
    }
    
    /**
     * Returns the list of suggestions.
     * 
     * @return The list of {@link Suggestions} that are submitted to this camp.
     */
    public ArrayList<Suggestions> getSuggestionList(){
        return this.suggestions;
    }
    
    /**
     * Compares the different parameters between camps for sorting purpose.
     */
     @Override
    public int compareTo(Camp other) {
        return Comparator.comparing(Camp::getCampName)
                .thenComparing(Camp::getLocation)
                .thenComparing(Camp::getCampDate)
                .thenComparing(Camp::getUserGroup)
                .compare(this, other);
    }
    
}
