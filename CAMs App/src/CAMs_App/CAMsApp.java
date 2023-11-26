package CAMs_App;

import CAMs_App.boundary.MainMenu;

/**
 * The main class responsible for running the CAMs application.
 * 
 *  <p>
 * This class handles initializing of the main menu.
 * </p>
 */
public class CAMsApp {
	/**
	 * The entry point for the CAMs application. This method is generates a new
	 * instance of the {@link MainMenu} object, as well as calls the viewApp method.
	 * 
	 * @param args an array of String arguments passed to this method
	 */
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.viewApp();

        
    }
}
