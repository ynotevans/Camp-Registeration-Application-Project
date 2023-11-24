package CAMs_App.entity;

import java.time.LocalDate;
// import java.util.*;
import CAMs_App.enums.*;

public class CampInfo {
    private String campName;
    private LocalDate campDate;
    private LocalDate campEndDate;
    private LocalDate regCloseDate;
    private Faculty userGroup;
    private String location;
    private int totalSlots;
    private int remainingSlot; //remaining slot for attendees
    private int campCommitteeSlots;
    private int campCommitteeRemainingSlot; // remaining slot for committee
    private String description;
    private String staffInCharge;
    private Boolean visibility;
    private int numberOfCampDays;
    

    //constructor
    public CampInfo(String campName, LocalDate campDate, LocalDate campEndDate, LocalDate regCloseDate, Faculty userGroup,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int remainingSlot){
        setCampName(campName);
        setCampDate(campDate);
        setCampEndDate(campEndDate);
        setRegCloseDate(regCloseDate);
        setUserGroup(userGroup);
        setLocation(location);
        setTotalSlots(totalSlots); //total = attendee + student
        setRemainingSlot(remainingSlot); //remaining slot for student
        setCampCommitteeSlots(campCommitteeSlots); //total committee slot
        setCampCommitteeRemainingSlots(campCommitteeSlots); //remaining committee slot
        //total remaining slot = remaining slot for committee + student

        setDescription(description);
        setStaffInCharge(staffInCharge);
        setVisibility(visibility);

    } 


    //default constructor
    public CampInfo(){};


    //setters
    public void setCampName(String campName){
        this.campName = campName;
    }

    public void setCampDate(LocalDate campDate){
        this.campDate = campDate;
    }

    public void setCampEndDate(LocalDate campEndDate){
        this.campEndDate = campEndDate;
    }
   
    public void setRegCloseDate(LocalDate regCloseDate){
        this.regCloseDate = regCloseDate;
    }

    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }

    public void setLocation(String location){
        this.location = location;
        }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public void setCampCommitteeSlots(int campCommitteeSlots){
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public void setCampCommitteeRemainingSlots(int campCommitteeRemainingSlot){
        this.campCommitteeRemainingSlot = campCommitteeRemainingSlot;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public void setStaffInCharge(String staffInCharge){
        this.staffInCharge = staffInCharge;
    }

    public void setVisibility(Boolean visibility){
        this.visibility = visibility;
    }

    public void setNumberOfCampDays(int numberOfCampDays) {
        this.numberOfCampDays = numberOfCampDays;
    }

    public void setRemainingSlot(int remainingSlot) {
        this.remainingSlot = remainingSlot;
    }

    //getters
    public String getCampName(){
        return campName;
    }
        
    public LocalDate getCampDate(){
        return campDate;
    } 
    
    public LocalDate getCampEndDate(){
        return campEndDate;
    } 

    public LocalDate getRegCloseDate(){
        return regCloseDate;
    } 

    public Faculty getUserGroup() {
        return userGroup;
    }

    public String getLocation(){
        return location;
    } 

    public int getTotalSlots(){
        return totalSlots;
    } 

    public int getCampCommitteeSlots(){
        return campCommitteeSlots;
    } 

    public int getCampCommitteeRemainingSlots(){
        return this.campCommitteeRemainingSlot; 
    }
    public String getDescription() {
        return description;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public int getNumberOfCampDays() {
        return numberOfCampDays;
    }

    public int getRemainingSlot() {
        return this.remainingSlot;
    }
}
