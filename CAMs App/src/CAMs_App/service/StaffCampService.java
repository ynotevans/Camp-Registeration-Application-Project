package CAMs_App.service;

import java.time.LocalDate;
import java.util.Map;
import CAMs_App.data.*;
import CAMs_App.entity.*;


public class StaffCampService extends CampManagementService{
   
    public void createCamp (String campName,LocalDate dates,LocalDate registerDate,String availability,
    String location,int totalSlots,int campCommitteeSlots,String description,String staffInCharge){
        Camp camp = new Camp();
        camp.setCampName(campName);
        camp.setCampDate(dates);
        camp.setRegCloseDate(registerDate);
        camp.setLocation(location);
        camp.setCampCommitteeSlots(campCommitteeSlots);
        camp.setDescription(description);
        camp.setStaffInCharge(staffInCharge);
        camp.setVisibility(true);

        Database.getCampData().put(campName , camp);
    }

    public void editCamp(Camp camp){
       
    }

    public boolean deleteCamp(String campName){
        Camp camp = DatabaseService.getCamp(campName);

        if(camp.getAttendees().size()!= 0) return false;
        else{
            Database.getCampData().remove(campName);
            return true;
        }

    }


    public void toggleVisibility(Camp camp , boolean visibility){
      camp.setVisibility(visibility);
    }

    // view camp
    public void viewAllCamps(){
        Map<String, Camp> camp1 = Database.getCampData(); 

        for (Camp camp : camp1.values()){
            HelperService.viewCamp(camp);
        }
    }

    // see staff created camps
    public void viewCampsCreated(String userID){
        Map<String, Camp> camp1 = Database.getCampData(); 

        for (Camp camp : camp1.values()){
            if(camp.getStaffInCharge() == userID)
                HelperService.viewCamp(camp);
        }
    }
    
    public static void addPoints(Student student) {
		student.setPoints(student.getPoints() + 1);
	}

    
    // generate attendance report
    public void generateAttendanceReport(Camp camp){
        System.out.println("Participants attending camp");
        for(int i=0;i<camp.getAttendees().size();i++)
            System.out.println(camp.getAttendees().get(i).getUserID());
        System.out.println("Committee members attending camp");
        for(int i=0;i>camp.getCommittee().size();i++)
            System.out.println(camp.getCommittee().get(i).getUserID());
    }
    // generate performance report
    public void generatePerformanceReport(Camp camp){
        for(int i=0;i<camp.getAttendees().size();i++){
            System.out.println(camp.getAttendees().get(i).getUserID()+":"
            +camp.getAttendees().get(i).getPoints());
        }
    }    
}