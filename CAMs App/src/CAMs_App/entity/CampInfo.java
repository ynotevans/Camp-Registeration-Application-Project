package CAMs_App.entity;

import java.util.*;
import CAMs_App.enums.*;
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

    //constructor
    CampInfo(String campName, String campDate, String regCloseDate, Faculty userGroup,String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, Boolean visibility, int numberOfCampDays){
        setCampName(campName);
        setCampDate(campDate);
        setRegCloseDate(regCloseDate);
        setLocation(location);
        setTotalSlots(totalSlots);
        setCampCommitteeSlots(campCommitteeSlots);
        setDescription(description);
        setStaffInCharge(staffInCharge);
        setNumberOfCampDays(numberOfCampDays);
    } 

    //default constructor
    CampInfo(){};



    //setters
    public void setCampName(String campName){
        this.campName = campName;
    }

    public void setCampDate(String campDate){
        this.campDate = campDate;
    }

    public void setRegCloseDate(String regCloseDate){
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

    //getters
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
