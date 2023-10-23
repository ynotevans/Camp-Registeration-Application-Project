package CAMs_App;

import java.util.*;

public class CampInfo {
    private String campName;
    private int campDate;
    private int regCloseDate;
    // open to own school or NTU
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private String staffInCharge;

    public CampInfo(String campName, int campDate, int regCloseDate, String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge){
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

    public boolean setCampDate(int campDate){
        this.campDate = campDate;
        return true;
    }

    public boolean setRegCloseDate(int regCloseDate){
        this.regCloseDate = regCloseDate;
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

    public String getCampName(){
        return campName;
    }
        
    public int getCampDate(){
            return campDate;
    } 

    public int getRegCloseDate(){
            return regCloseDate;
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
}
