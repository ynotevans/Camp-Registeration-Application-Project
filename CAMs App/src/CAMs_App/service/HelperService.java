package CAMs_App.service;


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

}
