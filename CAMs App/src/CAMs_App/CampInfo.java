package CAMs_App;

public class CampInfo {
    private String campName;
    private int date;
    private int regCloseDate;
    // open to own school or NTU
    private String location;
    private int totalSlots;
    private int campComitteeSlots;
    private String description;
    private String staffInCharge;

    // public void getCampInfo(String campName, int date, int regCloseDate, String location, int totalSlots, int campComitteeSlots, String description, String staffInCharge){
        //     this.regCloseDate = regCloseDate;
        //     this.location = location;
        //     this.totalSlots = totalSlots;
        //     this.campComitteeSlots = campComitteeSlots;
        //     this.description = description;
        //     this.staffInCharge = staffInCharge;
        // }
        
    public void getCampName(String campName){
            this.campName = campName;
    }
        
    public void getCampDate(int date){
            this.date = date;
    } 
}
