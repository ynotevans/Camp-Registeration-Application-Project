package CAMs_App;

import java.util.*;
import CAMs_App.enums.*;
public class Staff extends User{
    Scanner sc = new Scanner(System.in);
    private CampInfo campInfo;
    private Enquiries enquiries;
    private ArrayList <Camp> createdCamps = new ArrayList<>();
    private Suggestions suggestions;

    public Staff(int userID, String password,Faculty faculty,Status status) {
        super(userID, password, faculty, status);
    
    }
    public void createCamp (String campName,String dates,String registerDate,String availability,
    String location,int totalSlots,int campCommitteeSlots,String description,String staffInCharge){
        campInfo.setCampName(campName);
        campInfo.setCampDate(dates);
        campInfo.setRegCloseDate(registerDate);
        campInfo.setLocation(location);
        campInfo.setCampCommitteeSlots(campCommitteeSlots);
        campInfo.setDescription(description);
        campInfo.setStaffInCharge(staffInCharge);
        campInfo.setVisibility(true);
    }
    public void editCamp(Camp camp){
        System.out.println("Edit what about the "+camp);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
        
            default:
                break;
        }
    }
    public void deleteCamp(Camp camp){
        createdCamps.remove(camp);
    }
    public void toggleVisibility(Camp camp){
        System.out.println("Set visibility for "+camp+" (True/False):");
        boolean tV = sc.nextBoolean();
        campInfo.setVisibility(tV);
    }
    public void printAllCamps(){
        //print from database?
    }

    public ArrayList<Camp> campCreatedByStaff(){
        return createdCamps;
    }

    public void viewEnquiries(Camp camp){
        enquiries.getEnquiry();
    }
    public void replyEnquiries(Camp camp){
        System.out.println("Enter enquiry");
        String getenquiry = sc.next();
        enquiries.setEnquiry(getenquiry);
    }
    public void viewSuggestions(Camp camp){
        suggestions.getSuggestion();
    }
    public void approveSuggestions(Camp camp){
        System.out.println("Approve suggestion?");
        boolean approve = sc.nextBoolean();
        suggestions.setAccepted(approve);
    }
    /*(
    public void campReport(Camp camp){

    }
    public void performanceReport(Camp camp){

    }
    ) */
}
