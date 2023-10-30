package CAMs_App;

import java.util.*;

import CAMs_App.User.Faculty;

public class CampInfo {
    private String campName;
    private String campDate;
    private String regCloseDate;
    private Faculty userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInCharge;
    private Boolean visibility;
    private int numberOfCampDays;

    public CampInfo(String campName, String campDate, String regCloseDate, Faculty userGroup,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int numberOfCampDays){
        setCampName(campName);
        setCampDate(campDate);
        setRegCloseDate(regCloseDate);
        setLocation(location);
        setTotalSlots(totalSlots);
        setCampCommitteeSlots(campCommitteeSlots);
        setDescription(description);
        setStaffInCharge(staffInCharge);
    } 




    public boolean setCampName(String campName){
        this.campName = campName;
        return true;
    }

    public boolean setCampDate(String campDate){
        this.campDate = campDate;
        return true;
    }

    public boolean setRegCloseDate(String regCloseDate){
        this.regCloseDate = regCloseDate;
        return true;
    }

    public boolean setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
        return true;
    }

    public boolean setLocation(String location){
        this.location = location;
        return true;
    }

    public boolean setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return true;
    }

    public boolean setCampCommitteeSlots(int campCommitteeSlots){
        this.campCommitteeSlots = campCommitteeSlots;
        return true;
    }

    public boolean setDescription(String description){
        this.description = description;
        return true;
    }

    public boolean setStaffInCharge(String staffInCharge){
        this.staffInCharge = staffInCharge;
        return true;
    }

    public boolean setVisibility(Boolean visibility){
        this.visibility = visibility;
        return true;
    }

    public void setNumberOfCampDays(int numberOfCampDays) {
        this.numberOfCampDays = numberOfCampDays;
        
    }

    public String getCampName(){
        return campName;
    }
        
    public String getCampDate(){
        return campDate;
    } 

    public String getRegCloseDate(){
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
}
