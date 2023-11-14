package CAMs_App.service;

import CAMs_App.entity.*;

// class provides methods for reading and writing data from / to CSV files 

public class HelperService {

/**
   * Method to clear the screen of the terminal for user experience and neat
   * interface.
   */
  public static void clearScreen() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception err) {}
  }

    public void viewCamp(Camp camp){
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
    }

}
