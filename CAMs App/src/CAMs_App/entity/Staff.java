package CAMs_App.entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import CAMs_App.enums.*;
public class Staff extends User{
    Scanner sc = new Scanner(System.in);
    private ArrayList <Camp> createdCamps = new ArrayList<>();
    private Suggestions suggestions;

    public Staff(int userID, String password,Faculty faculty,Status status) {
        super(userID, password, faculty, status);
    
    }
    public void createCamp (String campName,String dates,String registerDate,String availability,
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
    }
    public void editCamp(Camp camp){
        int choice=0;
        do
        {
            System.out.println("Editting "+camp.getCampName());
            System.out.println("1.Change camp name");
            System.out.println("2.Change camp dates");
            System.out.println("3.Change registration date");
            System.out.println("4.Open camp to own school or whole NTU");
            System.out.println("5.Edit camp location");
            System.out.println("6.Edit number of camp committee slots");
            System.out.println("7.Edit camp description");
            System.out.println("8.Quit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Edit camp name");
                    System.out.println("Change camp name to");
                    camp.setCampName(sc.next());
                    System.out.println("Camp name changed in to "+camp.getCampName());
                    break;
                case 2:
                    System.out.println("Edit camp dates");
                    System.out.println("Enter starting date in dd-mm-yyyy format:");
                    String start = sc.next();
                    System.out.println("Enter ending date in dd-mm-yyyy format:");
                    String end = sc.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate startDate = LocalDate.parse(start,formatter);
                    System.out.println(startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    LocalDate endDate = LocalDate.parse(end,formatter);
                    System.out.println(endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    int numberofDays = (int)ChronoUnit.DAYS.between(startDate, endDate);
                    camp.setNumberOfCampDays(numberofDays);
                    camp.setCampDate(start);
                    break;
                case 3:
                    System.out.println("Edit camp registeration closing date");
                    System.out.println("Enter new closing date:");
                    camp.setRegCloseDate(sc.next());
                    System.out.println("Camp new closing date: "+camp.getRegCloseDate());
                    break;
                case 4:
                    System.out.println("Edit camp to open to own school or whole NTU");
                    System.out.println("1.Open to School\n2.Open to NTU\nEnter(1/2):");
                    break;
                case 5:
                    System.out.println("Edit camp location");
                    System.out.println("Enter camp's new location:");
                    camp.setLocation(sc.next());
                    System.out.println("Camp location changed to: "+camp.getLocation());                    
                    break;
                case 6:
                    System.out.println("Edit camp committee slots(up to max 10");
                    System.out.println("Set number of camp commitee slots");
                    camp.setCampCommitteeSlots(sc.nextInt());
                    System.out.println("The camp committee slots changed to: "+camp.getCampCommitteeSlots());
                    break;
                case 7:
                    System.out.println("Edit camp description");
                    System.out.println("Enter new camp description");
                    camp.setDescription(sc.next());
                    System.out.println("Camp description has been changed to\n"+camp.getDescription());
                    break;       
                case 8:
                    System.out.println("Exitting camp edits.");
                    break;    
                default:
                    System.out.println("Re-enter choice");
                    break;
            }
        }while(choice!=8);
    }
    public void deleteCamp(Camp camp){
        createdCamps.remove(camp);
    }

    public void toggleVisibility(Camp camp){
        System.out.println("Set visibility for "+camp+" (True/False):");
        boolean tV = sc.nextBoolean();
        camp.setVisibility(tV);
    }
    public void printAllCamps(){
        //print from database?
        //printing current Staff createdCamps
        for(int i=0;i<createdCamps.size();i++)
            System.out.println(createdCamps.get(i).toString());
    }

    public ArrayList<Camp> campCreatedByStaff(){
        return createdCamps;
    }


    //Enquiries
    //print all 
    public void viewAllEnquiries(Camp camp){
        for(int i = 0 ; i < camp.getEnquiryList().size() ; i++){
            System.out.println("Enquiry " + (i+1));
            camp.getEnquiryList().get(i).viewEnquiries();
            System.out.println("");
        }
    }

    //print 1
    public void viewEnquiries(Camp camp , int index){
        camp.getEnquiryList().get(index).viewEnquiries();
    }

    public void replyEnquiries(Camp camp , int index){
        Enquiries q = camp.getEnquiryList().get(index - 1);
        if(!q.getProcessed()){
            System.out.println("Reply to query: ");
            String ans = sc.next();
            q.setAnswer(ans, this.getUserID());
        }
        else{
            System.out.println("This query has been processed...");
        }
     
    }

    //Suggestions
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
