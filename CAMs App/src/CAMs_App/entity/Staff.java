package CAMs_App.entity;

import CAMs_App.enums.*;
public class Staff extends User{
    public Staff(int userID, String password,Faculty faculty,Status status) {
        super(userID, password, faculty, status);
    
    }
}
