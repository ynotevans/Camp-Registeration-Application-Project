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

    public static void addToDatabase(User user, ArrayList<User> database){
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

    
}
