package CAMs_App.entity;

import CAMs_App.enums.*;

/**
 * The {@link User} class represents a user in the system.
 * Users have a name, faculty, unique ID and password.
 */
public class User {
	/**
	 * The ID of the user.
	 */
    private String userID;  
    /**
     * The name of the user.
     */
    private String name; 
    /**
     * The password of the user.
     */
    private String password;
    /**
     * The {@link Faculty} of the user.
     */
    private Faculty faculty;

    
    /**
     * Constructs an {@link User} object with the given info.
     * 
     * @param userID	ID of the user.
     * @param password	Password of the user.
     * @param faculty	{@link Faculty} of the user.
     * @param name		Name of the user.
     */
    public User(String userID, String password, Faculty faculty, String name){
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
    }
    
    /**
     * Sets the user ID.
     * 
     * @param userID The ID to be set.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    /**
     * Sets the name of the user.
     * 
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the password of the user.
     * 
     * @param password The password to be set.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Sets the {@link Faculty} the user belongs to.
     * @param faculty The {@link Faculty} to be set.
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    /**
     * Gets the ID of the user.
     * 
     * @return The user ID.
     */
    public String getUserID() {
        return userID;
    }
    
    /**
     * Gets the name of the user.
     * 
     * @return The user name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the password of the user.
     * 
     * @return The user password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Gets The {@link Faculty} the user belongs to.
     * 
     * @return The user {@link Faculty}.
     */
    public String getFaculty() {
        return faculty.toString();
    }
}
