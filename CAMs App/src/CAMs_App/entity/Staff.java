package CAMs_App.entity;

import CAMs_App.enums.*;
public class Staff extends User{
    public Staff(String userID, String password,Faculty faculty) {
        super(userID, password, faculty);
        super.setIdentity(Identity.staff);
    }
   
}
