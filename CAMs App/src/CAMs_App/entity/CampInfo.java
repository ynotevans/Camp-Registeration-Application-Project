package CAMs_App.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import CAMs_App.enums.*;

/**
 * The {@link CampInfo} class contains all information on a {@link Camp} 
 * that inherits it.
 */
public class CampInfo{
	/**
	 * Name of the camp.
	 */
    private String campName;
    /**
     * Start date of the camp.
     */
    private LocalDate campDate;
    /**
     * End date of the camp.
     */
    private LocalDate campEndDate;
    /**
     * Closing date for registration of the camp.
     */
    private LocalDate regCloseDate;
    /**
     * The {@link Faculty} the camp is made for.
     */
    private Faculty userGroup;
    /**
     * Location of the camp.
     */
    private String location;
    /**
     * The total slots for the camp including both committee and attendee.
     */
    private int totalSlots;
    /**
     * The remaining slots for attendees for the camp.
     */
    private int remainingSlot; 
    /**
     * The total slots for committee members for the camp.
     */
    private int campCommitteeSlots;
    /**
     * The remaining slots for committee members for the camp.
     */
    private int campCommitteeRemainingSlot; 
    /**
     * An introductory description of the camp.
     */
    private String description;
    /**
     * The staff in charge of the camp.
     */
    private String staffInCharge;
    /**
     * Whether the camp is visible to students or not.
     */
    private Boolean visibility;
    /**
     * The duration of the camp in days.
     */
    private int numberOfCampDays;
    

    //constructor
    /**
     * Constructs a {@link CampInfo} object for a new camp using the given info.
     * 
     * @param campName			 Name of the camp.	
     * @param campDate			 Start date of the camp.
     * @param campEndDate		 End date of the camp.
     * @param regCloseDate		 Closing date for registration of the camp.
     * @param userGroup			 The {@link Faculty} the camp is made for.
     * @param location			 Location of the camp.
     * @param totalSlots		 The total slots for attendees for the camp.
     * @param campCommitteeSlots The total slots for committee members for the camp.
     * @param description		 An introductory description of the camp.
     * @param staffInCharge		 The staff in charge of the camp.
     * @param visibility		 Whether the camp is visible to students or not.
     * @param remainingSlot		 The remaining slots for attendees for the camp.
     */
    public CampInfo(String campName, LocalDate campDate, LocalDate campEndDate, LocalDate regCloseDate, Faculty userGroup,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int remainingSlot){
        setCampName(campName);
        setCampDate(campDate);
        setCampEndDate(campEndDate);
        setRegCloseDate(regCloseDate);
        setUserGroup(userGroup);
        setLocation(location);
        setTotalSlots(totalSlots); 
        setRemainingSlot(totalSlots - campCommitteeSlots); 
        setCampCommitteeSlots(campCommitteeSlots); 
        setCampCommitteeRemainingSlots(campCommitteeSlots); 
        setNumberOfCampDays((int)ChronoUnit.DAYS.between(campDate, campEndDate)+1);
        setDescription(description);
        setStaffInCharge(staffInCharge);
        setVisibility(visibility);

    } 

    /**
     * Constructs a {@link CampInfo} object with empty parameters.
     */
    //default constructor
    public CampInfo(){};


    //setters
    /**
     * Sets the name of the camp.
     * 
     * @param campName The name to be set for the camp.
     */
    public void setCampName(String campName){
        this.campName = campName;
    }
    
    /**
     * Sets the starting date of the camp.
     * 
     * @param campDate The date to be set as the starting date.
     */
    public void setCampDate(LocalDate campDate){
        this.campDate = campDate;
    }
    
    /**
     * Sets the ending date of the camp.
     * 
     * @param campEndDate The date to be set as the ending date.
     */
    public void setCampEndDate(LocalDate campEndDate){
        this.campEndDate = campEndDate;
    }
   
    /**
     * Sets the registration closing date of the camp.
     * 
     * @param regCloseDate The date to be set as the registration closing date.
     */
    public void setRegCloseDate(LocalDate regCloseDate){
        this.regCloseDate = regCloseDate;
    }
    
    /**
     * Sets the {@link Faculty} this camp is for.
     * 
     * @param userGroup The {@link Faculty} to be set.
     */
    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }
    
    /**
     * Sets the location of the camp
     * 
     * @param location The location to be set.
     */
    public void setLocation(String location){
        this.location = location;
        }
    
    /**
     * Sets the total slots for attendees for the camp.
     * 
     * @param totalSlots The number of total slots to be set.
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }
    
    /**
     * Sets total slots for committee members for the camp.
     * 
     * @param campCommitteeSlots The number of total slots to be set.
     */
    public void setCampCommitteeSlots(int campCommitteeSlots){
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * Sets the remaining slots for committee members for the camp.
     * 
     * @param campCommitteeRemainingSlot The number of remaining slots to be set.
     */
    public void setCampCommitteeRemainingSlots(int campCommitteeRemainingSlot){
        this.campCommitteeRemainingSlot = campCommitteeRemainingSlot;
    }
    
    /**
     * Sets the description of the camp.
     * 
     * @param description The description to be set.
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Sets the staff in charge of the camp.
     * 
     * @param staffInCharge The staff to be set.
     */
    public void setStaffInCharge(String staffInCharge){
        this.staffInCharge = staffInCharge;
    }
    
    /**
     * Sets the visibility of the camp.
     * 
     * @param visibility Whether the camp is visible to students.
     */
    public void setVisibility(Boolean visibility){
        this.visibility = visibility;
    }
    
    /**
     * Sets the duration of the camp.
     * 
     * @param numberOfCampDays The duration of the camp to be set in days.
     */
    public void setNumberOfCampDays(int numberOfCampDays) {
        this.numberOfCampDays = numberOfCampDays;
    }
    
    /**
     * Sets the remaining slots for attendees for the camp.
     * 
     * @param remainingSlot The number of remaining slots to be set.
     */
    public void setRemainingSlot(int remainingSlot) {
        this.remainingSlot = remainingSlot;
    }

    //getters
    /**
     * Gets the name of the camp.
     * 
     * @return The name of the camp
     */
    public String getCampName(){
        return campName;
    }
    
    /**
     * Gets the starting date of the camp.
     * 
     * @return The starting date of the camp.
     */
    public LocalDate getCampDate(){
        return campDate;
    } 
    
    /**
     * Gets the ending date of the camp.
     * 
     * @return The ending date of the camp.
     */
    public LocalDate getCampEndDate(){
        return campEndDate;
    } 

    /**
     * Gets the registration closing date of the camp.
     * 
     * @return The registration closing date of the camp.
     */
    public LocalDate getRegCloseDate(){
        return regCloseDate;
    } 
    
    /**
     * Gets the {@link Faculty} the camp is made for.
     * 
     * @return The {@link Faculty} of the camp.
     */
    public Faculty getUserGroup() {
        return userGroup;
    }
    
    /**
     * Gets the location of the camp.
     * 
     * @return the location of the camp.
     */
    public String getLocation(){
        return location;
    } 
    
    /**
     * Gets the total slots for attendees for the camp.
     *  
     * @return The number of total slots.
     */
    public int getTotalSlots(){
        return totalSlots;
    } 
    
    /**
     * Gets the total slots for committee members for the camp.
     * 
     * @return The number of total slots.
     */
    public int getCampCommitteeSlots(){
        return campCommitteeSlots;
    } 
    
    /**
     * Gets the remaining slots for committee members for the camp.
     * 
     * @return The number of remaining slots.
     */
    public int getCampCommitteeRemainingSlots(){
        return this.campCommitteeRemainingSlot; 
    }
    
    /**
     * Gets the description of the camp.
     * 
     * @return The description of the camp.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Gets the staff in charge of the camp.
     * 
     * @return The staff in charge of the camp.
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }
    
    /**
     * Gets the visibility of the camp.
     * 
     * @return The visibility of the camp.
     */
    public Boolean getVisibility() {
        return visibility;
    }
    
    /**
     * Gets the duration of the camp.
     * 
     * @return The duration of the camp in days.
     */
    public int getNumberOfCampDays() {
        return numberOfCampDays;
    }
    
    /**
     * Gets the remaining slots for attendees for the camp.
     * 
     * @return The number of remaining slots.
     */
    public int getRemainingSlot() {
        return this.remainingSlot;
    }


   

}
