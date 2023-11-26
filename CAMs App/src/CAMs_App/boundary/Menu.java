package CAMs_App.boundary;

/**
 * The {@link Menu} interface represents the generic menu that
 * defines printMenu() and viewApp() functions
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public interface Menu {
    /**
     * Prints the menu and its available selections for the specific class.
     */
    public void printMenu();
    /**
     * Call the printMenu(), prompt user input and calls the respective menu or controller based on user input
     */
    public void viewApp();
    
}
