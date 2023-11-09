package CAMs_App.boundary;

public interface Menu {
    
    //print the available selections for the certain class
    public void printMenu();

    //call the printMenu(), prompt user input and call the respective menu based on user input
    public void viewApp();
    
}
