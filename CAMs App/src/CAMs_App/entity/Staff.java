package CAMs_App.entity;

import CAMs_App.enums.*;

/**
 * The {@link Staff} class represents a staff user in the system.
 * It extends the {@link User} class, which contains basic user information.
 */
public class Staff extends User{
	/**
	 * Constructs a {@link Staff} objects with the given user info.
	 * 
	 * @param userID	The ID of the staff.
	 * @param password	The password of the staff.
	 * @param faculty	The {@link Faculty} of the staff.
	 * @param name		The name of the staff.
	 */
    public Staff(String userID, String password,Faculty faculty, String name) {
        super(userID, password, faculty,name);
    }
   
}
