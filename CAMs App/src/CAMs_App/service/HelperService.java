package CAMs_App.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import CAMs_App.data.Database;
import CAMs_App.entity.*;
import java.util.Scanner;

public class HelperService {
    static Scanner sc = new Scanner(System.in);

    /**
     * Method to clear the screen of the terminal for user experience and neat
     * interface.
    */
    public static void clearScreen() {
        try {
          new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception err) {}
    } 

    public static void printRoute(String route) {
        String spaces = String.format("%" + (105 - route.length()) + "s", "");
        System.out.println(
            "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ "+ route + spaces + "║");
        System.out.println(
            "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

 
    public static int readInt(){
      while (true) {
        try {
          int userInput = -1;
          userInput = sc.nextInt();
          sc.nextLine(); // Consume newline left-over
          return userInput;
        } catch (InputMismatchException e) {
          sc.nextLine();
          System.out.println("Invalid Input, enter an integer!");
        }
      }
    }

    //make some delay
    public static void wait(int sec){
      int milli = sec*1000;
      try {
          Thread.sleep(milli);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }

    public static void pressAnyKeyToContinue() {
      System.out.println("Press <Enter> key to continue...");
      try {
        System.in.read();
      } catch (Exception e) {
      }
    }

    public static int readInt(int min, int max , String message) {
      while (true) {
          try{
              int userInput = -1;
              userInput = sc.nextInt();
              sc.nextLine(); // Consume newline left-over
              if (userInput < min || userInput > max) {
                throw new OutOfRange();
              } else {
                return userInput;
              }
          } catch (InputMismatchException e) {
              sc.nextLine();
              System.out.println("\nInvalid Input, Enter an integer!");
            } catch (OutOfRange e) {
              System.out.println(message);
              }
        }
    }

    public static void viewCamp(Camp camp){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp Date: " + camp.getCampDate());
        System.out.println("Camp Registration Closing Date: " + camp.getRegCloseDate());
        System.out.println("Camp open to faculty: " + camp.getUserGroup().toString());   
        System.out.println("Camp location: " + camp.getLocation());
        System.out.println("Camp total slots: " + camp.getTotalSlots());
        System.out.println("Camp descriptions: " + camp.getDescription());
        System.out.println("Camp staffInCharge: " + camp.getStaffInCharge());
        System.out.println("Number of days: " + camp.getNumberOfCampDays());
        System.out.println("Camp remaining slot: " + camp.getRemainingSlot());
        System.out.println("\n");
    }

    public static List<Camp> filter(){
        System.out.println("View camps by :");
        System.out.println("(1) Dates");
        System.out.println("(2) Location");
        System.out.println("(3) Faculty");
        System.out.println("Alphabetical order by default (Any number)");
        Map<String, Camp> camp = Database.getCampData();

        int choice = HelperService.readInt();

        switch (choice) {
            case 1:    //date
                List<Camp> sortedDate = sortCampsByDates(camp);
                return sortedDate;
                
            
            case 2:    //location
                List<Camp> sortedLocation = sortCampsByLocation(camp);    
                return sortedLocation;

            case 3:    //Own faculty
                List<Camp> sortedFaculty = sortCampsByFaculty(camp);
                return sortedFaculty;
              
          
            default:   //alphabetical order
            List<Camp> sortedName = sortCampsByName(camp);
                return sortedName;
                
        }
    }

    public static List<Camp> sortCampsByName (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getCampName));
        return campList;
    }

    public static List<Camp> sortCampsByDates (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getCampDate));
        return campList;
    }
    
    public static List<Camp> sortCampsByLocation (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getLocation));
        return campList;
    }

    public static List<Camp> sortCampsByFaculty (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getUserGroup));
        return campList;
    }
   

}

class OutOfRange extends Exception {

    /**
     * Constructor that initialises the error message
     */
    public OutOfRange() {
      super("Input is out of allowed range");
    }
  
    /**
     * Overrided constructor that initialises the error message with the specified
     * message
     * 
     * @param message error message to be displayed
     */
    public OutOfRange(String message) {
      super(message);
    }
}