package CAMs_App.service;

import java.util.Map;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.CampCompMem;


public class AuthCampCompMemService extends AuthService {
    public AuthCampCompMemService(){
        super();
    };
    
    @Override
    public boolean login(String userID, String password) {
        // TODO Auto-generated method stub
        Map<String, CampCompMem> campCompMemData = Database.getCampCompMemData();
        CampCompMem campCompMem = campCompMemData.get(userID);
        if (authenticate(campCompMem, password)==false)
            return false;

        AuthData.setCurrentUser(campCompMem);

        return false;
    }
 
}