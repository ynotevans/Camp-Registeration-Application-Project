package CAMs_App;
import java.util.ArrayList;

public class Database {
    public static int findUserPos(User user, ArrayList<User> database){
        int j;
        for (j=0;j<database.size();j++) {
            if (database.get(j).getUserID() == user.getUserID())
                return j;
        }
        return -1;
    }

   

    public static void addUserToDatabase(User user, ArrayList<User> database){
        int j = findUserPos(user, database);
        if (j!=-1) {
            database.set(j,user);
            System.out.println(user.getUserID() + " successfully added.");
            return;
        }

        database.add(user);
        System.out.println(user.getUserID() + " successfully added.");
        return;
    }

     public static int findCampPos(Camp camp, ArrayList<Camp> database){
        int j;
        for (j=0;j<database.size();j++) {
            if (database.get(j).getCampName() == camp.getCampName())
                return j;
        }
        return -1;
    }

    public static void addCampToDatabase(Camp camp, ArrayList<Camp> database){
        int j = findCampPos(camp, database);
        if (j!=-1) {
            database.set(j,camp);
            System.out.println(camp.getCampName() + " successfully added.");
            return;
        }

        database.add(camp);
        System.out.println(camp.getCampName() + " successfully added.");
        return;
    }
}
