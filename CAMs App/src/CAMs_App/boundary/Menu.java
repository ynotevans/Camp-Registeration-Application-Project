package CAMs_App.boundary;

/**
 * The {@link Menu} interface represents the generic menu that
 * defines printMenu() and viewApp() functions
 */
public interface Menu {
    /**
     * Prints the menu and its available selections for the specific class.
     */
    public void printMenu();
    /**
     * Call the printMenu(), prompt user input and calls the respective menu based on user input
     */
    public void viewApp();
    
}
